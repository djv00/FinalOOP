/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapter;

import model.CostReportDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter that converts CostReportDTO into ReportData format.
 * Applies Adapter Pattern for FR-06 cost reporting.
 *
 * @author Kai Lu
 */
public class CostReportAdapter {

    /**
     * Converts cost reports into unified format.
     * @param reports List of CostReportDTO
     * @return list of ReportData
     */
    public static List<ReportData> adapt(List<CostReportDTO> reports) {
        List<ReportData> result = new ArrayList<>();
        for (CostReportDTO r : reports) {
            result.add(new ReportData(
                r.getVehicleId(),
                "Fuel Cost",
                String.format("$%.2f", r.getFuelCost()),
                r.getMonthYear()
            ));

            result.add(new ReportData(
                r.getVehicleId(),
                "Maintenance Cost",
                String.format("$%.2f", r.getMaintenanceCost()),
                r.getMonthYear()
            ));
        }
        return result;
    }
}
