package vn.edu.tdtu.javatech.lab5.servlets;

import vn.edu.tdtu.javatech.lab5.dao.UserDAO;
import vn.edu.tdtu.javatech.lab5.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        System.out.println("Starting Login Servlet!!!");
        System.out.println("Initializing ....");
        this.userDAO = new UserDAO();
    }

    @Override
    public void destroy() {
        System.out.println("Deleting Servlet!!!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberPassword = request.getParameter("rememberPassword");
        try {
            User user = userDAO.findByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                System.out.println("User " + username + " logged in sucessfully!!");
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("username", user.getUsername());
                if (rememberPassword != null) {
                    Cookie userId = new Cookie("userId", String.valueOf(user.getId()));
                    userId.setMaxAge(30 * 60 * 60 * 24);
                    response.addCookie(userId);
                }
                response.sendRedirect("/Lab5/Products");
            } else {
                System.out.println("User " + username + " failed to logged in!!");
                response.sendRedirect("/Lab5/Register");
            }
        }catch (Exception ex) {
            System.out.println("User " + username + " failed to log in!!");
            System.out.println(ex.getMessage());
            response.sendRedirect("/Lab5/Register");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
}