package servlet;

import dao.MaintenanceDAO;
import dao.MaintenanceDAOImpl;
import model.MaintenanceScheduleDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * Servlet that handles maintenance scheduling operations.
 * Supports FR-05: Predictive Maintenance.
 * 
 * URL: /MaintenanceScheduleServlet
 *
 * @author Kai Lu
 */
@WebServlet("/MaintenanceScheduleServlet")
public class MaintenanceScheduleServlet extends HttpServlet {

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
        
        // Determine action from request parameter
        String action = request.getParameter("action");
        
        try {
            MaintenanceDAOImpl dao = new MaintenanceDAOImpl();
            
            if ("schedule".equals(action)) {
                // Create new maintenance schedule
                MaintenanceScheduleDTO schedule = new MaintenanceScheduleDTO();
                schedule.setVehicleId(Integer.parseInt(request.getParameter("vehicleId")));
                schedule.setTask(request.getParameter("task"));
                schedule.setScheduledDate(Date.valueOf(request.getParameter("scheduledDate")));
                schedule.setStatus("Scheduled");
                
                dao.insertSchedule(schedule);
                request.setAttribute("message", "Maintenance task scheduled successfully");
            } 
            else if ("update".equals(action)) {
                // Update existing maintenance schedule status
                int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
                String status = request.getParameter("status");
                
                dao.updateScheduleStatus(scheduleId, status);
                request.setAttribute("message", "Maintenance status updated successfully");
            }
            
            // Refresh the schedules list
            List<MaintenanceScheduleDTO> schedules = dao.getAllSchedules();
            request.setAttribute("schedules", schedules);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Operation failed: " + e.getMessage());
        }
        
        request.getRequestDispatcher("maintenanceSchedule.jsp").forward(request, response);
    }
}