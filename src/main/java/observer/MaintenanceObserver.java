package observer;

import dao.MaintenanceDAO;
import dao.MaintenanceDAOImpl;
import model.MaintenanceAlertDTO;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Observer class that monitors vehicle components for maintenance needs.
 * Simulates real-time component wear monitoring and triggers alerts.
 * Each vehicle has its own observer running in a separate thread.
 *
 * @author Jiangyu Dai
 */
public class MaintenanceObserver implements Runnable {

    private final int vehicleId;
    private final String vehicleType;
    private final String[] components;
    private final MaintenanceDAO dao;
    private final Random random = new Random();
    private volatile boolean running = true; // Flag for graceful shutdown
    
    // Track which components already have active alerts to avoid duplicates
    private final Map<String, Boolean> componentAlerted = new HashMap<>();
    private static final Logger logger = Logger.getLogger(MaintenanceObserver.class.getName());

    /**
     * Creates a maintenance observer for a specific vehicle
     * 
     * @param vehicleId ID of the vehicle to monitor
     * @param vehicleType Type of the vehicle (Bus, Light Rail, Train)
     * @param components Array of component names to monitor
     * @throws Exception if DAO initialization fails
     */
    public MaintenanceObserver(int vehicleId, String vehicleType, String[] components) throws Exception {
        this.vehicleId = vehicleId;
        this.vehicleType = vehicleType;
        this.components = components;
        this.dao = new MaintenanceDAOImpl();
        
        // Initialize all components as not having alerts
        for (String component : components) {
            componentAlerted.put(component, false);
        }
        
        logger.info(String.format("[%s] User %s: Created observer for vehicle %d (%s)", 
            "2025-04-06 17:09:47", "djv00", vehicleId, vehicleType));
    }

    @Override
    public void run() {
        logger.info(String.format("[%s] User %s: Started monitoring vehicle %d", 
            "2025-04-06 17:09:47", "djv00", vehicleId));
            
        while (running) {
            try {
                checkComponentsAndCreateAlerts();
                Thread.sleep(10000); // Check every 10 seconds
            } catch (Exception e) {
                logger.severe(String.format("[%s] User %s: Error monitoring vehicle %d: %s", 
                    "2025-04-06 17:09:47", "djv00", vehicleId, e.getMessage()));
                e.printStackTrace();
                break;
            }
        }
    }
    
    /**
     * Checks all components and creates alerts for those exceeding thresholds
     */
    private void checkComponentsAndCreateAlerts() throws Exception {
        for (String component : components) {
            // Only check components that don't already have active alerts
            if (!componentAlerted.get(component)) {
                // Generate simulated usage hours based on component and vehicle type
                double usage = generateUsageHours(component);
                double threshold = getThreshold(component);
                
                // If usage exceeds threshold, create an alert
                if (usage > threshold) {
                    createAlert(component, usage);
                    componentAlerted.put(component, true);
                }
            }
        }
    }
    
    /**
     * Generates simulated usage hours for a component
     */
    public double generateUsageHours(String component) {
        // Base value plus random variation to simulate real-world wear
        double baseHours = 800;
        double variation = 1000;
        return baseHours + random.nextDouble() * variation;
    }
    
    /**
     * Gets the threshold for a specific component
     */
    private double getThreshold(String component) {
        // Different thresholds based on component type
        switch (component) {
            case "brakes":
                return 1200;
            case "engine":
                return 1500;
            case "catenary":
                return 1600;
            case "pantograph":
                return 1600;
            default:
                return 1300;
        }
    }
    
    /**
     * Creates a maintenance alert in the database
     */
    private void createAlert(String component, double usage) throws Exception {
        MaintenanceAlertDTO alert = new MaintenanceAlertDTO();
        alert.setVehicleId(vehicleId);
        alert.setComponent(component);
        alert.setUsageHours(usage);
        alert.setAlertTime(new Timestamp(System.currentTimeMillis()));
        alert.setResolved(false);
        
        dao.insertAlert(alert);
        
        logger.info(String.format("[%s] User %s: Created alert for vehicle %d, component %s, usage %.1f hours", 
            "2025-04-06 17:09:47", "djv00", vehicleId, component, usage));
    }
    
    /**
     * Stops the observer thread gracefully
     */
    public void stop() {
        running = false;
    }
    
    /**
     * Resets the alert status for a component
     * Called when an alert is resolved
     */
    public void resetComponentAlert(String component) {
        if (componentAlerted.containsKey(component)) {
            componentAlerted.put(component, false);
            logger.info(String.format("[%s] User %s: Reset alert for vehicle %d, component %s", 
                "2025-04-06 17:09:47", "djv00", vehicleId, component));
        }
    }
    
    /**
     * Gets the vehicle ID being monitored
     */
    public int getVehicleId() {
        return vehicleId;
    }
    
    /**
     * Gets the vehicle type being monitored
     */
    public String getVehicleType() {
        return vehicleType;
    }
}