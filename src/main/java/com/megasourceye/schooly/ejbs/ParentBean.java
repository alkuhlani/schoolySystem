/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Parent;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class ParentBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Parent obj) {
        dao.create(obj);
    }

    public List<Parent> findAll() {
        return dao.findAll("Parent.findAll", Parent.class);
    }

    public List<Parent> findBySchoolId(Long id) {
        return dao.findAll("Parent.findBySchoolId", Parent.class, id);
    }

    public Parent find(Long id) {
        return dao.find(id, Parent.class);
    }

    public void update(Parent obj) {
        dao.update(obj);
    }

    public void delete(Parent obj) {
        dao.delete(obj);
    }

}
