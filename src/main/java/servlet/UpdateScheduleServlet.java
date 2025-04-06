package servlet;

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
 * Servlet for maintenance schedule management.
 * Handles both viewing schedules and updating task status.
 * Used in FR-05 maintenance management.
 *
 * Route: /UpdateScheduleServlet
 * Methods: doGet (fetch schedules), doPost (update status)
 *
 * @author Jiangyu Dai
 */
@WebServlet("/UpdateScheduleServlet")
public class UpdateScheduleServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(UpdateScheduleServlet.class.getName());
    private static final String DATE_TIME = "2025-04-06 17:20:15";
    private static final String USER = "djv00";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            logger.info(String.format("[%s] User %s: Accessing maintenance schedule page", DATE_TIME, USER));
            
            // Get all vehicles for the dropdown
            VehicleDAO vehicleDAO = new VehicleDAOImpl();
            List<VehicleDTO> vehicles = vehicleDAO.getAllVehicles();
            request.setAttribute("vehicles", vehicles);
            
            // Get all maintenance schedules
            MaintenanceDAOImpl dao = new MaintenanceDAOImpl();
            List<MaintenanceScheduleDTO> schedules = dao.getAllSchedules();
            
            logger.info(String.format("[%s] User %s: Retrieved %d maintenance schedules", 
                DATE_TIME, USER, schedules.size()));
                
            request.setAttribute("schedules", schedules);
            
        } catch (Exception e) {
            logger.severe(String.format("[%s] User %s: Error retrieving maintenance schedules: %s", 
                DATE_TIME, USER, e.getMessage()));
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
                
                logger.info(String.format("[%s] User %s: Updated maintenance schedule %d status to %s", 
                    DATE_TIME, USER, scheduleId, status));
                    
                request.setAttribute("message", "Maintenance status updated successfully");
            } catch (Exception e) {
                logger.severe(String.format("[%s] User %s: Error updating maintenance status: %s", 
                    DATE_TIME, USER, e.getMessage()));
                e.printStackTrace();
                request.setAttribute("error", "Failed to update status: " + e.getMessage());
            }
        }

        // After updating, get fresh data and forward to JSP
        doGet(request, response);
    }
}