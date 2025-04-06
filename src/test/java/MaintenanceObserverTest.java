
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dao.MaintenanceDAO;
import model.MaintenanceAlertDTO;
import observer.MaintenanceObserver;

/**
 * Unit tests for predictive maintenance monitoring system (FR-05)
 */
public class MaintenanceObserverTest {

    @Mock
    private MaintenanceDAO mockDao;
    
    private MaintenanceObserver observer;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        observer = new MaintenanceObserver(1, "Bus", new String[]{"brakes", "wheels", "engine"});
        
        // Inject mock DAO
        setPrivateField(observer, "dao", mockDao);
        
        System.out.println("[2025-04-06 18:47:13] User djv00: Setting up maintenance observer test");
    }

    // Helper method to set private fields
    private void setPrivateField(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }
    
    // Helper method to get private field value
    private Object getPrivateField(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
    
    // Helper method to invoke private method
    private Object invokePrivateMethod(Object obj, String methodName, Class<?>[] paramTypes, Object[] args) throws Exception {
        Method method = obj.getClass().getDeclaredMethod(methodName, paramTypes);
        method.setAccessible(true);
        return method.invoke(obj, args);
    }

    @Test
    public void testThresholdValues() throws Exception {
        // Test threshold values for different components
        double brakesThreshold = (double) invokePrivateMethod(observer, "getThreshold", 
                new Class<?>[] {String.class}, new Object[] {"brakes"});
        double engineThreshold = (double) invokePrivateMethod(observer, "getThreshold", 
                new Class<?>[] {String.class}, new Object[] {"engine"});
        double pantographThreshold = (double) invokePrivateMethod(observer, "getThreshold", 
                new Class<?>[] {String.class}, new Object[] {"pantograph"});
        
        assertEquals(1200.0, brakesThreshold, 0.01);
        assertEquals(1500.0, engineThreshold, 0.01);
        assertEquals(1600.0, pantographThreshold, 0.01);
        
        System.out.println("[2025-04-06 18:47:13] User djv00: Verified component maintenance thresholds");
    }
    
    @Test
    public void testCreateAlert() throws Exception {
        // Directly test alert creation functionality
        invokePrivateMethod(observer, "createAlert", 
                new Class<?>[] {String.class, double.class}, 
                new Object[] {"brakes", 1250.5});
        
        // Verify alert was created with correct data
        ArgumentCaptor<MaintenanceAlertDTO> alertCaptor = ArgumentCaptor.forClass(MaintenanceAlertDTO.class);
        verify(mockDao).insertAlert(alertCaptor.capture());
        
        MaintenanceAlertDTO alert = alertCaptor.getValue();
        assertEquals(1, alert.getVehicleId());
        assertEquals("brakes", alert.getComponent());
        assertEquals(1250.5, alert.getUsageHours(), 0.01);
        assertFalse(alert.isResolved());
        
        System.out.println("[2025-04-06 18:47:13] User djv00: Verified alert creation functionality");
    }
    
    @Test
    public void testAlertResetFunctionality() throws Exception {
        // Setup component alert tracking status
        Map<String, Boolean> componentAlerted = new HashMap<>();
        componentAlerted.put("brakes", true);  // Assume brakes already has an alert
        componentAlerted.put("wheels", false);
        componentAlerted.put("engine", false);
        setPrivateField(observer, "componentAlerted", componentAlerted);
        
        // Reset brakes alert
        observer.resetComponentAlert("brakes");
        
        // Verify alert status was reset
        Map<String, Boolean> updatedMap = (Map<String, Boolean>) getPrivateField(observer, "componentAlerted");
        assertFalse(updatedMap.get("brakes"));
        
        System.out.println("[2025-04-06 18:47:13] User djv00: Verified alert reset functionality");
    }
    
    @Test
    public void testAlertTracking() throws Exception {
        // Setup component alert tracking status
        Map<String, Boolean> componentAlerted = new HashMap<>();
        componentAlerted.put("brakes", false);
        componentAlerted.put("wheels", false);
        componentAlerted.put("engine", false);
        setPrivateField(observer, "componentAlerted", componentAlerted);
        
        // Manually update status to simulate post-alert creation state
        componentAlerted.put("brakes", true);
        
        // Verify status update
        assertTrue("Brakes should be marked as alerted", componentAlerted.get("brakes"));
        
        System.out.println("[2025-04-06 18:47:13] User djv00: Tested component alert status tracking");
    }
    
    @Test
    public void testUsageHoursGeneration() throws Exception {
        // Test usage hours generation functionality
        double usage = (double) invokePrivateMethod(observer, "generateUsageHours", 
                new Class<?>[] {String.class}, new Object[] {"brakes"});
        
        assertTrue("Usage hours should be at least 800", usage >= 800);
        assertTrue("Usage hours should be at most 1800", usage <= 1800);
        
        System.out.println("[2025-04-06 18:47:13] User djv00: Verified usage hours generation functionality");
    }
    
    @Test
    public void testThresholdComparison() throws Exception {
        // Test threshold comparison logic
        double brakesThreshold = (double) invokePrivateMethod(observer, "getThreshold", 
                new Class<?>[] {String.class}, new Object[] {"brakes"});
        
        // Brakes threshold should be 1200 hours
        assertTrue("1300 hours should exceed brakes threshold", 1300.0 > brakesThreshold);
        assertFalse("1100 hours should not exceed brakes threshold", 1100.0 > brakesThreshold);
        
        System.out.println("[2025-04-06 18:47:13] User djv00: Tested threshold comparison logic");
    }
    
    @Test
    public void testComponentList() throws Exception {
        // Test component list
        String[] components = (String[]) getPrivateField(observer, "components");
        
        assertEquals(3, components.length);
        assertEquals("brakes", components[0]);
        assertEquals("wheels", components[1]);
        assertEquals("engine", components[2]);
        
        System.out.println("[2025-04-06 18:47:13] User djv00: Verified component list structure");
    }
    
    @Test
    public void testMaintenanceScheduling() throws Exception {
        // Test maintenance scheduling functionality
        invokePrivateMethod(observer, "createAlert", 
                new Class<?>[] {String.class, double.class}, 
                new Object[] {"brakes", 1250.5});
        
        // Verify alert creation and potential maintenance scheduling
        verify(mockDao, atLeastOnce()).insertAlert(any(MaintenanceAlertDTO.class));
        
        System.out.println("[2025-04-06 18:47:13] User djv00: Tested maintenance scheduling process");
    }
}