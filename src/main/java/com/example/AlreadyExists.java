/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author damian.wrobel
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyExists extends RuntimeException {

    public AlreadyExists(int id) {
        super("Istnieje już osoba o ID  " + id);
    }
}
