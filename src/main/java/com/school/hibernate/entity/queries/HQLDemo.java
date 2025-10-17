package com.school.hibernate.entity.queries;

import com.school.hibernate.entity.Student;
import com.school.hibernate.entity.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HQLDemo {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Student> query = session.createQuery("FROM Student s WHERE s.age > :age", Student.class);
        query.setParameter("age", 15);
        query.setCacheable(true);

        List<Student> students = query.list();
        for (Student s : students) {
            System.out.println(s);
        }

        session.close();
    }
}

