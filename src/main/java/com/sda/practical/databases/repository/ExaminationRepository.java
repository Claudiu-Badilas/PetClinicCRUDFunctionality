package com.sda.practical.databases.repository;

import com.sda.practical.databases.model.Examination;
import com.sda.practical.databases.utils.PetClinicDatabase;
import org.hibernate.Session;

import java.util.List;

public class ExaminationRepository extends RepositoryImp<Examination> {

    Session session;

    @Override
    public Examination findById(Integer id) {
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

    @Override
    public List<Examination> findAll() {
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

    public List<Examination> findAllExaminationsToCheckOut() {
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
