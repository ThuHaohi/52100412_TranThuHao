package vn.edu.tdtu.javatech.lab5.servlets;

import vn.edu.tdtu.javatech.lab5.dao.ProductDAO;
import vn.edu.tdtu.javatech.lab5.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/Products")
public class ProductsServlet extends HttpServlet {
    private ProductDAO productDAO;
    @Override
    public void init() throws ServletException {
        System.out.println("Starting Products Servlet!!!");
        System.out.println("Initializing ....");
        this.productDAO = new ProductDAO();
    }

    @Override
    public void destroy() {
        System.out.println("Deleting Servlet!!!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("delete".equals(request.getParameter("action"))) {
            Long productId = Long.valueOf(request.getParameter("productId"));
            productDAO.removeById(productId);
            response.sendRedirect("/Lab5/Products");
            return;
        }
        response.setContentType("text/html");
        String productName = request.getParameter("productname");
        String productPrice = request.getParameter("productprice");
        try {
            Product product = new Product(productName, Double.valueOf(productPrice));
            productDAO.add(product);
            System.out.println("Product " + productName + " is successfully added!!!");
            response.sendRedirect("/Lab5/Products");
        }catch (Exception ex) {
            System.out.println("Failed to add a product!!");
            System.out.println(ex.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDAO.getAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/jsp/products.jsp").forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}