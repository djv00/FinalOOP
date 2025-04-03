package servlet;

import dao.VehicleDAO;
import dao.VehicleDAOImpl;
import model.VehicleDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet to load and display all registered vehicles.
 * Forwards vehicle list to vehicleList.jsp.
 *
 * URL: /VehicleListServlet (via FrontControllerServlet)
 * 
 * @author Kai Lu
 */
@WebServlet("/VehicleListServlet")
public class VehicleListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            VehicleDAO dao = new VehicleDAOImpl();
            List<VehicleDTO> vehicles = dao.getAllVehicles();
            request.setAttribute("vehicles", vehicles);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load vehicle list: " + e.getMessage());
        }

        request.getRequestDispatcher("vehicleList.jsp").forward(request, response);
    }
}
