package com.sda.practical.databases.repository;

import com.sda.practical.databases.utils.PetClinicDatabase;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class RepositoryImp<T> implements Repository<T>{
    Transaction transaction;
    Session session;

    @Override
    public void save(T t) {
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(t);
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

    @Override
    public void delete(T t) {
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(t);
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

    @Override
    public void update(T t) {
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(t);
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


}
