package servlet;

import dao.MaintenanceDAOImpl;
import model.MaintenanceScheduleDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * Servlet for maintenance schedule management.
 * Handles both viewing schedules and updating task status.
 * Used in FR-05 maintenance management.
 *
 * Route: /UpdateScheduleServlet
 * Methods: doGet (fetch schedules), doPost (update status)
 *
 * @author Kai Lu
 */
@WebServlet("/UpdateScheduleServlet")
public class UpdateScheduleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            MaintenanceDAOImpl dao = new MaintenanceDAOImpl();
            List<MaintenanceScheduleDTO> schedules = dao.getAllSchedules();
            request.setAttribute("schedules", schedules);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to retrieve maintenance schedules: " + e.getMessage());
        }
        
        request.getRequestDispatcher("maintenanceSchedule.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("scheduleId");
        String status = request.getParameter("status");

        if (idParam != null && status != null) {
            try {
                int scheduleId = Integer.parseInt(idParam);
                MaintenanceDAOImpl dao = new MaintenanceDAOImpl();
                dao.updateScheduleStatus(scheduleId, status);
                request.setAttribute("message", "Maintenance status updated successfully");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Failed to update status: " + e.getMessage());
            }
        }

        // After updating, get fresh data and forward to JSP
        doGet(request, response);
    }
}