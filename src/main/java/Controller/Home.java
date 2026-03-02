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

@WebServlet("/index")
public class Home extends HttpServlet {
    private BeverageServices beverageServices = new BeverageServices();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            List<Beverage> beverageList = beverageServices.getAllBeverage();
            request.setAttribute("beverages", beverageList);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            request.setAttribute("error", ex.getMessage());
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
