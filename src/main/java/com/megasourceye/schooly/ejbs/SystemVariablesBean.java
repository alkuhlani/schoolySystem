/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.SystemVariables;
import com.megasourceye.schooly.entities.Term;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class SystemVariablesBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(SystemVariables obj) {
        dao.create(obj);
    }

    public List<SystemVariables> findAll() {
        return dao.findAll("SystemVariables.findAll", SystemVariables.class);
    }

    public List<SystemVariables> findBySchoolId(Long id) {
        return dao.findAll("SystemVariables.findBySchoolId", SystemVariables.class, id);
    }

    public SystemVariables findBySchool(Long id) {
        return dao.findBy("SystemVariables.findBySchool", SystemVariables.class,id);
    }
    public SystemVariables find(Long id) {
        return dao.find(id, SystemVariables.class);
    }

    public void update(SystemVariables obj) {
        dao.update(obj);
    }

    public void delete(SystemVariables obj) {
        dao.delete(obj);
    }

}
