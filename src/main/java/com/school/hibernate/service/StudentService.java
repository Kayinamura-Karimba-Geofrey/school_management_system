package com.school.hibernate.service;

import com.school.hibernate.entity.Student;
import com.school.hibernate.entity.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class StudentService {

    // CREATE or UPDATE
    public void saveStudent(Student student) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.merge(student);
            tx.commit();
            System.out.println("Student saved: " + student.getName());
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error saving student: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // READ
    public Student getStudent(int id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Student.class, id);
        } catch (Exception e) {
            System.err.println("Error retrieving student with id " + id + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // READ ALL
    public List<Student> getAllStudents() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from Student", Student.class).list();
        } catch (Exception e) {
            System.err.println("Error retrieving all students: " + e.getMessage());
            e.printStackTrace();
            return List.of(); // Return empty list instead of null
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // UPDATE
    public void updateStudent(Student student) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.merge(student);
            tx.commit();
            System.out.println("Student updated: " + student.getName());
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error updating student: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    // DELETE
    public void deleteStudent(int id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.remove(student);
                System.out.println("Student deleted: " + student.getName());
            } else {
                System.out.println("Student with id " + id + " not found");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("Error deleting student with id " + id + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

