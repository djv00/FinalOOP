package listener;

import observer.GpsSimulatorManager;
import observer.MaintenanceMonitorManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Listener that starts background simulation threads when the app starts.
 * - Starts GPS and Maintenance Observer threads
 * 
 * @author Kai Lu
 */
@WebListener
public class AppStartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("🚀 Starting simulation threads...");
        GpsSimulatorManager.startAllSimulations();
        MaintenanceMonitorManager.startMonitoring();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("🛑 Shutting down simulation threads...");
        GpsSimulatorManager.stopAllSimulations();
        MaintenanceMonitorManager.stopAllMonitoring();
    }
}
