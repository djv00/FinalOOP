package servlet;

import dao.MaintenanceDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for updating maintenance schedule task status.
 * Used in FR-05 schedule management UI.
 *
 * Route: /UpdateScheduleServlet
 * Method: doPost
 * Updates the status field of a maintenance_schedule row.
 *
 * @author Kai Lu
 */
@WebServlet("/UpdateScheduleServlet")
public class UpdateScheduleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String status = request.getParameter("status");

        if (idParam != null && status != null) {
            try {
                int scheduleId = Integer.parseInt(idParam);
                MaintenanceDAOImpl dao = new MaintenanceDAOImpl();
                dao.updateScheduleStatus(scheduleId, status);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("maintenanceSchedule.jsp");
    }
}
