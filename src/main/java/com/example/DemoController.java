/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
public class DemoController {

    private final List<Person> persons = new ArrayList<>();

    @PostConstruct
    private void setup() {
        Person olek = new Person("Olek", 40, 3);
        Person adam = new Person("Adam", 14, 2);
        Person karol = new Person("Karol", 30, 1);

        persons.add(olek);
        persons.add(adam);
        persons.add(karol);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
    public Person showPerson(@PathVariable int id) {
        Optional<Person> optPerson = searchPersonBy(id);
        if (optPerson.isPresent() == true) {
            return optPerson.get();
        } else {
            throw new PersonNotFound(id);
        }

    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable int id) {

        Optional<Person> optPerson = searchPersonBy(id);
        if (optPerson.isPresent() == true) {
            persons.remove(id);
        } else {
            throw new PersonNotFound(id);
        }
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public String addPerson(@RequestBody Person person) {
        persons.add(person);
        return "Dodano osobę " + person.toString() + " do listy";
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<Person> showPersons() {
        return persons;
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT)
    public void modifyPerson(@PathVariable int id,
            @RequestHeader(value = "name") String name,
            @RequestHeader(value = "age") int age) {

        Optional<Person> optPerson = searchPersonBy(id);
        if (optPerson.isPresent() == true) {
            Person person = new Person(id);
            person.setAge(age);
            person.setName(name);
        } else {
            throw new PersonNotFound(id);
        }
    }

    public Optional<Person> searchPersonBy(int id) {
        for (Person person : persons) {
            if (person.getId() == id) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }
}
