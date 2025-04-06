package servlet;

import dao.MaintenanceDAO;
import dao.MaintenanceDAOImpl;
import dao.VehicleDAO;
import dao.VehicleDAOImpl;
import model.MaintenanceScheduleDTO;
import model.VehicleDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Servlet for scheduling maintenance tasks.
 * Part of FR-05 (Predictive Maintenance)
 * 
 * @author Jiangyu Dai
 */
@WebServlet("/ScheduleTaskServlet")
public class ScheduleTaskServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(ScheduleTaskServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // Get all vehicles for the dropdown
            VehicleDAO vehicleDAO = new VehicleDAOImpl();
            List<VehicleDTO> vehicles = vehicleDAO.getAllVehicles();
            request.setAttribute("vehicles", vehicles);
            
            // Get all schedules
            MaintenanceDAO maintenanceDAO = new MaintenanceDAOImpl();
            List<MaintenanceScheduleDTO> schedules = maintenanceDAO.getAllSchedules();
            request.setAttribute("schedules", schedules);
            
        } catch (Exception e) {
            logger.severe("Error preparing maintenance schedule page: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Failed to load data: " + e.getMessage());
        }
        
        request.getRequestDispatcher("maintenanceSchedule.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        try {
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            String task = request.getParameter("task");
            Date scheduledDate = Date.valueOf(request.getParameter("scheduledDate"));
            
            MaintenanceScheduleDTO schedule = new MaintenanceScheduleDTO();
            schedule.setVehicleId(vehicleId);
            schedule.setTask(task);
            schedule.setScheduledDate(scheduledDate);
            schedule.setStatus("Scheduled");
            
            MaintenanceDAO dao = new MaintenanceDAOImpl();
            dao.insertSchedule(schedule);
            
            request.setAttribute("message", "Maintenance task scheduled successfully!");
            
        } catch (Exception e) {
            logger.severe("Error scheduling maintenance: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Failed to schedule maintenance: " + e.getMessage());
        }
        
        // Redirect to get method to refresh the page
        doGet(request, response);
    }
}