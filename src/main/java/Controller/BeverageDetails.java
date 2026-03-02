package Controller;

import Models.Beverage;
import Services.BeverageServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/beverageDetails")
public class BeverageDetails extends HttpServlet {
    private BeverageServices beverageServices = new BeverageServices();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()){
                response.sendRedirect("index");
                return;
            }
            int beverageid = Integer.parseInt(idParam);
            Beverage beverage = beverageServices.GetBeverageByID(beverageid);
            if(beverage != null){
                request.setAttribute("beverage", beverage);
                request.getRequestDispatcher("beverage-details.jsp").forward(request, response);
            }
            else{
                request.setAttribute("error", "Sorry, this beverage is not found.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (NumberFormatException ex){
            response.sendRedirect("index.jsp");
        } catch (Exception ex){
            ex.printStackTrace();
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}
