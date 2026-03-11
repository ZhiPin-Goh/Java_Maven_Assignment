package Controller;

import ModelsDTO.CartDTO;
import ModelsDTO.GetCartDTO;
import Services.CartServices;
import Services.PointServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class Cart extends HttpServlet {
    private CartServices cartServices = new CartServices();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       try{
           HttpSession session = request.getSession();
           Integer userId = (Integer) session.getAttribute("loggedInUserID");
           if (userId == null){
               response.sendRedirect("sign-up.jsp");
               return;
           }
           GetCartDTO cartData = cartServices.GetUserCartList(userId);
           request.setAttribute("cartData", cartData);

           try {
               PointServices pointServices = new PointServices();
               java.math.BigDecimal userPoints = pointServices.GetPoints(userId);
               request.setAttribute("userPoints", userPoints);
           } catch (Exception e) {
               // Silently fail if points can't be fetched, checkout should still work
               request.setAttribute("userPoints", java.math.BigDecimal.ZERO);
           }

           request.getRequestDispatcher("cart.jsp").forward(request,response);
       }
       catch (Exception ex){
           request.setAttribute("error", ex.getMessage());
           request.getRequestDispatcher("sign-up.jsp").forward(request,response);

       }
    }
}
