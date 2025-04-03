package servlet;

import dao.BreakDAO;
import dao.BreakDAOImpl;
import model.BreakDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Servlet for handling operator break submissions.
 * Used in FR-03 for manual logging of out-of-service periods.
 *
 * Method: doPost
 * Route: /BreakServlet
 *
 * Accepts form inputs: userId, vehicleId, breakStart, breakEnd, lat, lng
 *
 * Inserts a new break record using BreakDAO.
 *
 * Redirects to confirmation or form page on error.
 *
 * @author Kai Lu
 */
@WebServlet("/BreakServlet")
public class BreakServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
            Timestamp breakStart = Timestamp.valueOf(request.getParameter("breakStart"));
            Timestamp breakEnd = Timestamp.valueOf(request.getParameter("breakEnd"));
            double lat = Double.parseDouble(request.getParameter("lat"));
            double lng = Double.parseDouble(request.getParameter("lng"));

            BreakDTO b = new BreakDTO();
            b.setUserId(userId);
            b.setVehicleId(vehicleId);
            b.setBreakStart(breakStart);
            b.setBreakEnd(breakEnd);
            b.setLocationLat(lat);
            b.setLocationLng(lng);

            BreakDAO dao = new BreakDAOImpl();
            dao.logBreak(b);

            response.sendRedirect("breakLog.jsp?success=true");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to log break: " + e.getMessage());
            request.getRequestDispatcher("breakLog.jsp").forward(request, response);
        }
    }
}
