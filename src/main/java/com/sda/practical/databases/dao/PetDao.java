package com.sda.practical.databases.dao;


import com.sda.practical.databases.entity.*;
import com.sda.practical.databases.utils.PetClinicDatabase;
import org.hibernate.*;

import java.util.*;

public class PetDao {
    public void addPet(Pet pet) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(pet);
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

    public void updatePet(Pet pet) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(pet);
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

    public void deletePet(Pet pet) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(pet);
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

    public Pet getPet(Integer id) {
        Session session = null;

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

    public List<Pet> getPets() {

        Session session = null;
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

    public Pet findPetByOwnersName(String name) {
        Session session = null;
        try {
            session = PetClinicDatabase.getSessionFactory().openSession();

            Pet pet = session.createQuery("from Pet p  where p.ownerName = '" + name + "'", Pet.class).getSingleResult();
            return pet;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

}
