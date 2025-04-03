package strategy;

/**
 * Simple Factory for selecting the appropriate fuel/energy strategy
 * based on the unit type: litres, gallons, or kWh.
 *
 * Used by EnergyConsumptionDAO to dynamically choose strategy.
 *
 * @author Kai Lu
 */
public class StrategyFactory {

    /**
     * Returns appropriate strategy based on unit type.
     * @param unit the unit from the fuel_logs table
     * @return a ConsumptionStrategy implementation
     */
    public static ConsumptionStrategy getStrategy(String unit) {
        if (unit == null) return new DieselStrategy();
        return switch (unit.toLowerCase()) {
            case "litres", "gallons" -> new DieselStrategy();
            case "kwh" -> new ElectricStrategy();
            default -> new DieselStrategy(); // default fallback
        };
    }
} 
