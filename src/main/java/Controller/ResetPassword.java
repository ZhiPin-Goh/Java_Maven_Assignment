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

        // 从 Session 里拿出上一步存的 Email
        String email = (String) session.getAttribute("resetEmail");

        // 如果 Email 丢失（直接强行访问网址），踢回忘记密码页
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

            // 3. 呼叫后端 API
            ResetPasswordDTO dto = new ResetPasswordDTO(email, otp, newPassword);
            String result = userServices.ResetPassword(dto);

            // 4. 重置成功！清理 Session 并跳转登录页
            session.removeAttribute("resetEmail");
            session.setAttribute("successMessage", "Password reset successfully! You can now login.");
            response.sendRedirect("login.jsp");

        } catch (Exception ex) {
            // OTP 错误、过期等问题，留在当前页显示错误
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("reset-password.jsp").forward(request, response);
        }
    }
}
