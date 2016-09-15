/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.wrobel.damian.services;

import com.example.Person;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

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

}
