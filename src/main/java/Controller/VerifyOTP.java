package Controller;

import Services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/verifyOtp")
public class VerifyOTP extends HttpServlet {
    UserServices userServices = new UserServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String referer = request.getHeader("Referer");
        String targetPage = (referer != null) ? referer : "index";
        String email = (String) session.getAttribute("pendingVerificationEmail");
        String otp = request.getParameter("otp");
        if (email == null) {
            response.sendRedirect("signup.jsp");
            return;
        }
        try{
            String result = userServices.VerifyOTP(email, otp);
            session.removeAttribute("pendingVerificationEmail");
            session.setAttribute("successMessage", "Account verified successfully! You can now login.");
            response.sendRedirect("login.jsp");
        }
        catch (Exception ex){
            request.setAttribute("errorMessage", ex.getMessage());
            response.sendRedirect(targetPage);
        }
    }
}
