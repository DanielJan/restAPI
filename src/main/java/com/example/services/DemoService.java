/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.Person;
import com.example.PersonNotFound;
import com.example.database.dao.PersonDAO;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author damian.wrobel
 */
@Service
public class DemoService {

    private PersonDAO personDAO;

    @Autowired
    public DemoService(@Qualifier(value = "PersonDAOPostgres") PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public Person showPerson(int id) {
        Optional<Person> optPerson = personDAO.searchPersonBy(id);
        if (optPerson.isPresent() == true) {
            return optPerson.get();
        } else {
            throw new PersonNotFound(id);
        }
    }

    public void deletePerson(int id) {
        Optional<Person> optPerson = personDAO.searchPersonBy(id);
        if (optPerson.isPresent() == true) {
            personDAO.deletePerson(id);
        } else {
            throw new PersonNotFound(id);
        }
    }

    public void modifyPerson(int id, String name, Integer age) {
        Optional<Person> optPerson = personDAO.searchPersonBy(id);
        if (optPerson.isPresent()) {
            Person objPer = optPerson.get();
            if (name != null) {
                objPer.setName(name);
            }
            if (age != null) {
                objPer.setAge(age);
            }
        } else {
            throw new PersonNotFound(id);
        }
    }

    public void addPerson(Person person) {
        personDAO.addPerson(person);
           }

    public Collection<Person> showPersons() {
        return personDAO.showPersons();
    }

}
