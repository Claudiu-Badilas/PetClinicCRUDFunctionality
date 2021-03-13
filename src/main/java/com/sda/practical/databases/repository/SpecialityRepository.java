package com.sda.practical.databases.repository;

import com.sda.practical.databases.model.Speciality;
import com.sda.practical.databases.utils.PetClinicDatabase;
import org.hibernate.Session;

import java.util.List;

public class SpecialityRepository extends RepositoryImp<Speciality> {

    Session session;

    @Override
    public Speciality findById(Integer id) {
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.find(Speciality.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Speciality> findAll() {
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.createQuery("from Speciality", Speciality.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
