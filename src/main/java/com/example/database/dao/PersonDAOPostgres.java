/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.database.dao;

import com.example.Person;
import java.util.Collection;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author damian.wrobel
 */
@Repository(value = "PersonDAOPostgres")
public class PersonDAOPostgres implements PersonDAO {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public void setentityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public EntityManagerFactory getentityManagerFactory() {
        return entityManagerFactory;
    }

   // private EntityManager getEntityManager() {
   //     return eMF.createEntityManager();
   //}

    @Override
    public Optional<Person> searchPersonBy(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePerson(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifyPerson(int id, String name, Integer age) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPerson(Person person) {
   
        
        
        
    }

    @Override
    public Collection<Person> showPersons() {
        EntityManager em = entityManagerFactory.createEntityManager();
        
        return em.createQuery("from Person", Person.class).getResultList();
    }

}
