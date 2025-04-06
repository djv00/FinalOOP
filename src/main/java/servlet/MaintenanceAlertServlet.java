package servlet;

import dao.MaintenanceDAO;
import dao.MaintenanceDAOImpl;
import dao.VehicleDAO;
import dao.VehicleDAOImpl;
import model.MaintenanceAlertDTO;
import model.VehicleDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Servlet for displaying maintenance alerts with filtering options.
 * Part of FR-05 (Predictive Maintenance)
 * 
 * @author Jiangyu Dai
 */
@WebServlet("/MaintenanceAlertServlet")
public class MaintenanceAlertServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(MaintenanceAlertServlet.class.getName());
    private static final String DATE_TIME = "2025-04-06 16:46:27";
    private static final String USER = "djv00";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            logger.info(String.format("[%s] User %s: Accessing maintenance alerts", DATE_TIME, USER));
            
            // Get filters from request parameters
            String vehicleIdParam = request.getParameter("vehicleId");
            String typeParam = request.getParameter("type");
            String resolvedParam = request.getParameter("resolved");
            
            MaintenanceDAOImpl maintenanceDAO = new MaintenanceDAOImpl();
            VehicleDAO vehicleDAO = new VehicleDAOImpl();
            List<MaintenanceAlertDTO> allAlerts = new ArrayList<>();
            
            // Get all vehicles
            List<VehicleDTO> vehicles = vehicleDAO.getAllVehicles();
            request.setAttribute("vehicles", vehicles);
            
            // Extract all vehicle types for the filter dropdown
            Set<String> vehicleTypes = new HashSet<>();
            for (VehicleDTO vehicle : vehicles) {
                if (vehicle.getVehicleType() != null && !vehicle.getVehicleType().isEmpty()) {
                    vehicleTypes.add(vehicle.getVehicleType());
                }
            }
            request.setAttribute("vehicleTypes", vehicleTypes);
            
            // Filter by vehicle ID if specified
            if (vehicleIdParam != null && !vehicleIdParam.isEmpty()) {
                try {
                    int vehicleId = Integer.parseInt(vehicleIdParam);
                    
                    // Get alerts for this vehicle with resolved filter
                    if ("true".equals(resolvedParam)) {
                        allAlerts = maintenanceDAO.getAllAlertsByVehicle(vehicleId);
                    } else {
                        allAlerts = maintenanceDAO.getUnresolvedAlertsByVehicle(vehicleId);
                    }
                    
                    request.setAttribute("selectedVehicleId", vehicleId);
                    
                } catch (NumberFormatException e) {
                    logger.warning(String.format("[%s] User %s: Invalid vehicle ID: %s", 
                        DATE_TIME, USER, vehicleIdParam));
                }
            } 
            // Filter by vehicle type if specified
            else if (typeParam != null && !typeParam.isEmpty()) {
                for (VehicleDTO vehicle : vehicles) {
                    if (typeParam.equals(vehicle.getVehicleType())) {
                        // Get alerts for this vehicle with resolved filter
                        if ("true".equals(resolvedParam)) {
                            allAlerts.addAll(maintenanceDAO.getAllAlertsByVehicle(vehicle.getId()));
                        } else {
                            allAlerts.addAll(maintenanceDAO.getUnresolvedAlertsByVehicle(vehicle.getId()));
                        }
                    }
                }
                request.setAttribute("selectedType", typeParam);
            } 
            // No specific filters, get all alerts
            else {
                for (VehicleDTO vehicle : vehicles) {
                    // Get alerts for all vehicles with resolved filter
                    if ("true".equals(resolvedParam)) {
                        allAlerts.addAll(maintenanceDAO.getAllAlertsByVehicle(vehicle.getId()));
                    } else {
                        allAlerts.addAll(maintenanceDAO.getUnresolvedAlertsByVehicle(vehicle.getId()));
                    }
                }
            }
            
            // Set resolved status in request for filter form
            request.setAttribute("showResolved", "true".equals(resolvedParam));
            
            // Add vehicle type information to each alert
            for (MaintenanceAlertDTO alert : allAlerts) {
                for (VehicleDTO vehicle : vehicles) {
                    if (vehicle.getId() == alert.getVehicleId()) {
                        alert.setVehicleType(vehicle.getVehicleType());
                        break;
                    }
                }
            }
            
            request.setAttribute("alerts", allAlerts);
            
            logger.info(String.format("[%s] User %s: Retrieved %d alerts", 
                DATE_TIME, USER, allAlerts.size()));
            
        } catch (Exception e) {
            logger.severe(String.format("[%s] User %s: Error retrieving alerts: %s", 
                DATE_TIME, USER, e.getMessage()));
            e.printStackTrace();
            request.setAttribute("error", "Failed to retrieve alerts: " + e.getMessage());
            request.setAttribute("alerts", new ArrayList<MaintenanceAlertDTO>());
        }

        request.getRequestDispatcher("maintenanceAlert.jsp").forward(request, response);
    }
}