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

/**
 * Servlet for creating new maintenance tasks.
 * Part of FR-05: Predictive Maintenance.
 *
 * Route: /ScheduleTaskServlet
 * Method: doPost
 * 
 * @author Kai Lu
 */
@WebServlet("/ScheduleTaskServlet")
public class ScheduleTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            MaintenanceDAOImpl dao = new MaintenanceDAOImpl();
            
            MaintenanceScheduleDTO schedule = new MaintenanceScheduleDTO();
            schedule.setVehicleId(Integer.parseInt(request.getParameter("vehicleId")));
            schedule.setTask(request.getParameter("task"));
            schedule.setScheduledDate(Date.valueOf(request.getParameter("scheduledDate")));
            schedule.setStatus("Scheduled");
            
            dao.insertSchedule(schedule);
            request.setAttribute("message", "Maintenance task scheduled successfully");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to schedule task: " + e.getMessage());
        }
        
        // Redirect to maintenance schedule page
        response.sendRedirect("controller?action=updateSchedule");
    }
}