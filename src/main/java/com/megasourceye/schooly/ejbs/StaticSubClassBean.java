/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.SchoolYear;
import com.megasourceye.schooly.entities.StaticSubClass;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class StaticSubClassBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
@Inject
    private DataAccessObject dao;

    public void create(StaticSubClass obj) {
        dao.create(obj);
    }

    public List<StaticSubClass> findAll() {
        return dao.findAll("StaticSubClass.findAll", StaticSubClass.class);
    }

    public StaticSubClass find(Long id) {
        return dao.find(id, StaticSubClass.class);
    }

    public void update(StaticSubClass obj) {
        dao.update(obj);
    }

    public void delete(StaticSubClass obj) {
        dao.delete(obj);
    }
}

