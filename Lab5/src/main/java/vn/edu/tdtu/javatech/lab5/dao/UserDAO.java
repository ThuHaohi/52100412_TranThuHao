package vn.edu.tdtu.javatech.lab5.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import vn.edu.tdtu.javatech.lab5.model.User;
import vn.edu.tdtu.javatech.lab5.repository.Repository;
import vn.edu.tdtu.javatech.lab5.utils.HibernateUtils;

import java.util.List;

public class UserDAO implements Repository<User, Long> {
    @Override
    public Long add(User user) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            Long userID = (Long) session.save(user);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return userID;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            List<User> productList = session.createQuery("FROM User", User.class).list();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return productList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public User get(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            User user = session.find(User.class, id);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return user;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(User item) {
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
    public boolean remove(User item) {
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
            User user = session.find(User.class, id);
            session.delete(user);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public User findByUsername(String username) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            Query query= session.createQuery("from User where username=:username");
            query.setParameter("username", username);
            User user = (User)query.uniqueResult();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return user;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

}
