package Controller;

import Services.CheckOutServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/checkOut")
public class CheckOut extends HttpServlet {
    CheckOutServices checkOutServices = new CheckOutServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("loggedInUserID");
        if (userId == null){
            response.sendRedirect("index.jsp");
            return;
        }
        try{

        }
        catch (Exception ex){

            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("cart.jsp").forward(request,response);
        }
    }
}
