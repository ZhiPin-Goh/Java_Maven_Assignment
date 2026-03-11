package Controller;

import Services.CheckOutServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/checkOut")
public class CheckOut extends HttpServlet {
    CheckOutServices checkOutServices = new CheckOutServices();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("loggedInUserID");
        if (userId == null){
            response.sendRedirect("sign-up.jsp");
            return;
        }
        try{
            String[] selectCartIDs = request.getParameterValues("cardIDs");
            boolean usepoints = Boolean.getBoolean(request.getParameter("usepoints"));
            if(selectCartIDs == null || selectCartIDs.length == 0){
                session.setAttribute("error", "Please select at least one item to checkout.");
                response.sendRedirect("cart");
                return;
            }

            List<Integer> idList = new ArrayList<>();
            for(String idStr: selectCartIDs){
                idList.add(Integer.parseInt(idStr));
            }

            String result = checkOutServices.CheckOut(idList, usepoints);
            session.setAttribute("successMessage", "Checkout Successful!");
            response.sendRedirect("payment-success");
        }
        catch (Exception ex){

            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("cart.jsp").forward(request,response);
        }
    }
}
