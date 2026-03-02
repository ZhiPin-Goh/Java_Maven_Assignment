package Controller;

import Models.Cart;
import ModelsDTO.CartDTO;
import Services.CartServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/addtocart")
public class AddToCart extends HttpServlet {
    private CartServices cartServices = new CartServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String referer = request.getHeader("Referer");
        String targetPage = (referer != null) ? referer : "home";
        Integer userId = (Integer) session.getAttribute("loggedInUserID");
        if (userId == null){
            response.sendRedirect("index.jsp");
            return;
        }
        try{
            int beverageId = Integer.parseInt(request.getParameter("beverageId"));
            int quantity = getIntParameter(request, "quantity", 1);
            int sizeId = getIntParameter(request,"sizeId", 1);
            int sugarId = getIntParameter(request, "sugarId", 1);
            int iceId = getIntParameter(request, "iceId", 1);
            Cart cart = new Cart(0, userId, beverageId, quantity, sugarId, sizeId, iceId);

            String result = cartServices.CreateCart(cart);
            session.setAttribute("successMessage", "Added to cart successfully!");
            response.sendRedirect(targetPage);
        }
        catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Failed to add to cart: " + e.getMessage());
            response.sendRedirect(targetPage);
        }
    }
    private int getIntParameter(HttpServletRequest request, String paramName, int defaultValue) {
        String paramValue = request.getParameter(paramName);
        if (paramValue != null && !paramValue.isEmpty()) {
            try {
                return Integer.parseInt(paramValue);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        return defaultValue;
    }
}
