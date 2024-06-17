package org.example.tutorial.hibernate.postgresql;

import org.checkerframework.checker.units.qual.s;
import org.checkerframework.checker.units.qual.t;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.query.Query;

import java.util.List;



public class demo {
    public static void main(String[] args) {
        /*A service is something which provides a piece of specific functionality required by the framework. */
        /*The main purpose of a service registry is to hold, manage and provide access to services. */
        /*The service registry is built using info about the database connection,entity classes.
         * using hibernate.cfg.xml,the serviceRegistry knows properties such as printing SQL to the console,auto transact at startup,
         * database connection details etc.
         */

         /*The metadatasources collects the metadata information about the annotated classes, etc from the serviceregistry.
          * 
          */
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        SessionFactory sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();

        /*The session class is a critical component which is used to interact with the database. */
        Session session = sessionFactory.openSession();
    //    createTeachersRecord(session);
    // //    deleteTeachersRecord(session);
    // updateTeachersRecord(session);
    fetchTeachersRecord(session);
        sessionFactory.close();

        //CRUD commands

        
    }

   

    private static void createTeachersRecord(Session session){
      
       Teachers teacher1 = new Teachers();
       Teachers teacher2 = new Teachers();
       Teachers teacher3 = new Teachers();
       teacher1.setId(1);
       teacher1.setFirstName("Priyam");
       teacher1.setLastName("Mehta");
       teacher1.setSalary(55000);
       teacher2.setId(2);
       teacher2.setFirstName("Dev");
       teacher2.setLastName("Bhagadia");
       teacher2.setSalary(60000);
       teacher3.setId(3);
       teacher3.setFirstName("Kushal");
       teacher3.setLastName("Maru");
       teacher3.setSalary(85000);
       session.beginTransaction();
       session.save(teacher2);
       session.save(teacher3);
       session.getTransaction().commit();
    }

    private static void deleteTeachersRecord(Session session){
        int id = 1;
        Teachers teacher1 = session.get(Teachers.class, id);
        session.beginTransaction();
        session.delete(teacher1);
        session.getTransaction().commit();
    }

    private static void updateTeachersRecord(Session session){
        int id=1;
        Teachers teacher = session.get(Teachers.class, id);
        teacher.setSalary(100000);
        session.beginTransaction();
        session.save(teacher);
        session.getTransaction().commit();
    }

    private static void fetchTeachersRecord(Session session){
        Query<Teachers> query = session.createQuery("FROM Teachers",Teachers.class);
        List<Teachers> teachers = query.list();
        for(Teachers teacher : teachers){
System.out.println("First Name : "+ teacher.getFirstName());
System.out.println("Last Name :"+teacher.getLastName());
System.out.println("Salary :"+ teacher.getSalary());
        }
    }
    
}
