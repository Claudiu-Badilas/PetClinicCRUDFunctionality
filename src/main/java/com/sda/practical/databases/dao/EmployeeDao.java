package com.sda.practical.databases.dao;

import com.sda.practical.databases.entity.*;
import com.sda.practical.databases.utils.PetClinicDatabase;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDao {
    public void addEmployee(Employee employee) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateEmployee(Employee employee) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteEmployee(Employee employee) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Employee getEmployee(Integer id) {
        Session session = null;

        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.find(Employee.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Employee> getEmployees() {

        Session session = null;
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.createQuery("from Employee", Employee.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public Employee getEmployeeByFirstName(String firstName) {
        Session session = null;
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.createQuery("from Employee where firstName = '" + firstName + "'", Employee.class).getSingleResult();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}