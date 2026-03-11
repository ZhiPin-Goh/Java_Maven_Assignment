package Controller;

import Models.Beverage;
import ModelsDTO.TransactionPreparingDTO;
import Services.BeverageServices;
import Services.TransactionServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class Home extends HttpServlet {
    private BeverageServices beverageServices = new BeverageServices();
    private TransactionServices transactionServices = new TransactionServices();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Beverage> beverageList = beverageServices.getAllBeverage();
            request.setAttribute("beverages", beverageList);
            
            try {
                List<Beverage> bestbeverages = beverageServices.BestSellerBeverage();
                request.setAttribute("bestbeverages", bestbeverages);
            } catch (Exception e) {
                System.out.println("Failed to load best sellers: " + e.getMessage());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            request.setAttribute("error", ex.getMessage());
        }

        // Load preparing orders if user is logged in
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("loggedInUserID");
        if (userId != null) {
            try {
                List<TransactionPreparingDTO> preparing = transactionServices.GetTransactionPreparing(userId);
                request.setAttribute("preparing", preparing);
            } catch (Exception ex) {
                System.out.println("Failed to load preparing orders: " + ex.getMessage());
            }
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
