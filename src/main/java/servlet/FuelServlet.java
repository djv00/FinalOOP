package servlet;

import dao.EnergyConsumptionDAO;
import dao.EnergyConsumptionDAOImpl;
import dao.VehicleDAO;
import dao.VehicleDAOImpl;
import model.FuelLogDTO;
import model.VehicleDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/FuelServlet")
public class FuelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            VehicleDAO vehicleDAO = new VehicleDAOImpl();
            EnergyConsumptionDAO energyDAO = new EnergyConsumptionDAOImpl();

            List<VehicleDTO> vehicleList = vehicleDAO.getAllVehicles();
            request.setAttribute("vehicleList", vehicleList);

            String vehicleIdParam = request.getParameter("vehicleId");
            if (vehicleIdParam != null && !vehicleIdParam.isEmpty()) {
                int vehicleId = Integer.parseInt(vehicleIdParam);
                List<FuelLogDTO> logs = energyDAO.getLogsByVehicleId(vehicleId);

                List<Map<String, Object>> result = new ArrayList<>();
                for (FuelLogDTO log : logs) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("log", log);
                    map.put("efficiency", energyDAO.calculateEfficiency(log));
                    result.add(map);
                }

                request.setAttribute("logs", result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load fuel report: " + e.getMessage());
        }

        request.getRequestDispatcher("fuelReport.jsp").forward(request, response);
    }
}
