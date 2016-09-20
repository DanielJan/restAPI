/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.services.DemoService;
import java.util.Collection;

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
        return demoService.showPerson(id);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable int id) {
        demoService.deletePerson(id);
    }

    @RequestMapping(value = "/persons/{id}", method = RequestMethod.PUT)
    public void modifyPerson(@PathVariable int id,
            @RequestHeader(value = "name", required = false) String name,
            @RequestHeader(value = "age", required = false) Integer age) {
        demoService.modifyPerson(id, name, age);
    }

    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public void addPerson(@RequestBody Person person) {
        demoService.addPerson(person);
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public Collection<Person> showPersons() {
        return demoService.showPersons();
    }

}
