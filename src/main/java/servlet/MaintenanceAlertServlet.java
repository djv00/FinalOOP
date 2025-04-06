package servlet;

import dao.MaintenanceDAO;
import dao.MaintenanceDAOImpl;
import model.MaintenanceAlertDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet that retrieves unresolved maintenance alerts for all vehicles.
 * Applies DAO + Observer Pattern for FR-05.
 *
 * URL: /MaintenanceAlertServlet
 *
 * @author Kai Lu
 */
@WebServlet("/MaintenanceAlertServlet")
public class MaintenanceAlertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            MaintenanceDAO dao = new MaintenanceDAOImpl();
            List<MaintenanceAlertDTO> allAlerts = new ArrayList<>();

            // Assuming vehicle IDs are 1, 2, 3
            for (int vehicleId = 1; vehicleId <= 3; vehicleId++) {
                List<MaintenanceAlertDTO> alerts = dao.getUnresolvedAlertsByVehicle(vehicleId);
                allAlerts.addAll(alerts);
            }

            request.setAttribute("alerts", allAlerts);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to retrieve alerts: " + e.getMessage());
            request.setAttribute("alerts", new ArrayList<MaintenanceAlertDTO>());
        }

        request.getRequestDispatcher("maintenanceAlert.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        String action = request.getParameter("action");
        
        if ("resolve".equals(action)) {
            try {
                int alertId = Integer.parseInt(request.getParameter("alertId"));
                MaintenanceDAO dao = new MaintenanceDAOImpl();
                dao.markAlertResolved(alertId);
                request.setAttribute("message", "Alert marked as resolved successfully");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Failed to resolve alert: " + e.getMessage());
            }
        }
        
        // Redirect to doGet to refresh the alerts list
        doGet(request, response);
    }
}