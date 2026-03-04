package Controller;

import Services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/forgetPassword")
public class ForgetPassword extends HttpServlet {
    UserServices userServices = new UserServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String referer = request.getHeader("Referer");
        String targetPage = (referer != null) ? referer : "index";
        try {
            String email = request.getParameter("email");
            if (email == null){
                request.getSession().setAttribute("errorMessage", "Email is null");
                response.sendRedirect(targetPage);
                return;
            }
            String result = userServices.ForgetPassword(email);
            HttpSession session = request.getSession();
            session.setAttribute("resetEmail",email);
            response.sendRedirect("reset-password.jsp");
        }
        catch (Exception ex){
            request.getSession().setAttribute("errorMessage", ex.getMessage());
            response.sendRedirect(targetPage);
        }
    }
}
