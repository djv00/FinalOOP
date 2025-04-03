package servlet;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Handles login logic for FR-01.
 * Validates credentials and creates session with role-based control.
 *
 * URL: /login
 *
 * @author Kai Lu
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            UserDAO userDAO = new UserDAOImpl();
            UserDTO user = userDAO.login(email, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                if ("Manager".equals(user.getRole())) {
                    response.sendRedirect("managerHome.jsp");
                } else {
                    response.sendRedirect("operatorHome.jsp");
                }
            } else {
                request.setAttribute("error", "Invalid email or password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("error", "Login error: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}
