/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class ClassBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Class_ obj) {
        dao.create(obj);
    }

    public List<Class_> findAll() {
        return dao.findAll("Class_.findAll", Class_.class);
    }

    public List<Class_> findBySchoolId(Long id) {
        return dao.findAll("Class_.findBySchoolId", Class_.class, id);
    }   

    public Class_ find(Long id) {
        return dao.find(id, Class_.class);
    }

    public void update(Class_ obj) {
        dao.update(obj);
    }

    public void delete(Class_ obj) {
        dao.delete(obj);
    }

}
