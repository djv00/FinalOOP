
package adapter;

import model.PerformanceLogDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter that converts PerformanceLogDTO into ReportData format.
 * Applies Adapter Pattern for FR-06 performance dashboard.
 *
 * @author Kai Lu
 */
public class PerformanceReportAdapter {

    /**
     * Converts a list of performance logs to unified report data.
     * @param logs List of PerformanceLogDTO
     * @return list of ReportData
     */
    public static List<ReportData> adapt(List<PerformanceLogDTO> logs) {
        List<ReportData> result = new ArrayList<>();
        for (PerformanceLogDTO log : logs) {
            String label = "Operator Efficiency";
            String value = String.format("%.2f", log.getEfficiencyScore());
            String context = "User " + log.getUserId() + " @ " + log.getActualArrival();
            result.add(new ReportData(log.getVehicleId(), label, value, context));
        }
        return result;
    }
}
