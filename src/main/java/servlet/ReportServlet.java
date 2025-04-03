package servlet;

import adapter.CostReportAdapter;
import adapter.MaintenanceScheduleAdapter;
import adapter.PerformanceReportAdapter;
import adapter.ReportData;
import dao.MaintenanceDAOImpl;
import dao.ReportDAO;
import dao.ReportDAOImpl;
import model.CostReportDTO;
import model.MaintenanceScheduleDTO;
import model.PerformanceLogDTO;

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
 * Combines performance, cost, and maintenance schedule data using Adapter Pattern.
 *
 * Route: /ReportServlet
 * Forwards reportData to reportDashboard.jsp.
 *
 * @author Kai Lu
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ReportDAO reportDAO = new ReportDAOImpl();
            MaintenanceDAOImpl maintenanceDAO = new MaintenanceDAOImpl();

            List<PerformanceLogDTO> perfLogs = reportDAO.getAllPerformanceLogs();
            List<CostReportDTO> costReports = reportDAO.getAllCostReports();
            List<MaintenanceScheduleDTO> schedules = maintenanceDAO.getAllSchedules();

            List<ReportData> reportData = new ArrayList<>();
            reportData.addAll(PerformanceReportAdapter.adapt(perfLogs));
            reportData.addAll(CostReportAdapter.adapt(costReports));
            reportData.addAll(MaintenanceScheduleAdapter.adapt(schedules));

            request.setAttribute("reportData", reportData);
        } catch (Exception e) {
            request.setAttribute("error", "Failed to generate report: " + e.getMessage());
            e.printStackTrace();
        }

        request.getRequestDispatcher("reportDashboard.jsp").forward(request, response);

    }
}
