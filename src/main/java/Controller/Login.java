package Controller;
import Models.*;
import Services.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet{
    UserServices userServices = new UserServices();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try{
            int userID = userServices.LoginUser(email, password);
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUserID", userID);
            response.sendRedirect("index.jsp");
        }
        catch (Exception ex){
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}
