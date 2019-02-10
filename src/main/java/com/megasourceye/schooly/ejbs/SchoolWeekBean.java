/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.SchoolWeek;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class SchoolWeekBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(SchoolWeek obj) {
        dao.create(obj);
    }

    public List<SchoolWeek> findAll() {
        return dao.findAll("SchoolWeek.findAll", SchoolWeek.class);
    }

    public List<SchoolWeek> findBySchoolYearId(Long id) {
        return dao.findAll("SchoolWeek.findBySchoolYearId", SchoolWeek.class, id);
    }
    public List<SchoolWeek> findByTermId(Long id) {
        return dao.findAll("SchoolWeek.findByTermId", SchoolWeek.class, id);
    }
    public List<SchoolWeek> findByTermIdandTermType(Long id,Character id1) {
        return dao.findAll("SchoolWeek.findByTermIdandTermType", SchoolWeek.class, id,id1);
    }
    
    public SchoolWeek find(Long id) {
        return dao.find(id, SchoolWeek.class);
    }

    public void update(SchoolWeek obj) {
        dao.update(obj);
    }

    public void delete(SchoolWeek obj) {
        dao.delete(obj);
    }
}
