/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.SchoolYear;
import com.megasourceye.schooly.entities.StaticClass;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class StaticClassBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(StaticClass obj) {
        dao.create(obj);
    }

    public List<StaticClass> findAll() {
        return dao.findAll("StaticClass.findAll", StaticClass.class);
    }
   
    public StaticClass find(Long id) {
        return dao.find(id, StaticClass.class);
    }

    public void update(StaticClass obj) {
        dao.update(obj);
    }

    public void delete(StaticClass obj) {
        dao.delete(obj);
    }
}
