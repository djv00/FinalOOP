package model;

/**
 * Data Transfer Object representing a Vehicle.
 * Maps to the 'vehicles' table in the database.
 * Uses Builder Pattern for elegant object construction.
 *
 * Fields: id, vehicleNumber, vehicleType, fuelType, consumptionRate, maxPassengers, currentRoute
 *
 * Example:
 *   VehicleDTO vehicle = new VehicleDTO.Builder()
 *     .withVehicleNumber("BUS001")
 *     .withVehicleType("Diesel Bus")
 *     .withFuelType("Diesel")
 *     .withConsumptionRate(30.5)
 *     .withMaxPassengers(50)
 *     .withCurrentRoute("Route A")
 *     .build();
 *
 * @author Kai Lu
 */
public class VehicleDTO {
    private int id;
    private String vehicleNumber;
    private String vehicleType;
    private String fuelType;
    private double consumptionRate;
    private int maxPassengers;
    private String currentRoute;

    private VehicleDTO() {}

    public static class Builder {
        private final VehicleDTO vehicle = new VehicleDTO();

        public Builder withId(int id) {
            vehicle.id = id;
            return this;
        }

        public Builder withVehicleNumber(String vehicleNumber) {
            vehicle.vehicleNumber = vehicleNumber;
            return this;
        }

        public Builder withVehicleType(String vehicleType) {
            vehicle.vehicleType = vehicleType;
            return this;
        }

        public Builder withFuelType(String fuelType) {
            vehicle.fuelType = fuelType;
            return this;
        }

        public Builder withConsumptionRate(double rate) {
            vehicle.consumptionRate = rate;
            return this;
        }

        public Builder withMaxPassengers(int max) {
            vehicle.maxPassengers = max;
            return this;
        }

        public Builder withCurrentRoute(String route) {
            vehicle.currentRoute = route;
            return this;
        }

        public VehicleDTO build() {
            return vehicle;
        }
    }

    public int getId() { return id; }
    public String getVehicleNumber() { return vehicleNumber; }
    public String getVehicleType() { return vehicleType; }
    public String getFuelType() { return fuelType; }
    public double getConsumptionRate() { return consumptionRate; }
    public int getMaxPassengers() { return maxPassengers; }
    public String getCurrentRoute() { return currentRoute; }

    public void setId(int id) { this.id = id; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
    public void setFuelType(String fuelType) { this.fuelType = fuelType; }
    public void setConsumptionRate(double consumptionRate) { this.consumptionRate = consumptionRate; }
    public void setMaxPassengers(int maxPassengers) { this.maxPassengers = maxPassengers; }
    public void setCurrentRoute(String currentRoute) { this.currentRoute = currentRoute; }
}
