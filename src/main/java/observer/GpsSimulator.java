package observer;

import dao.GpsTrackingDAO;
import model.GpsLogDTO;

import java.sql.Timestamp;
import java.util.Random;

/**
 * Background simulator that mimics real-time GPS location updates
 * by generating pseudo-random positions for a vehicle.
 *
 * Implements Observer Pattern for FR-03.
 * Each vehicle has its own simulator instance.
 *
 * Assumes stations are defined externally.
 *
 * @author Kai Lu
 */
public class GpsSimulator implements Runnable {

    private final int vehicleId;
    private final GpsTrackingDAO gpsDao;
    private final Random random = new Random();

    private double currentLat;
    private double currentLng;

    public GpsSimulator(int vehicleId, double startLat, double startLng, GpsTrackingDAO dao) {
        this.vehicleId = vehicleId;
        this.currentLat = startLat;
        this.currentLng = startLng;
        this.gpsDao = dao;
    }

    @Override
    public void run() {
        while (true) {
            try {
                GpsLogDTO log = new GpsLogDTO();
                log.setVehicleId(vehicleId);
                log.setLatitude(currentLat);
                log.setLongitude(currentLng);
                log.setTimestamp(new Timestamp(System.currentTimeMillis()));

                gpsDao.logVehicleLocation(log);

                // Simulate slight movement
                currentLat += (random.nextDouble() - 0.5) * 0.001;
                currentLng += (random.nextDouble() - 0.5) * 0.001;

                Thread.sleep(5000); // wait 5 seconds between updates
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
