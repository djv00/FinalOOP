package servlet;

import dao.MaintenanceDAO;
import dao.MaintenanceDAOImpl;

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
                dao.markAlertResolved(alertId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("maintenanceAlert.jsp");
    }
}
