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
 * Supports clean shutdown via 'running' flag.
 *
 * @author Kai Lu
 */
public class GpsSimulator implements Runnable {

    private final int vehicleId;
    private final GpsTrackingDAO gpsDao;
    private final Random random = new Random();

    private double currentLat;
    private double currentLng;
    private volatile boolean running = true; // 支持安全关闭

    public GpsSimulator(int vehicleId, double startLat, double startLng, GpsTrackingDAO dao) {
        this.vehicleId = vehicleId;
        this.currentLat = startLat;
        this.currentLng = startLng;
        this.gpsDao = dao;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                GpsLogDTO log = new GpsLogDTO();
                log.setVehicleId(vehicleId);
                log.setLatitude(currentLat);
                log.setLongitude(currentLng);
                log.setTimestamp(new Timestamp(System.currentTimeMillis()));

                gpsDao.logVehicleLocation(log);

                // 模拟小范围移动
                currentLat += (random.nextDouble() - 0.5) * 0.001;
                currentLng += (random.nextDouble() - 0.5) * 0.001;

                Thread.sleep(5000); // 每5秒更新一次
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
