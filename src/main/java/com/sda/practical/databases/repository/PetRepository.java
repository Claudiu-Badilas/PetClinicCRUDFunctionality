package com.sda.practical.databases.repository;

import com.sda.practical.databases.model.Pet;
import com.sda.practical.databases.utils.PetClinicDatabase;
import org.hibernate.Session;

import java.util.List;

public class PetRepository extends RepositoryImp<Pet> {

    Session session;

    @Override
    public Pet findById(Integer id) {
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.find(Pet.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Pet> findAll() {
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            return session.createQuery("from Pet", Pet.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
