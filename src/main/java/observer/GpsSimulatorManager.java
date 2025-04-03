package observer;

import dao.GpsTrackingDAO;
import dao.GpsTrackingDAOImpl;

/**
 * Manages background threads simulating GPS updates for all vehicles.
 * Used to start one simulator per vehicle with predefined starting coordinates.
 *
 * Call GpsSimulatorManager.startAllSimulations() to begin.
 *
 * Used in FR-03 to simulate real-time GPS tracking.
 *
 * @author Kai Lu
 */
public class GpsSimulatorManager {

    public static void startAllSimulations() {
        try {
            GpsTrackingDAO gpsDao = new GpsTrackingDAOImpl();

            // Vehicle 1: Diesel Bus
            Runnable sim1 = new GpsSimulator(1, 45.4215, -75.6972, gpsDao);
            new Thread(sim1).start();

            // Vehicle 2: Electric Light Rail
            Runnable sim2 = new GpsSimulator(2, 45.4240, -75.6900, gpsDao);
            new Thread(sim2).start();

            // Vehicle 3: Diesel-Electric Train
            Runnable sim3 = new GpsSimulator(3, 45.4260, -75.6880, gpsDao);
            new Thread(sim3).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
