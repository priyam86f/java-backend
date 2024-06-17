package org.example.tutorial.hibernate.postgresql;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernatePostgresqlDemo {

    public static void main(String[] args) {
        // Set up the SessionFactory using modern Hibernate bootstrap API
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml") // Load configuration
                .build();
        SessionFactory sessionFactory = new MetadataSources(serviceRegistry)
                .buildMetadata()
                .buildSessionFactory();

    Session session = sessionFactory.openSession() ;
            //CREATE
            saveStudentRecord(session);

            // READ
            fetchStudentRecord(session);

            // UPDATE
            updateStudentRecord(session);

            //DELETE
            deleteStudentRecord(session);
        

        sessionFactory.close();
    }

    private static void deleteStudentRecord(Session session) {
        int id = 8;
        Student student1 = session.get(Student.class, id);
        if (student1 != null) {
            session.beginTransaction();
            session.delete(student1);
            session.getTransaction().commit();
            System.out.println("Record deleted successfully...");
        } else {
            System.out.println("Student with id " + id + " not found");
        }
    }

    private static void updateStudentRecord(Session session) {
        int id = 3;
        Student student1 = session.get(Student.class, id);
        if (student1 != null) {
            student1.setLastName("Thawani");
            student1.setRoll(40);
            student1.setStandard("P");
            session.beginTransaction();
            session.save(student1);
            session.getTransaction().commit();
            System.out.println("Record updated successfully...");
        } else {
            System.out.println("Student with id " + id + " not found");
        }
    }

    private static void fetchStudentRecord(Session session) {
        Query<Student> query = session.createQuery("FROM Student", Student.class);
        List<Student> students = query.list();
        System.out.println("Reading student records...");
        for (Student student : students) {
            System.out.println("First Name: " + student.getFirstName());
            System.out.println("Last Name: " + student.getLastName());
            System.out.println("Roll Number: " + student.getRoll());
            System.out.println("Standard: " + student.getStandard());
        }
    }

    private static void saveStudentRecord(Session session) {
        Student student1 = new Student();
        student1.setFirstName("Priyam");
        student1.setLastName("Mehta");
        student1.setRoll(9);
        student1.setStandard("A");
        session.beginTransaction();
        session.save(student1);
        session.getTransaction().commit();
        System.out.println("Record saved successfully...");
    }
}
