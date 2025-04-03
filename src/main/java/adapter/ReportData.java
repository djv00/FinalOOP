
package adapter;

/**
 * Unified report data format for display.
 * Adapter Pattern target interface for FR-06.
 *
 * Allows both performance and cost reports to conform to one structure.
 *
 * Fields include vehicle ID, label (metric name), value, and time or context.
 *
 * @author Kai Lu
 */
public class ReportData {
    private int vehicleId;
    private String label;
    private String value;
    private String context;

    public ReportData(int vehicleId, String label, String value, String context) {
        this.vehicleId = vehicleId;
        this.label = label;
        this.value = value;
        this.context = context;
    }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }

    public String getContext() { return context; }
    public void setContext(String context) { this.context = context; }
}
