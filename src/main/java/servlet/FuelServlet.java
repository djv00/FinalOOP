package servlet;

import dao.EnergyConsumptionDAO;
import dao.EnergyConsumptionDAOImpl;
import model.FuelLogDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet to generate fuel/energy usage reports.
 * Used in FR-04 for analyzing consumption data and efficiency.
 *
 * Route: /FuelServlet
 * Method: doGet
 * Forwards to fuelReport.jsp with calculated efficiency data.
 *
 * @author Kai Lu
 */
@WebServlet("/FuelServlet")
public class FuelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            EnergyConsumptionDAO dao = new EnergyConsumptionDAOImpl();
            List<FuelLogDTO> logs = dao.getAllLogs();

            // Wrap logs and their calculated efficiency into map objects
            List<Map<String, Object>> result = new ArrayList<>();
            for (FuelLogDTO log : logs) {
                Map<String, Object> entry = new HashMap<>();
                entry.put("log", log);
                double efficiency = dao.calculateEfficiency(log);
                entry.put("efficiency", efficiency);
                result.add(entry);
            }

            request.setAttribute("logs", result);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to generate report: " + e.getMessage());
        }

        request.getRequestDispatcher("fuelReport.jsp").forward(request, response);
    }
}
