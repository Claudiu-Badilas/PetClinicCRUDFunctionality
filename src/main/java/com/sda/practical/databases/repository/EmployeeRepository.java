package com.sda.practical.databases.repository;

import com.sda.practical.databases.model.Employee;
import com.sda.practical.databases.utils.PetClinicDatabase;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeRepository extends RepositoryImp<Employee> {
    Transaction transaction;
    Session session;

    @Override
    public Employee findById(Integer id) {
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

    @Override
    public List<Employee> findAll() {
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
}
