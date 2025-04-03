package model;

/**
 * DTO for monthly fuel and maintenance cost reports.
 * Maps to the 'cost_reports' table.
 *
 * Fields: id, vehicleId, fuelCost, maintenanceCost, monthYear
 * Used in FR-06 Reporting & Analytics for cost dashboard.
 *
 * @author Kai Lu
 */
public class CostReportDTO {
    private int id;
    private int vehicleId;
    private double fuelCost;
    private double maintenanceCost;
    private String monthYear;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public double getFuelCost() { return fuelCost; }
    public void setFuelCost(double fuelCost) { this.fuelCost = fuelCost; }

    public double getMaintenanceCost() { return maintenanceCost; }
    public void setMaintenanceCost(double maintenanceCost) { this.maintenanceCost = maintenanceCost; }

    public String getMonthYear() { return monthYear; }
    public void setMonthYear(String monthYear) { this.monthYear = monthYear; }
}
