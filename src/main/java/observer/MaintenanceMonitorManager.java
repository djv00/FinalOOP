package observer;

import dao.VehicleDAO;
import dao.VehicleDAOImpl;
import model.VehicleDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Manager for maintenance monitoring system.
 * Creates and controls observers for all vehicles.
 * 
 * @author Jiangyu Dai
 */
public class MaintenanceMonitorManager {

    private static final List<Thread> threads = new ArrayList<>();
    private static final List<MaintenanceObserver> observers = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(MaintenanceMonitorManager.class.getName());

    /**
     * Starts maintenance monitoring for all vehicles
     */
    public static void startMonitoring() {
        try {
            logger.info(String.format("[%s] User %s: Starting maintenance monitoring", 
                "2025-04-06 17:09:47", "djv00"));
            
            // Get all vehicles from database
            VehicleDAO vehicleDAO = new VehicleDAOImpl();
            List<VehicleDTO> vehicles = vehicleDAO.getAllVehicles();
            
            if (vehicles == null || vehicles.isEmpty()) {
                // Fallback to default vehicles if database has no vehicles
                createDefaultObservers();
            } else {
                // Create observers for each vehicle from database
                createObserversFromDatabase(vehicles);
            }
            
        } catch (Exception e) {
            logger.severe(String.format("[%s] User %s: Failed to start monitoring: %s", 
                "2025-04-06 17:09:47", "djv00", e.getMessage()));
            // Fallback to default vehicles if database access fails
            createDefaultObservers();
        }
    }
    
    /**
     * Creates observers for all vehicles from database
     */
    private static void createObserversFromDatabase(List<VehicleDTO> vehicles) {
        try {
            for (VehicleDTO vehicle : vehicles) {
                // Get components based on vehicle type
                String[] components = getComponentsForType(vehicle.getVehicleType());
                
                // Create and start observer
                MaintenanceObserver observer = new MaintenanceObserver(
                    vehicle.getId(), 
                    vehicle.getVehicleType(), 
                    components
                );
                
                startObserver(observer);
            }
            
            logger.info(String.format("[%s] User %s: Started monitoring for %d vehicles", 
                "2025-04-06 17:09:47", "djv00", observers.size()));
        } catch (Exception e) {
            logger.severe(String.format("[%s] User %s: Error creating observers: %s", 
                "2025-04-06 17:09:47", "djv00", e.getMessage()));
        }
    }
    
    /**
     * Creates default observers if database access fails
     */
    private static void createDefaultObservers() {
        try {
            logger.info(String.format("[%s] User %s: Using default vehicles", 
                "2025-04-06 17:09:47", "djv00"));
            
            // Create three default observers for different vehicle types
            MaintenanceObserver bus = new MaintenanceObserver(
                1, "Bus", 
                new String[]{"brakes", "wheels", "axle bearings", "engine"}
            );
            
            MaintenanceObserver lightRail = new MaintenanceObserver(
                2, "Light Rail", 
                new String[]{"brakes", "wheels", "axle bearings", "catenary", "pantograph", "circuit breakers"}
            );
            
            MaintenanceObserver train = new MaintenanceObserver(
                3, "Train", 
                new String[]{"brakes", "wheels", "axle bearings", "engine", "transmission"}
            );
            
            // Start observers
            startObserver(bus);
            startObserver(lightRail);
            startObserver(train);
            
        } catch (Exception e) {
            logger.severe(String.format("[%s] User %s: Failed to create default observers: %s", 
                "2025-04-06 17:09:47", "djv00", e.getMessage()));
        }
    }
    
    /**
     * Gets appropriate components to monitor based on vehicle type
     */
    private static String[] getComponentsForType(String vehicleType) {
        if ("Bus".equals(vehicleType)) {
            return new String[]{"brakes", "wheels", "axle bearings", "engine"};
        } else if ("Light Rail".equals(vehicleType)) {
            return new String[]{"brakes", "wheels", "axle bearings", "catenary", "pantograph", "circuit breakers"};
        } else if ("Train".equals(vehicleType)) {
            return new String[]{"brakes", "wheels", "axle bearings", "engine", "transmission"};
        } else {
            return new String[]{"brakes", "wheels", "engine"}; // Default components
        }
    }
    
    /**
     * Starts an observer in a new thread
     */
    private static void startObserver(MaintenanceObserver observer) {
        observers.add(observer);
        Thread thread = new Thread(observer);
        thread.setName("MaintenanceObserver-" + observer.getVehicleId());
        threads.add(thread);
        thread.start();
    }
    
    /**
     * Stops all monitoring threads
     */
    public static void stopAllMonitoring() {
        for (MaintenanceObserver observer : observers) {
            observer.stop();
        }
        logger.info(String.format("[%s] User %s: All maintenance monitoring stopped", 
            "2025-04-06 17:09:47", "djv00"));
    }
    
    /**
     * Resets component alert for a specific vehicle
     */
    public static void resetComponentAlert(int vehicleId, String component) {
        // Find the observer for this vehicle
        for (MaintenanceObserver observer : observers) {
            if (observer.getVehicleId() == vehicleId) {
                observer.resetComponentAlert(component);
                return;
            }
        }
        
        logger.warning(String.format("[%s] User %s: No observer found for vehicle %d", 
            "2025-04-06 17:09:47", "djv00", vehicleId));
    }
}