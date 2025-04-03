package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Front Controller for PTFMS.
 * Routes all incoming actions to corresponding servlets or JSPs.
 * 
 * URL Pattern: /controller
 * Example: /controller?action=fuelReport → FuelServlet
 * 
 * @author Kai Lu
 */
@WebServlet("/controller")
public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String destination;

        switch (action) {
            // Core servlets
            case "login": destination = "login"; break;
            case "logout": destination = "logout"; break;
            case "register": destination = "register"; break;
            case "logBreak": destination = "BreakServlet"; break;
            case "fuelReport": destination = "FuelServlet"; break;
            case "gpsReport": destination = "GpsServlet"; break;
            case "report": destination = "ReportServlet"; break;
            case "resolveAlert": destination = "ResolveAlertServlet"; break;
            case "updateSchedule": destination = "UpdateScheduleServlet"; break;
            case "vehicleFormSubmit": destination = "VehicleRegisterServlet"; break;
            case "vehicleList": destination = "VehicleListServlet"; break;

            // View-only JSPs
            case "managerHome": destination = "managerHome.jsp"; break;
            case "operatorHome": destination = "operatorHome.jsp"; break;
            case "breakLog": destination = "breakLog.jsp"; break;
            case "fuelView": destination = "fuelReport.jsp"; break;
            case "gpsView": destination = "gpsView.jsp"; break;
            case "maintenanceAlert": destination = "maintenanceAlert.jsp"; break;
            case "maintenanceSchedule": destination = "maintenanceSchedule.jsp"; break;
            case "reportDashboard": destination = "reportDashboard.jsp"; break;
            case "vehicleForm": destination = "vehicleForm.jsp"; break;

            case "registerPage": destination = "register.jsp"; break;
            case "loginPage": destination = "login.jsp"; break;
            

            default:
                destination = "error.jsp"; // fallback
                break;
        }

        request.getRequestDispatcher(destination).forward(request, response);
    }
}
