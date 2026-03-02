package Controller;

import ModelsDTO.CartQuantityDTO;
import Services.CartServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {"/addQuantity", "/minusQuantity"})
public class CartQuantity extends HttpServlet {
    private CartServices cartServices = new CartServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("loggedInUserID");
        if (userId == null){
            response.sendRedirect("index.jsp");
            return;
        }
        String path = request.getServletPath();

        switch (path){
            case "/addQuantity":
                try{
                    int cartID = Integer.parseInt(request.getParameter("cartID"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    CartQuantityDTO dto = new CartQuantityDTO(userId, cartID, quantity);
                    String result = cartServices.AddQuantity(dto);
                    response.sendRedirect("cart");
                }
                catch (Exception ex){
                    request.setAttribute("error", ex.getMessage());
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                }
                break;
            case "/minusQuantity":
                try{
                    int cartID = Integer.parseInt(request.getParameter("cartID"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    CartQuantityDTO dto = new CartQuantityDTO(userId, cartID, quantity);
                    String result = cartServices.MinusQuantity(dto);
                    response.sendRedirect("cart");
                }
                catch (Exception ex){
                    request.setAttribute("error",ex.getMessage());
                    request.getRequestDispatcher("cart.jsp").forward(request, response);

                }
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }

    }
}
