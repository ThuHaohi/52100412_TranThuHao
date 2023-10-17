package vn.edu.tdtu.javatech.lab5.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import vn.edu.tdtu.javatech.lab5.model.Product;
import vn.edu.tdtu.javatech.lab5.repository.Repository;
import vn.edu.tdtu.javatech.lab5.utils.HibernateUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductDAO implements Repository<Product, Long> {
    @Override
    public Long add(Product item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            Long productID = (Long) session.save(item);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return productID;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            List<Product> productList = session.createQuery("FROM Product", Product.class).list();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return productList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Product get(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            Product product = session.find(Product.class, id);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return product;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Product item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            session.update(item);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean remove(Product item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            session.delete(item);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean removeById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            Product product = session.find(Product.class, id);
            session.delete(product);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

}
