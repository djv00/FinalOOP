package observer;

import dao.GpsTrackingDAO;
import dao.GpsTrackingDAOImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages GPS simulation threads.
 * Supports start and stop for clean shutdown.
 * Applies Observer Pattern for FR-03: Real-time GPS tracking.
 * 
 * @author Kai Lu
 */
public class GpsSimulatorManager {

    private static final List<GpsSimulator> simulators = new ArrayList<>();
    private static final List<Thread> threads = new ArrayList<>();

    /**
     * Starts all GPS simulation threads for predefined vehicles.
     */
    public static void startAllSimulations() {
        try {
            GpsTrackingDAO gpsDao = new GpsTrackingDAOImpl();

            GpsSimulator sim1 = new GpsSimulator(1, 45.4215, -75.6972, gpsDao);
            GpsSimulator sim2 = new GpsSimulator(2, 45.4240, -75.6900, gpsDao);
            GpsSimulator sim3 = new GpsSimulator(3, 45.4260, -75.6880, gpsDao);

            simulators.add(sim1);
            simulators.add(sim2);
            simulators.add(sim3);

            for (GpsSimulator sim : simulators) {
                Thread thread = new Thread(sim);
                threads.add(thread);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops all running GPS simulation threads gracefully.
     */
    public static void stopAllSimulations() {
        for (GpsSimulator sim : simulators) {
            sim.stop();
        }
    }
}
