package com.school.hibernate.service;

import com.school.hibernate.entity.Address;
import com.school.hibernate.entity.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class AddressService {

    public void saveAddress(Address address) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(address);
            tx.commit();
            System.out.println("Address saved: " + address.getStreet());
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error saving address: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Address getAddress(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Address.class, id);
        } catch (Exception e) {
            System.err.println("Error retrieving address with id " + id + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Address> getAllAddresses() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from Address", Address.class).list();
        } catch (Exception e) {
            System.err.println("Error retrieving all addresses: " + e.getMessage());
            e.printStackTrace();
            return List.of(); // Return empty list instead of null
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteAddress(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Address address = session.get(Address.class, id);
            if (address != null) {
                session.remove(address);
                System.out.println("Address deleted: " + address.getStreet());
            } else {
                System.out.println("Address with id " + id + " not found");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error deleting address with id " + id + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
