package Controller;

import Services.CartServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/removeCart")
public class RemoveCart extends HttpServlet {
    CartServices cartServices = new CartServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String referer = request.getHeader("Referer");
        String targetPage = (referer != null) ? referer : "index";
        Integer userId = (Integer) session.getAttribute("loggedInUserID");
        if (userId == null){
            response.sendRedirect("sign-up.jsp");
            return;
        }
        try {
            int cartId = Integer.parseInt(request.getParameter("cartID"));
            String result = cartServices.RemoveCart(cartId);
            session.setAttribute("successMessage", "Item removed from cart!");
            response.sendRedirect(targetPage);
        }
        catch (Exception ex) {
            session.setAttribute("errorMessage", "Failed to remove cart: " + ex.getMessage());
            response.sendRedirect(targetPage);
        }
    }
}
