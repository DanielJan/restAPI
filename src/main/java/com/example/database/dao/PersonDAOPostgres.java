/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.database.dao;

import com.example.Person;
import com.example.PersonNotFound;
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

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public Optional<Person> searchPersonBy(int id) {
        EntityManager entityManager = getEntityManager();
        return Optional.ofNullable(entityManager.find(Person.class, id));
    }

    @Override
    public void deletePerson(int id) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        if (entityManager.contains(entityManager.find(Person.class, id))) {
       //    entityManager.remove(entityManager.find(Person.class, id));
       entityManager.remove();
            entityManager.getTransaction().commit();
        } else {
            throw new PersonNotFound(id);
        }

        entityManager.close();
    }

    @Override
    public void modifyPerson(int id, String name, Integer age) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPerson(Person person) {
        EntityManager entityManager = getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();
        System.out.println("dodano " + person);
        entityManager.close();

    }

    @Override
    public Collection<Person> showPersons() {
        EntityManager entityManager = getEntityManager();

        return entityManager.createQuery("from Person", Person.class).getResultList();
    }

}
