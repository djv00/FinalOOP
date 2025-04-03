package dao;

import model.CostReportDTO;
import model.PerformanceLogDTO;

import java.util.List;

/**
 * DAO interface for FR-06 Reporting & Analytics.
 * Handles access to performance_logs and cost_reports tables.
 *
 * Includes methods to support dashboard data extraction.
 *
 * @author Kai Lu
 */
public interface ReportDAO {

    /**
     * Get all operator performance logs.
     * @return list of performance logs
     * @throws Exception on failure
     */
    List<PerformanceLogDTO> getAllPerformanceLogs() throws Exception;

    /**
     * Get all cost reports.
     * @return list of cost report entries
     * @throws Exception on failure
     */
    List<CostReportDTO> getAllCostReports() throws Exception;
}
