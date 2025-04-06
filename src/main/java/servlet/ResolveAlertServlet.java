package servlet;

import dao.MaintenanceDAO;
import dao.MaintenanceDAOImpl;
import model.MaintenanceAlertDTO;
import observer.MaintenanceMonitorManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Servlet for resolving maintenance alerts.
 * Part of FR-05 (Predictive Maintenance)
 * 
 * @author Jiangyu Dai
 */
@WebServlet("/ResolveAlertServlet")
public class ResolveAlertServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ResolveAlertServlet.class.getName());
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("alertId");

        if (idParam != null) {
            try {
                int alertId = Integer.parseInt(idParam);
                MaintenanceDAO dao = new MaintenanceDAOImpl();
                
                // Get alert details to reset component status
                MaintenanceAlertDTO alert = dao.getAlertById(alertId);
                
                // Mark alert as resolved in database
                dao.markAlertResolved(alertId);
                
                // Reset component alert flag to allow future alerts
                if (alert != null) {
                    MaintenanceMonitorManager.resetComponentAlert(
                        alert.getVehicleId(), alert.getComponent());
                    logger.info("Reset alert tracking for vehicle " + alert.getVehicleId() + 
                                ", component " + alert.getComponent());
                }
                
                request.setAttribute("message", "Alert marked as resolved successfully!");
                
            } catch (Exception e) {
                logger.severe("Error resolving alert: " + e.getMessage());
                e.printStackTrace();
                request.setAttribute("error", "Failed to resolve alert: " + e.getMessage());
            }
        } else {
            logger.warning("No alert ID provided in resolve request");
            request.setAttribute("error", "No alert ID provided");
        }

        response.sendRedirect("controller?action=maintenanceAlertServlet");
    }
}