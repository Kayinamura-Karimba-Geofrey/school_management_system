package com.school.hibernate.service;

import com.school.hibernate.entity.Teacher;
import com.school.hibernate.entity.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class TeacherService {

    public void saveTeacher(Teacher teacher) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.persist(teacher);
            tx.commit();
            System.out.println("Teacher saved: " + teacher.getName());
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error saving teacher: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Teacher getTeacher(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Teacher.class, id);
        } catch (Exception e) {
            System.err.println("Error retrieving teacher with id " + id + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Teacher> getAllTeachers() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from Teacher", Teacher.class).list();
        } catch (Exception e) {
            System.err.println("Error retrieving all teachers: " + e.getMessage());
            e.printStackTrace();
            return List.of(); // Return empty list instead of null
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteTeacher(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Teacher teacher = session.get(Teacher.class, id);
            if (teacher != null) {
                session.remove(teacher);
                System.out.println("Teacher deleted: " + teacher.getName());
            } else {
                System.out.println("Teacher with id " + id + " not found");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error deleting teacher with id " + id + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
