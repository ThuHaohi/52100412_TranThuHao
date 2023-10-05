package vn.edu.tdtu.javatech.lab3.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import vn.edu.tdtu.javatech.lab3.domain.Manufacturer;
import vn.edu.tdtu.javatech.lab3.repository.Repository;
import vn.edu.tdtu.javatech.lab3.utils.HibernateUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ManufacturerDAO implements Repository<Manufacturer, Long> {
    @Override
    public Long add(Manufacturer item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            Long id = (Long) session.save(item);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return id;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Manufacturer> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            List<Manufacturer> manufacturerList = session.createQuery("FROM Manufacture", Manufacturer.class).list();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return manufacturerList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Manufacturer get(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            Manufacturer manufacturer = session.find(Manufacturer.class, id);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return manufacturer;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Manufacturer item) {
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
    public boolean remove(Manufacturer item) {
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
            Manufacturer manufacturer = session.find(Manufacturer.class, id);
            session.delete(manufacturer);
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean allHaveMoreEmployees(Long number) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Manufacturer> cr1 = cb.createQuery(Manufacturer.class);
            Root<Manufacturer> root1 = cr1.from(Manufacturer.class);
            cr1.select(root1).where(cb.gt(root1.get("employeeNumber"), number));
            Query<Manufacturer> query1 = session.createQuery(cr1);
            List<Manufacturer> manufacturerList = query1.getResultList();
            CriteriaQuery<Long> cr2 = cb.createQuery(Long.class);
            Root<Manufacturer> root2 = cr2.from(Manufacturer.class);
            cr2.select(cb.count(root2.get("id")));
            Query<Long> query2 = session.createQuery(cr2);
            Long manufactureNumber = query2.getSingleResult();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return manufacturerList.size() == manufactureNumber;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public Integer getTotalEmployees() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Integer> cr = cb.createQuery(Integer.class);
            Root<Manufacturer> root = cr.from(Manufacturer.class);
            cr.select(cb.sum(root.get("employeeNumber")));
            Query<Integer> query = session.createQuery(cr);
            Integer employeeNumber = query.getSingleResult();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return employeeNumber;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public List<Manufacturer> findManufacturesByLocation(String location) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            // Begin a unit of work
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Manufacturer> cr = cb.createQuery(Manufacturer.class);
            Root<Manufacturer> root = cr.from(Manufacturer.class);
            cr.select(root).where(cb.equal(root.get("location"), location));
            Query<Manufacturer> query = session.createQuery(cr);
            List<Manufacturer> manufacturerList = query.getResultList();
            // Commit the current resource transaction, writing any unflushed changes to the database.
            session.getTransaction().commit();
            return manufacturerList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
}
