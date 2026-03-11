package Controller;

import Services.PointServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/getPoints")
public class DailyGetPoints extends HttpServlet {
    PointServices pointServices = new PointServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String referer = request.getHeader("Referer");
        String targetPage = (referer != null) ? referer : "index";
        Integer userId = (Integer) session.getAttribute("loggedInUserID");
        if (userId == null){
            response.sendRedirect("sign-up.jsp");
            return;
        }
        try{
            String result = pointServices.DailyCheckIn(userId);
            response.sendRedirect(targetPage);
        } catch (Exception ex) {
            String errorMsg = ex.getMessage();
            if (errorMsg != null && errorMsg.contains("already checked in")) {
                response.sendRedirect(request.getContextPath() + "/myPoints?alreadyCheckedIn=true");
            } else {
                response.sendRedirect(request.getContextPath() + "/myPoints?error=" + java.net.URLEncoder.encode(errorMsg, "UTF-8"));
            }
        }
    }
}
