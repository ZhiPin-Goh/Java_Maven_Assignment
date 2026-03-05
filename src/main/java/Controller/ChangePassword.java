package Controller;

import ModelsDTO.ChangePasswordDTO;
import Services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/changePassword")
public class ChangePassword extends HttpServlet {
    UserServices userServices = new UserServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String referer = request.getHeader("Referer");
        String targetPage = (referer != null) ? referer : "home";
        Integer userId = (Integer) session.getAttribute("loggedInUserID");
        String Password_Pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$";
        if (userId == null){
            response.sendRedirect("sign-up.jsp");
            return;
        }
        try{
            String currentPassword = request.getParameter("currentpassword");
            String newPassword = request.getParameter("newpassword");
            String confirmPassword = request.getParameter("confirmPassword");
            if (!newPassword.matches(Password_Pattern)){
                session.setAttribute("errorMessage", "Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character.");
                response.sendRedirect(targetPage);
                return;
            }
            if (currentPassword.equals(newPassword)){
                session.setAttribute("errorMessage", "New Password cannot same");
                response.sendRedirect(targetPage);
                return;
            }
            if (!newPassword.equals(confirmPassword)){
                session.setAttribute("errorMessage", "New Password and Confirm password must be same");
                request.getRequestDispatcher("change-password.jsp").forward(request, response);
                return;
            }
            ChangePasswordDTO password = new ChangePasswordDTO(userId, currentPassword, confirmPassword);
            String result = userServices.ChangePassword(password);
            session.setAttribute("successMessage", "Change Password Success");
            response.sendRedirect(targetPage);
        }
        catch (Exception ex){
            session.setAttribute("errorMessage", "Failed to change password: " + ex.getMessage());
            request.getRequestDispatcher("change-password.jsp").forward(request, response);
        }
    }
}
