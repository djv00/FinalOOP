package dao;

import model.CostReportDTO;
import model.PerformanceLogDTO;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of ReportDAO for FR-06 Reporting & Analytics.
 * Queries performance_logs and cost_reports for dashboards.
 *
 * @author Kai Lu
 */
public class ReportDAOImpl implements ReportDAO {

    private final Connection conn;

    public ReportDAOImpl() throws Exception {
        this.conn = DBConnection.getConnection();
    }

    @Override
    public List<PerformanceLogDTO> getAllPerformanceLogs() throws Exception {
        List<PerformanceLogDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM performance_logs ORDER BY actual_arrival DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PerformanceLogDTO p = new PerformanceLogDTO();
                p.setId(rs.getInt("id"));
                p.setUserId(rs.getInt("user_id"));
                p.setVehicleId(rs.getInt("vehicle_id"));
                p.setScheduledArrival(rs.getTimestamp("scheduled_arrival"));
                p.setActualArrival(rs.getTimestamp("actual_arrival"));
                p.setEfficiencyScore(rs.getDouble("efficiency_score"));
                list.add(p);
            }
        }
        return list;
    }

    @Override
    public List<CostReportDTO> getAllCostReports() throws Exception {
        List<CostReportDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM cost_reports ORDER BY month_year DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CostReportDTO c = new CostReportDTO();
                c.setId(rs.getInt("id"));
                c.setVehicleId(rs.getInt("vehicle_id"));
                c.setFuelCost(rs.getDouble("fuel_cost"));
                c.setMaintenanceCost(rs.getDouble("maintenance_cost"));
                c.setMonthYear(rs.getString("month_year"));
                list.add(c);
            }
        }
        return list;
    }
} 
