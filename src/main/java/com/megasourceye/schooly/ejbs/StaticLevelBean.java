/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.SchoolYear;
import com.megasourceye.schooly.entities.StaticLevel;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class StaticLevelBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(StaticLevel obj) {
        dao.create(obj);
    }

    public List<StaticLevel> findAll() {
        return dao.findAll("StaticLevel.findAll", StaticLevel.class);
    }

    public StaticLevel find(Long id) {
        return dao.find(id, StaticLevel.class);
    }

    public void update(StaticLevel obj) {
        dao.update(obj);
    }

    public void delete(StaticLevel obj) {
        dao.delete(obj);
    }
}
