/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.services;

import com.example.Person;
import com.example.PersonNotFound;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 *
 * @author damian.wrobel
 */
@Service
public class DemoService {

    private ArrayList<Person> persons = new ArrayList<Person>();

    @PostConstruct
    private void setup() {
        Person olek = new Person("Olek", 40, 3);
        Person adam = new Person("Adam", 14, 2);
        Person karol = new Person("Karol", 30, 1);

        persons.add(olek);
        persons.add(adam);
        persons.add(karol);
    }

    private Optional<Person> searchPersonBy(int id) {
        for (Person person : persons) {
            if (person.getId() == id) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }

    public Person showPerson(int id) {
        Optional<Person> optPerson = searchPersonBy(id);
        if (optPerson.isPresent() == true) {
            return optPerson.get();
        } else {
            throw new PersonNotFound(id);
        }
    }

    public void deletePerson(int id) {
        Optional<Person> optPerson = searchPersonBy(id);
        if (optPerson.isPresent() == true) {
            Person objPer = optPerson.get();
            persons.remove(objPer);
        } else {
            throw new PersonNotFound(id);
        }
    }

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

    public String addPerson(Person person) {
        persons.add(person);
        return "Dodano osobę " + person.toString() + " do listy";
    }

    public List<Person> showPersons() {
        return persons;
    }

}
