package Controller;

import Models.Beverage;
import Services.BeverageServices;
import Services.DrinkOptionServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/beverageDetails")
public class BeverageDetails extends HttpServlet {
    private BeverageServices beverageServices = new BeverageServices();
    DrinkOptionServices optionServices = new DrinkOptionServices();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendRedirect("index");
                return;
            }
            int beverageid = Integer.parseInt(idParam);
            Beverage beverage = beverageServices.GetBeverageByID(beverageid);
            if (beverage != null) {
                request.setAttribute("beverage", beverage);
                DrinkOptionServices optionServices = new DrinkOptionServices();
                request.setAttribute("sizes", optionServices.GetDrinkSize());
                request.setAttribute("sugars", optionServices.GetDrinkSugar());
                request.setAttribute("ices", optionServices.GetDrinkIce());
                request.getRequestDispatcher("beverage-details.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Sorry, this beverage is not found.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (NumberFormatException ex) {
            response.sendRedirect("index");
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            request.getRequestDispatcher("index").forward(request, response);
        }
    }
}
