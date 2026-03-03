package Controller;

import Models.User;
import ModelsDTO.EditUserDTO;
import Services.UserServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = {"/profile", "/editProfile"})
public class Profile extends HttpServlet {
    UserServices userServices = new UserServices();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("loggedInUserID");
        if (userId == null){
            response.sendRedirect("index.jsp");
            return;
        }
        try{
            User user = userServices.SearchUserByID(userId);
            if (user != null){
                request.setAttribute("user", user);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }
            else{
                request.setAttribute("error", "Please login");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        }
        catch (Exception ex){
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String referer = request.getHeader("Referer");
        String targetPage = (referer != null) ? referer : "home";
        Integer userId = (Integer) session.getAttribute("loggedInUserID");
        if (userId == null){
            response.sendRedirect("index.jsp");
            return;
        }
        try{
            String userName = request.getParameter("username");
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phonenumber");
            EditUserDTO edituser = new EditUserDTO(userId, userName, email, phoneNumber);
            String result = userServices.EditUser(edituser);
            session.setAttribute("successMessage", "Profile update successfully!");
            response.sendRedirect(targetPage);
        }
        catch (Exception ex){
            session.setAttribute("errorMessage", "Failed to update profile: " + ex.getMessage());
            response.sendRedirect(targetPage);
        }
    }
}
