/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author damian.wrobel
 */
@RestController
public class DemoController{
    
    private List<Person> persons = new ArrayList<Person>();
    
    @PostConstruct
    private void setup(){
       Person Adam = new Person("Adam",14,1);
       Person Karol = new Person("Karol",30,2);
        
        persons.add(Adam);
        persons.add(Karol);
     }
  
    @RequestMapping(value = "/persons/{name}", method = RequestMethod.GET)
    public String helloWorld(@PathVariable String name){
        return "Hello:"+ name;        
        
    }
    
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<Person> showPersons(){
        return persons;
      }
    
    @RequestMapping(value = "/persons", method = RequestMethod.POST)
    public String addPerson(@RequestBody Person person){
        persons.add(person);
        return "Dodano osobę " +  person.toString() + "do listy";
        
    }
    
    
    
    
     
   
}
