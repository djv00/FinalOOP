package adapter;

import model.MaintenanceAlertDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter that converts MaintenanceAlertDTO into ReportData format.
 * Applies Adapter Pattern for FR-06 maintenance reporting.
 *
 * @author Kai Lu
 */
public class MaintenanceAlertAdapter {

    /**
     * Converts maintenance alerts into unified report format.
     * @param alerts List of MaintenanceAlertDTO
     * @return list of ReportData
     */
    public static List<ReportData> adapt(List<MaintenanceAlertDTO> alerts) {
        List<ReportData> result = new ArrayList<>();
        for (MaintenanceAlertDTO alert : alerts) {
            result.add(new ReportData(
                alert.getVehicleId(),
                "Maintenance Alert",
                alert.getComponent() + " (" + String.format("%.1f", alert.getUsageHours()) + " hrs)",
                "Alert Time: " + alert.getAlertTime() + (alert.isResolved() ? " - Resolved" : " - Unresolved")
            ));
        }
        return result;
    }
}