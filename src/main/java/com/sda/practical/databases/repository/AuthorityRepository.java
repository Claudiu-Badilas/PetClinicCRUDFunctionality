package com.sda.practical.databases.repository;

import com.sda.practical.databases.model.Authority;
import com.sda.practical.databases.utils.PetClinicDatabase;
import org.hibernate.Session;

import java.util.List;

public class AuthorityRepository extends RepositoryImp<Authority>{

    Session session;

    @Override
    public Authority findById(Integer id) {
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.find(Authority.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Authority> findAll() {
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.createQuery("from Authority", Authority.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public Authority findByRole(String role) {
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.createQuery("from Authority where role='" + role +"'", Authority.class).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
