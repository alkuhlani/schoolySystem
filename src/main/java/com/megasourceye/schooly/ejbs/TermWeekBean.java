/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.TermMonth;
import com.megasourceye.schooly.entities.TermType;
import com.megasourceye.schooly.entities.TermWeek;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class TermWeekBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(TermWeek obj) {
        dao.create(obj);
    }

    public List<TermWeek> findAll() {
        return dao.findAll("TermWeek.findAll", TermWeek.class);
    }

    public List<TermWeek> findBySchoolYearId(Long id) {
        return dao.findAll("TermWeek.findBySchoolYearId", TermWeek.class, id);
    }

    public List<TermWeek> findBySchoolYearIdAndTermId(Long id, Long id2) {
        return dao.findAll("TermWeek.findBySchoolYearIdAndTermId", TermWeek.class, id, id2);
    }
    

    public TermWeek find(Long id) {
        return dao.find(id, TermWeek.class);
    }

    public void update(TermWeek obj) {
        dao.update(obj);
    }

    public void delete(TermWeek obj) {
        dao.delete(obj);
    }

}
