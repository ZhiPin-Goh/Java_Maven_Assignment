package Controller;

import Services.PointServices;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/pointLogs")
public class PointLogs extends HttpServlet {
    PointServices pointServices = new PointServices();
}
