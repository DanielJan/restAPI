/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.wrobel.damian.controllers;

import com.example.Person;
import com.example.PersonNotFound;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.wrobel.damian.services.DemoService;

/**
 *
 * @author damian.wrobel
 */
@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;

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
            Person objPer = optPerson.get();
            persons.remove(objPer);
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
        for (Person person : persons) {
            if (person.getId() == id) {
                return Optional.of(person);
            }
        }
        return Optional.empty();
    }
}
