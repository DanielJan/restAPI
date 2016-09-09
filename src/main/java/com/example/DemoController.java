/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.ArrayList;
import java.util.List;
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
public class DemoController {
    
    private List<Person> persons = new ArrayList<Person>();
    
  
    @RequestMapping(value = "/dupa/{name}", method = RequestMethod.GET)
    public String helloWorld(@PathVariable String name){
        return "Hello:"+ name;        
        
    }
    @RequestMapping(value = "/asd", method = RequestMethod.POST)
    public String helloPost(@RequestBody Person person){
        return "Czesc " + person.getName() + " masz " + person.getAge();
            }
    
}
