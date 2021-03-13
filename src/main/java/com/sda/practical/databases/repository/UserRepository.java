package com.sda.practical.databases.repository;

import com.sda.practical.databases.model.User;
import com.sda.practical.databases.utils.PetClinicDatabase;
import org.hibernate.Session;

import java.util.List;

public class UserRepository extends RepositoryImp<User> {
    Session session;

    @Override
    public User findById(Integer id) {
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.find(User.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> findAll() {
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public User logIn(String username , String password) {
        Session session = null;
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();

            return session.createQuery("from User u where u.username = '" + username +"' and u.password ='" +
                    password + "'" ,User.class).getSingleResult();
        } catch (Exception ex) {
            System.out.println("You provided wrong credentials!!!");
            ex.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
