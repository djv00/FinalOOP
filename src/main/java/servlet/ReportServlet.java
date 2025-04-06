package servlet;

import adapter.*;
import dao.MaintenanceDAOImpl;
import dao.ReportDAO;
import dao.ReportDAOImpl;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet for FR-06 Reporting & Analytics.
 * Combines performance, cost, and maintenance data using Adapter Pattern.
 * Supports different report types via type parameter.
 *
 * Route: /ReportServlet
 * Parameters: type (maintenance, performance, cost, or none for unified view)
 *
 * @author Kai Lu
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String reportType = request.getParameter("type");
        String destination = "reportDashboard.jsp"; // Default view
        
        try {
            ReportDAO reportDAO = new ReportDAOImpl();
            MaintenanceDAOImpl maintenanceDAO = new MaintenanceDAOImpl();
            
            List<PerformanceLogDTO> perfLogs = reportDAO.getAllPerformanceLogs();
            List<CostReportDTO> costReports = reportDAO.getAllCostReports();
            List<MaintenanceScheduleDTO> schedules = maintenanceDAO.getAllSchedules();
            
            // Get maintenance alerts
            List<MaintenanceAlertDTO> alerts = new ArrayList<>();
            for (int vehicleId = 1; vehicleId <= 3; vehicleId++) {
                alerts.addAll(maintenanceDAO.getUnresolvedAlertsByVehicle(vehicleId));
            }
            
            // Default - unified report
            List<ReportData> reportData = new ArrayList<>();
            
            // Based on report type, prepare different data
            if (reportType == null || reportType.isEmpty()) {
                // Combined unified report
                reportData.addAll(PerformanceReportAdapter.adapt(perfLogs));
                reportData.addAll(CostReportAdapter.adapt(costReports));
                reportData.addAll(MaintenanceScheduleAdapter.adapt(schedules));
                reportData.addAll(MaintenanceAlertAdapter.adapt(alerts));
                
            } else if ("maintenance".equals(reportType)) {
                // Maintenance dashboard
                reportData.addAll(MaintenanceScheduleAdapter.adapt(schedules));
                reportData.addAll(MaintenanceAlertAdapter.adapt(alerts));
                
                // Set additional data for specific view
                request.setAttribute("schedules", schedules);
                request.setAttribute("alerts", alerts);
                destination = "maintenanceDashboard.jsp";
                
            } else if ("performance".equals(reportType)) {
                // Performance dashboard
                reportData.addAll(PerformanceReportAdapter.adapt(perfLogs));
                
                // Calculate statistics
                calculatePerformanceStats(perfLogs, request);
                request.setAttribute("performanceLogs", perfLogs);
                destination = "performanceDashboard.jsp";
                
            } else if ("cost".equals(reportType)) {
                // Cost reports
                reportData.addAll(CostReportAdapter.adapt(costReports));
                
                // Calculate totals
                calculateCostStats(costReports, request);
                request.setAttribute("costReports", costReports);
                destination = "costDashboard.jsp";
            }
            
            request.setAttribute("reportData", reportData);
        } catch (Exception e) {
            request.setAttribute("error", "Failed to generate report: " + e.getMessage());
            e.printStackTrace();
        }

        request.getRequestDispatcher(destination).forward(request, response);
    }
    
    /**
     * Calculate performance statistics
     */
    private void calculatePerformanceStats(List<PerformanceLogDTO> logs, HttpServletRequest request) {
        if (logs.isEmpty()) {
            request.setAttribute("avgEfficiency", 0);
            request.setAttribute("onTimeRate", 0);
            return;
        }
        
        double totalEfficiency = 0;
        int onTimeCount = 0;
        
        for (PerformanceLogDTO log : logs) {
            totalEfficiency += log.getEfficiencyScore();
            if (log.isOnTime()) {
                onTimeCount++;
            }
        }
        
        double avgEfficiency = totalEfficiency / logs.size();
        double onTimeRate = (onTimeCount * 100.0) / logs.size();
        
        request.setAttribute("avgEfficiency", avgEfficiency);
        request.setAttribute("onTimeRate", onTimeRate);
    }
    
    /**
     * Calculate cost statistics
     */
    private void calculateCostStats(List<CostReportDTO> reports, HttpServletRequest request) {
        double totalFuelCost = 0;
        double totalMaintenanceCost = 0;
        
        for (CostReportDTO report : reports) {
            totalFuelCost += report.getFuelCost();
            totalMaintenanceCost += report.getMaintenanceCost();
        }
        
        request.setAttribute("totalFuelCost", totalFuelCost);
        request.setAttribute("totalMaintenanceCost", totalMaintenanceCost);
        request.setAttribute("totalCost", totalFuelCost + totalMaintenanceCost);
    }
}