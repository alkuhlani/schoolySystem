/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.SchoolYear;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class SchoolYearBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(SchoolYear obj) {
        dao.create(obj);
    }

    public List<SchoolYear> findAll() {
        return dao.findAll("SchoolYear.findAll", SchoolYear.class);
    }
   
    public List<SchoolYear> findBySchoolId(Long id) {
        return dao.findAll("SchoolYear.findBySchoolId", SchoolYear.class, id);
    }
   

    public SchoolYear findBySchool(Long id) {
        return dao.findBy("SchoolYear.findBySchoolId", SchoolYear.class, id);
    }

    public SchoolYear find(Long id) {
        return dao.find(id, SchoolYear.class);
    }

    public void update(SchoolYear obj) {
        dao.update(obj);
    }

    public void delete(SchoolYear obj) {
        dao.delete(obj);
    }
}
