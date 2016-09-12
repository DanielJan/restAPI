/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author damian.wrobel
 */
@RestController
public class DemoController {

    private List<Person> persons = new ArrayList<Person>();

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
        for (int i = 0; i < persons.size(); i++) {
            Person person = persons.get(i);
            if (person.getId() == id) {
                return person;
            }
        }
        throw new RuntimeException("nie ma takiej osoby");
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<Person> showPersons() {
        return persons;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public String addPerson(@RequestBody Person person) {
        persons.add(person);
        return "Dodano osobę " + person.toString() + "do listy";
    }

}
