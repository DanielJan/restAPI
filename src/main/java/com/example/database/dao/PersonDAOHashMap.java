/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.database.dao;

import com.example.AlreadyExists;
import com.example.Person;
import com.example.PersonNotFound;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

/**
 *
 * @author damian.wrobel
 */
@Repository(value = "personDAOHashMap")
public class PersonDAOHashMap implements PersonDAO {

    private HashMap<Integer, Person> persons = new HashMap<Integer, Person>();

    @PostConstruct
    private void setup() {
        Person olek = new Person("Olek", 40, 3);
        Person adam = new Person("Adam", 14, 2);
        Person karol = new Person("Karol", 30, 1);

        persons.put(olek.getId(), olek);
        persons.put(adam.getId(), adam);
        persons.put(karol.getId(), karol);
    }

    @Override
    public Optional<Person> searchPersonBy(int id) {
        return Optional.ofNullable(persons.get(id));
    }

    @Override
    public void deletePerson(int id) {
        if (persons.containsKey(id)) {
            persons.remove(id);
            System.out.println("Usunięto osobę");
        } else {
            throw new PersonNotFound(id);
        }
    }

    @Override
    public void modifyPerson(int id, String name, Integer age) {
        Optional<Person> optPerson = searchPersonBy(id);
        if (optPerson.isPresent() == true) {
            Person objPer = optPerson.get();
            if (name != null & age != null) {
                objPer.setAge(age);
                objPer.setName(name);
            } else if (name != null & age == null) {
                objPer.setName(name);
            } else if (name == null & age != null) {
                objPer.setAge(age);
            }
        } else {
            throw new PersonNotFound(id);
        }
    }

    @Override
    public void addPerson(Person person) {
        if (!persons.containsKey(person.getId())) {
            persons.put(person.getId(), person);
        } else {
            throw new AlreadyExists(person.getId());
        }

    }

    @Override
    public Collection<Person> showPersons() {
        return persons.values();
    }

}
