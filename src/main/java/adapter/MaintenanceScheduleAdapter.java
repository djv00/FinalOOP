package adapter;

import model.MaintenanceScheduleDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter that converts MaintenanceScheduleDTO into ReportData format.
 * Adds maintenance tasks to unified report dashboard for FR-06.
 *
 * @author Kai Lu
 */
public class MaintenanceScheduleAdapter {

    /**
     * Converts maintenance schedule data into unified report format.
     * @param list List of MaintenanceScheduleDTO
     * @return list of ReportData
     */
    public static List<ReportData> adapt(List<MaintenanceScheduleDTO> list) {
        List<ReportData> result = new ArrayList<>();
        for (MaintenanceScheduleDTO s : list) {
            result.add(new ReportData(
                s.getVehicleId(),
                "Maintenance Task",
                s.getTask(),
                s.getScheduledDate() + " - " + s.getStatus()
            ));
        }
        return result;
    }
}
