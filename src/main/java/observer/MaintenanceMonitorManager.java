package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages maintenance observer threads for all vehicles.
 * Applies Observer Pattern for FR-05: Predictive Maintenance.
 * Supports start and graceful stop for memory-safe shutdown.
 * 
 * @author Kai Lu
 */
public class MaintenanceMonitorManager {

    private static final List<Thread> threads = new ArrayList<>();
    private static final List<MaintenanceObserver> observers = new ArrayList<>();

    /**
     * Starts maintenance monitoring threads for predefined vehicles.
     */
    public static void startMonitoring() {
        try {
            String[] busComponents = {"brakes", "wheels", "axle bearings", "engine"};
            String[] lrtComponents = {"brakes", "wheels", "axle bearings", "catenary", "pantograph", "circuit breakers"};
            String[] trainComponents = {"brakes", "wheels", "axle bearings", "engine"};

            MaintenanceObserver observer1 = new MaintenanceObserver(1, busComponents);
            MaintenanceObserver observer2 = new MaintenanceObserver(2, lrtComponents);
            MaintenanceObserver observer3 = new MaintenanceObserver(3, trainComponents);

            observers.add(observer1);
            observers.add(observer2);
            observers.add(observer3);

            threads.add(new Thread(observer1));
            threads.add(new Thread(observer2));
            threads.add(new Thread(observer3));

            for (Thread thread : threads) {
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Signals all monitoring threads to stop gracefully.
     */
    public static void stopAllMonitoring() {
        for (MaintenanceObserver observer : observers) {
            observer.stop();
        }
    }
    
     /**
     * Resets the component alert flag in the appropriate observer.
     * Called when an alert is resolved.
     * 
     * @param vehicleId ID of the vehicle
     * @param component Name of the component
     */
    public static void resetComponentAlert(int vehicleId, String component) {
        // vehicleId is 1-based, observers list is 0-based
        int index = vehicleId - 1;
        if (index >= 0 && index < observers.size()) {
            observers.get(index).resetComponentAlert(component);
        }
    }
}
