package com.sda.practical.databases.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.sda.practical.databases.model.Type;
import com.sda.practical.databases.utils.PetClinicDatabase;

import java.util.List;

public class TypeRepository extends RepositoryImp<Type>{
     Session session;
     Transaction transaction;
    @Override
    public List<Type> findAll() {
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.createQuery("from Type", Type.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public Type findById(Integer id) {
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.find(Type.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
