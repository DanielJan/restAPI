/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.database.dao;

import com.example.Person;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author damian.wrobel
 */
public interface PersonDAO {

    Optional<Person> searchPersonBy(int id);

    Person showPerson(int id);

    void deletePerson(int id);

    void modifyPerson(int id, String name, Integer age);

    String addPerson(Person person);

    public List<Person> showPersons();
}
