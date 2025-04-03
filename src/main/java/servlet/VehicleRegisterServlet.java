package servlet;

import dao.VehicleDAO;
import dao.VehicleDAOImpl;
import model.VehicleDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet to handle vehicle registration form submission.
 * Saves vehicle info to MySQL using DAO pattern.
 *
 * URL: /VehicleRegisterServlet
 * Triggered via: action=vehicleFormSubmit in FrontController
 *
 * @author Kai Lu
 */
@WebServlet("/VehicleRegisterServlet")
public class VehicleRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            VehicleDTO vehicle = new VehicleDTO.Builder()
                .withVehicleNumber(request.getParameter("vehicleNumber"))
                .withVehicleType(request.getParameter("vehicleType"))
                .withFuelType(request.getParameter("fuelType"))
                .withConsumptionRate(Double.parseDouble(request.getParameter("consumptionRate")))
                .withMaxPassengers(Integer.parseInt(request.getParameter("maxPassengers")))
                .withCurrentRoute(request.getParameter("currentRoute"))
                .build();

            VehicleDAO dao = new VehicleDAOImpl();
            dao.addVehicle(vehicle);

            request.setAttribute("message", "Vehicle registered successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to register vehicle: " + e.getMessage());
        }

        request.getRequestDispatcher("vehicleForm.jsp").forward(request, response);
    }
}
