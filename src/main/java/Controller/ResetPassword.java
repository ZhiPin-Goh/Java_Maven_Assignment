package Controller;

import ModelsDTO.ResetPasswordDTO;
import Services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/resetPassword")
public class ResetPassword extends HttpServlet {
    UserServices userServices = new UserServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("resetEmail");

        if (email == null) {
            response.sendRedirect("forget-password.jsp");
            return;
        }

        String otp = request.getParameter("otp");
        String newPassword = request.getParameter("newpassword");
        String confirmPassword = request.getParameter("confirmpassword");
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$";

        try {
            if (!newPassword.matches(passwordPattern)) {
                request.setAttribute("errorMessage", "Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character.");
                request.getRequestDispatcher("reset-password.jsp").forward(request, response);
                return;
            }

            if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("errorMessage", "New Password and Confirm Password do not match.");
                request.getRequestDispatcher("reset-password.jsp").forward(request, response);
                return;
            }
            ResetPasswordDTO dto = new ResetPasswordDTO(email, otp, newPassword);
            String result = userServices.ResetPassword(dto);

            session.removeAttribute("resetEmail");
            session.setAttribute("successMessage", "Password reset successfully! You can now login.");
            response.sendRedirect("login.jsp");

        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
        }
    }
}
