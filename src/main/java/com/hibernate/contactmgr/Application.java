package com.hibernate.contactmgr;

import com.hibernate.contactmgr.model.Contact;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class Application {
    //hold reusable referce to as session factory
    private static final SessionFactory sessionFactory= buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
        //create stand service registry
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
    public static void main(String[] args){
        ///////////////////CREATE//////////////////////////////////////
            Contact contact= new Contact.ContactBuilder("Sam","johnson")
                    .withEmail("sam111@gmail.com")
                    .withPhone(2146843111L)
                    .build();
            int id= save(contact);
            //display contact before update

        ///////////////////READ//////////////////////////////////
        System.out.println("before update... /n /n");
            fetchAllContacts().stream().forEach(System.out::println);


   ////////////////////////UPDATE///////////////////////////////////////////
            //get persited contact
            Contact c = findContactById(id);
            //update contact
            c.setFirsName("beatrix");
            //persist the changes
        System.out.println(" updating.... /n /n");
            update(c);
            //display list of contacts after update
        System.out.println("update complete! /n /n");
        fetchAllContacts().stream().forEach(System.out::println);
        /////////////////////////DELETE//////////////////////////////////////////
        delete(c);
        fetchAllContacts().stream().forEach(System.out::println);

    }
    private static Contact findContactById(int id){
        //oppen a session
        Session session = sessionFactory.openSession();
        //retrive the persistent object
        Contact contact= session.get(Contact.class, id);
        //close session
        session.close();

        //return obj
        return contact;
    }

    private static void update(Contact contact){
        //open session
        Session session = sessionFactory.openSession();
        //begin transaction
        session.beginTransaction();
        //use the session to update contact
        session.update(contact);
        //commit transaction
        session.getTransaction().commit();
        //close session
        session.close();
    }

    private static void delete(Contact contact){
        //open session
        Session session = sessionFactory.openSession();
        //begin transaction
        session.beginTransaction();
        //use the session to update contact
        session.delete(contact);
        //commit transaction
        session.getTransaction().commit();
        //close session
        session.close();
    }
    @SuppressWarnings("uncheked")
    private static List<Contact> fetchAllContacts() {
        // Open a session
        Session session = sessionFactory.openSession();

        // DEPRECATED: Create Criteria
        // Criteria criteria = session.createCriteria(Contact.class);

        // DEPRECATED: Get a list of Contact objects according to the Criteria object
        // List<Contact> contacts = criteria.list();

        // UPDATED: Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // UPDATED: Create CriteriaQuery
        CriteriaQuery<Contact> criteria = builder.createQuery(Contact.class);

        // UPDATED: Specify criteria root
        criteria.from(Contact.class);

        // UPDATED: Execute query
        List<Contact> contacts = session.createQuery(criteria).getResultList();

        // Close the session
        session.close();

        return contacts;
    }

    private  static int save(Contact contact){
        //open a session
        Session session = sessionFactory.openSession();
        //begin a transaction
        session.beginTransaction();
        //use the session to save the contact
        int id= (int)session.save(contact);
        //commit transaction
        session.getTransaction().commit();
        //close the session
        session.close();
        return id;
    }
}
