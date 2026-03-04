package Controller;

import Services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/resendOtp")
public class ResendOTP extends HttpServlet {
    UserServices userServices = new UserServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String referer = request.getHeader("Referer");
        String targetPage = (referer != null) ? referer : "index";
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("pendingVerificationEmail");
        if (email == null){
            response.sendRedirect("sign-up.jsp");
            return;
        }
        try{
            String result = userServices.ResendOTP(email);
            response.sendRedirect(targetPage);
        }
        catch (Exception ex){
            request.setAttribute("errorMessage", ex.getMessage());
            response.sendRedirect(targetPage);
        }
    }
}
