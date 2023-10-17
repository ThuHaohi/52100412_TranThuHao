package vn.edu.tdtu.javatech.lab5.servlets;

import vn.edu.tdtu.javatech.lab5.dao.UserDAO;
import vn.edu.tdtu.javatech.lab5.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class MainServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("Starting Main Servlet!!!");
        System.out.println("Initializing ....");
    }
    @Override
    public void destroy() {
        System.out.println("Deleting Servlet!!!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/Lab5/Login");
    }
}