package Controller;

import Models.Beverage;
import Services.BeverageServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/searchBeverage")
public class SearchBeverage extends HttpServlet {
    private BeverageServices beverageServices = new BeverageServices();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String keyword = request.getParameter("keyword");
            if (keyword == null || keyword.trim().isEmpty()) {
                response.sendRedirect("index");
                return;
            }
            List<Beverage> results = beverageServices.SearchBeverage(keyword.trim());
            request.setAttribute("beverages", results);
            request.getRequestDispatcher("drinks.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", "Search failed: " + ex.getMessage());
            request.getRequestDispatcher("drinks.jsp").forward(request, response);
        }
    }
}
