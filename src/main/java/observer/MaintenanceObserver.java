package observer;

import dao.MaintenanceDAO;
import dao.MaintenanceDAOImpl;
import model.MaintenanceAlertDTO;

import java.sql.Timestamp;
import java.util.Random;

/**
 * Observer simulation for predictive maintenance alerting.
 * Runs in background and mimics monitoring wear levels.
 *
 * Triggers alerts when component usage exceeds safe threshold.
 *
 * Applies Observer Pattern for FR-05.
 *
 * @author Kai Lu
 */
public class MaintenanceObserver implements Runnable {

    private final int vehicleId;
    private final String[] components;
    private final MaintenanceDAO dao;
    private final Random random = new Random();

    public MaintenanceObserver(int vehicleId, String[] components) throws Exception {
        this.vehicleId = vehicleId;
        this.components = components;
        this.dao = new MaintenanceDAOImpl();
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (String component : components) {
                    double usage = 800 + random.nextDouble() * 1000; // Simulate high usage
                    if (usage > 1200) { // trigger alert if usage too high
                        MaintenanceAlertDTO alert = new MaintenanceAlertDTO();
                        alert.setVehicleId(vehicleId);
                        alert.setComponent(component);
                        alert.setUsageHours(usage);
                        alert.setAlertTime(new Timestamp(System.currentTimeMillis()));
                        alert.setResolved(false);
                        dao.insertAlert(alert);
                    }
                }
                Thread.sleep(10000); // simulate periodic checking every 10 seconds
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
