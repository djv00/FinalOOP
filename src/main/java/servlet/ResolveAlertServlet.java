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

/**
 * Servlet that marks a maintenance alert as resolved.
 * Used in FR-05 maintenance alert dashboard.
 *
 * Route: /ResolveAlertServlet
 * Method: doPost
 *
 * Receives alertId and updates alert resolution status.
 * Redirects to alert page after update.
 *
 * @author Kai Lu
 */
@WebServlet("/ResolveAlertServlet")
public class ResolveAlertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("alertId");

        if (idParam != null) {
            try {
                int alertId = Integer.parseInt(idParam);
                MaintenanceDAO dao = new MaintenanceDAOImpl();
                
                // Get alert details before marking as resolved
                MaintenanceAlertDTO alert = dao.getAlertById(alertId);
                
                // Mark alert as resolved in database
                dao.markAlertResolved(alertId);
                
                // Reset component alert flag in observer
                if (alert != null) {
                    MaintenanceMonitorManager.resetComponentAlert(
                        alert.getVehicleId(), alert.getComponent());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("controller?action=maintenanceAlertServlet");
    }
}