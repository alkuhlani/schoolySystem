/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.SchoolMonth;
import com.megasourceye.schooly.entities.SchoolWeek;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class SchoolMonthBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(SchoolMonth obj) {
        dao.create(obj);
    }

    public List<SchoolMonth> findAll() {
        return dao.findAll("SchoolMonth.findAll", SchoolMonth.class);
    }

    public List<SchoolMonth> findBySchoolYearId(Long id) {
        return dao.findAll("SchoolMonth.findBySchoolYearId", SchoolMonth.class, id);
    }
    public List<SchoolMonth> findByTermId(Long id) {
        return dao.findAll("SchoolMonth.findByTermId", SchoolMonth.class, id);
    }
    public List<SchoolMonth> findBySchoolYearIdAndTermMonthId(Long id,Long id1) {
        return dao.findAll("SchoolMonth.findBySchoolYearIdAndTermMonthId", SchoolMonth.class, id,id1);
    }
    

    public SchoolMonth find(Long id) {
        return dao.find(id, SchoolMonth.class);
    }

    public void update(SchoolMonth obj) {
        dao.update(obj);
    }

    public void delete(SchoolMonth obj) {
        dao.delete(obj);
    }
}
