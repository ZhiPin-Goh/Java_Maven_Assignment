package Controller;

import ModelsDTO.TransactionDTO;
import ModelsDTO.TransactionPreparingDTO;
import Services.TransactionServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/orderList", "/orderDetails", "/orderPreparing"})
public class Order extends HttpServlet {
    TransactionServices transactionServices = new TransactionServices();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("loggedInUserID");

        if (userId == null){
            response.sendRedirect("index.jsp");
            return;
        }

        String path = request.getServletPath();
        switch (path){
            case "/orderList":
                try{
                    List<TransactionDTO> orderList = transactionServices.GetTransactionHistory(userId);
                    request.setAttribute("orderList", orderList);
                    request.getRequestDispatcher("order-history.jsp").forward(request, response);
                }
                catch (Exception ex){
                    request.setAttribute("errorMessage", ex.getMessage());
                    request.getRequestDispatcher("profile.jsp").forward(request, response);
                }
                break;
            case "/orderDetails":
                try{
                    String transNo = request.getParameter("transactionNo");

                    if (transNo == null || transNo.isEmpty()) {
                        response.sendRedirect("orderList");
                        return;
                    }

                    TransactionDTO orderDetails = transactionServices.GetOneTransactionDetails(transNo);

                    request.setAttribute("orderDetails", orderDetails);

                    request.getRequestDispatcher("order-details.jsp").forward(request, response);
                }
                catch (Exception ex){
                    request.setAttribute("errorMessage","Failed to load details: " + ex.getMessage());
                    request.getRequestDispatcher("profile.jsp").forward(request, response);
                }
                break;
            case "orderPreparing":
                try{
                     List<TransactionPreparingDTO> preparing = transactionServices.GetTransactionPreparing(userId);
                     request.setAttribute("preparing", preparing);
                     request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                catch (Exception ex){
                    request.setAttribute("errorMessage", ex.getMessage());
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;
            default:
                response.sendRedirect("index");
        }
    }
}
