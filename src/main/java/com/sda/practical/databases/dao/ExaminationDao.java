package com.sda.practical.databases.dao;

import com.sda.practical.databases.entity.Examination;
import com.sda.practical.databases.entity.Examination;
import com.sda.practical.databases.utils.PetClinicDatabase;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ExaminationDao {
    public void addExamination(Examination examination) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(examination);
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

    public void updateExamination(Examination examination) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(examination);
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


    public Examination getExamination(Integer id) {
        Session session = null;

        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.find(Examination.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Examination> getExaminations() {

        Session session = null;
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.createQuery("from Examination", Examination.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Examination> getExaminationsToCheckOut() {
        Session session = null;
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.createQuery("from Examination where checkOut = null", Examination.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

}
