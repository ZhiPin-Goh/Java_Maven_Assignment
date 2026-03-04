package Controller;

import ModelsDTO.CreateUserDTO;
import Services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/signUp")
public class SIgnUp extends HttpServlet {
    UserServices userServices = new UserServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Password_Pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$";
        try{
            String username = request.getParameter("username");
            String phonenumber = request.getParameter("phonenumber");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            if (!password.matches(Password_Pattern)){
                request.setAttribute("errorMessage","Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character.");
                request.getRequestDispatcher("sign-up.jsp").forward(request, response);
                return;
            }
            CreateUserDTO user = new CreateUserDTO(username, email, phonenumber, password);
            String result = userServices.CreateUser(user);
            HttpSession session = request.getSession();
            session.setAttribute("pendingVerificationEmail", email);
            session.setAttribute("successMessage", "Sign up successful! Please check your email for the OTP code.");
            response.sendRedirect("verify-otp");
        }
        catch (Exception ex){
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("sign-up.jsp").forward(request,response);
        }
    }
}
