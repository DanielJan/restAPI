/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.database.dao;

import com.example.Person;
import java.util.Collection;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author damian.wrobel
 */
@Repository(value = "personDAOPostgres")
public class PersonDAOPostgres implements PersonDAO {
    
      
    @Autowired
    private EntityManagerFactory emf;

    @Override
    public Optional<Person> searchPersonBy(int id) {
        EntityManager em = getEntityManager();
        return null;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Person> showPersons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private EntityManager getEntityManager () {
        return emf.createEntityManager();
    }
    
}
