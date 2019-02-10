/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.TermMonth;
import com.megasourceye.schooly.entities.TermWeek;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class TermMonthBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(TermMonth obj) {
        dao.create(obj);
    }

    public List<TermMonth> findAll() {
        return dao.findAll("TermMonth.findAll", TermMonth.class);
    }

    public List<TermMonth> findBySchoolYearId(Long id) {
        return dao.findAll("TermMonth.findBySchoolYearId", TermMonth.class, id);
    }
    public List<TermMonth> findBySchoolYearIdAndTermId(Long id,Long id2) {
        return dao.findAll("TermMonth.findBySchoolYearIdAndTermId", TermMonth.class, id,id2);
    }

    public TermMonth find(Long id) {
        return dao.find(id, TermMonth.class);
    }

    public void update(TermMonth obj) {
        dao.update(obj);
    }

    public void delete(TermMonth obj) {
        dao.delete(obj);
    }

}
