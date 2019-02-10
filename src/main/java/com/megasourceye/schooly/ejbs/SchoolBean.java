/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.Vacation;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class SchoolBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(School obj) {
        dao.create(obj);
    }

    public List<School> findAll() {
        return dao.findAll("School.findAll", School.class);
    }

    public List<School> findByCourseId(Long id) {
        return dao.findAll("School.findByCourseId", School.class, id);
    }

    public School findBy(Long id) {
        return dao.findBy("School.findById", School.class, id);
    }

    public School find(Long id) {
        return dao.find(id, School.class);
    }

    public void update(School obj) {
        dao.update(obj);
    }

    public void delete(School obj) {
        dao.delete(obj);
    }
}
