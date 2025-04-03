package servlet;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Handles user registration for FR-01.
 * Saves new user (Manager or Operator) to database using DAO.
 *
 * URL: /register
 *
 * @author Kai Lu
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            UserDAO userDAO = new UserDAOImpl();

            if (userDAO.emailExists(email)) {
                request.setAttribute("error", "Email already registered.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            UserDTO user = new UserDTO();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setRole(role);

            boolean success = userDAO.registerUser(user);

            if (success) {
                request.setAttribute("message", "Registration successful. Please login.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Registration failed.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    }
}
