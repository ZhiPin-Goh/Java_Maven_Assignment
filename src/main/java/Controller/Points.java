package Controller;

import Models.PointLogs;
import Services.PointServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(urlPatterns = {"/getPoints", "/pointLogs"})
public class Points extends HttpServlet {
    PointServices pointServices = new PointServices();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("loggedInUserID");
        if (userId == null){
            response.sendRedirect("sign-up.jsp");
            return;
        }
        String referer = request.getHeader("Referer");
        String targetPage = (referer != null) ? referer : "index";
        String path = request.getServletPath();
        switch (path){

            //This is show point at header area: e.g:10 Point
            case "/getPoins":
                try{
                    BigDecimal points = pointServices.GetPoints(userId);
                    request.setAttribute("points", points);
                    request.getRequestDispatcher("my-points").forward(request, response);
                } catch (Exception ex) {
                    request.setAttribute("errorMessage", ex.getMessage());
                    request.getRequestDispatcher("my-points").forward(request,response);
                }
            // This show list point logs at footer area
            case "/pointLogs":
                try{
                    List<PointLogs> pointLogs = pointServices.GetPointLogs(userId);
                    request.setAttribute("pointsLogs", pointLogs);
                    request.getRequestDispatcher("my-points").forward(request,response);
                }catch (Exception ex){
                    request.setAttribute("errorMessage", ex.getMessage());
                    request.getRequestDispatcher("my-points").forward(request, response);
                }
        }
    }
}
