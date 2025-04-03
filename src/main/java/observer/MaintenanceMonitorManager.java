package observer;

/**
 * Manager class that initializes maintenance monitoring threads
 * for each vehicle and its components.
 *
 * Acts as the Subject in Observer Pattern for FR-05.
 * Starts observer threads to simulate predictive maintenance detection.
 *
 * @author Kai Lu
 */
public class MaintenanceMonitorManager {

    public static void startMonitoring() {
        try {
            // Vehicle 1: Diesel Bus
            String[] busComponents = {"brakes", "wheels", "axle bearings", "engine"};
            Runnable observer1 = new MaintenanceObserver(1, busComponents);
            new Thread(observer1).start();

            // Vehicle 2: Electric Light Rail
            String[] lrtComponents = {"brakes", "wheels", "axle bearings", "catenary", "pantograph", "circuit breakers"};
            Runnable observer2 = new MaintenanceObserver(2, lrtComponents);
            new Thread(observer2).start();

            // Vehicle 3: Diesel-Electric Train
            String[] trainComponents = {"brakes", "wheels", "axle bearings", "engine"};
            Runnable observer3 = new MaintenanceObserver(3, trainComponents);
            new Thread(observer3).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
