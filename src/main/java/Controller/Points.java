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

@WebServlet("/myPoints")
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

        try{
            BigDecimal points = pointServices.GetPoints(userId);
            request.setAttribute("points", points);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Failed to get points: " + ex.getMessage());
        }

        try{
            int page = 1;
            String pageParam = request.getParameter("page");
            if (pageParam != null && !pageParam.isEmpty()) {
                try {
                    page = Integer.parseInt(pageParam);
                } catch (NumberFormatException ignored) {}
            }
            int pageSize = 5;

            List<PointLogs> allLogs = pointServices.GetPointLogs(userId);
            
            int totalRecords = allLogs.size();
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
            
            if (page < 1) page = 1;
            if (page > totalPages && totalPages > 0) page = totalPages;

            int fromIndex = (page - 1) * pageSize;
            int toIndex = Math.min(fromIndex + pageSize, totalRecords);

            List<PointLogs> pagedLogs = null;
            if (fromIndex < totalRecords) {
                pagedLogs = allLogs.subList(fromIndex, toIndex);
            } else {
                pagedLogs = new java.util.ArrayList<>();
            }

            request.setAttribute("pointsLogs", pagedLogs);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
        }catch (Exception ex){
            request.setAttribute("errorMessage", "Failed to get point logs: " + ex.getMessage());
        }

        String errorParam = request.getParameter("error");
        if (errorParam != null && !errorParam.isEmpty()) {
            request.setAttribute("errorMessage", "Check-in failed: " + errorParam);
        }

        boolean alreadyCheckedIn = "true".equals(request.getParameter("alreadyCheckedIn"));
        request.setAttribute("alreadyCheckedIn", alreadyCheckedIn);

        request.getRequestDispatcher("my-points.jsp").forward(request,response);
    }
}
