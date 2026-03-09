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

@WebServlet("beseSeller")
public class BestSeller extends HttpServlet {
    BeverageServices beverageServices = new BeverageServices();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            List<Beverage> bestbeverages = beverageServices.BestSellerBeverage();
            request.setAttribute("bestbeverages", bestbeverages);
            request.getRequestDispatcher("index").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("bestbeverages").forward(request, response);
        }
    }
}
