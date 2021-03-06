/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.AlreadyExists;
import com.example.Person;
import com.example.PersonNotFound;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author damian.wrobel
 */
@RestController
public class DemoController2 {

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

    @RequestMapping(value = "/osoby/{id}", method = RequestMethod.GET)
    public Person showPerson(@PathVariable int id) {
        Optional<Person> optPerson = searchPersonBy(id);
        if (optPerson.isPresent()) {
            return optPerson.get();
        } else {
            throw new PersonNotFound(id);
        }
    }

    @RequestMapping(value = "/osoby/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable int id) {
        if (persons.containsKey(id)) {
            persons.remove(id);
            System.out.println("Usunięto osobę");
        } else {
            throw new PersonNotFound(id);
        }
//        if (persons.remove(id) != null) {
//            System.out.println("Usunięto osobę");
//        } else {
//            throw new PersonNotFound(id);
//        }
    }

    @RequestMapping(value = "/osoby", method = RequestMethod.POST)
    public void addPerson(@RequestBody Person person) {
        if (!persons.containsKey(person.getId())) {
            persons.put(person.getId(), person);
        } else {
            throw new AlreadyExists(person.getId());
        }
    }

    @RequestMapping(value = "/osoby", method = RequestMethod.GET)
    public Collection<Person> showPersons() {
        return persons.values();
    }

    @RequestMapping(value = "/osoby/{id}", method = RequestMethod.PUT)
    public void modifyPerson(@PathVariable int id,
            @RequestHeader(value = "name", required = false) String name,
            @RequestHeader(value = "age", required = false) Integer age) {
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

    public Optional<Person> searchPersonBy(int id) {
        return Optional.ofNullable(persons.get(id));
    }

}
