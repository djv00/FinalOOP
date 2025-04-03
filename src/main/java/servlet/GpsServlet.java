package servlet;

import dao.GpsTrackingDAO;
import dao.GpsTrackingDAOImpl;
import model.GpsLogDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet for handling GPS log requests.
 * Responds to vehicle log queries for FR-03 GPS Tracking.
 *
 * Route: /GpsServlet
 * Uses doGet to handle report viewing.
 *
 * Forwards results to gpsView.jsp.
 *
 * @author Kai Lu
 */
@WebServlet("/GpsServlet")
public class GpsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vehicleIdParam = request.getParameter("vehicleId");

        if (vehicleIdParam != null && !vehicleIdParam.isEmpty()) {
            try {
                int vehicleId = Integer.parseInt(vehicleIdParam);
                GpsTrackingDAO dao = new GpsTrackingDAOImpl();
                List<GpsLogDTO> logs = dao.getLogsByVehicleId(vehicleId);
                request.setAttribute("logs", logs);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Unable to load GPS data: " + e.getMessage());
            }
        }

        request.getRequestDispatcher("gpsView.jsp").forward(request, response);
    }
}
