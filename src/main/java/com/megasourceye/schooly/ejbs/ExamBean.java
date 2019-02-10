/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Exam;
import com.megasourceye.schooly.entities.Term;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class ExamBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Exam obj) {
        dao.create(obj);
    }

    public List<Exam> findAll() {
        return dao.findAll("Exam.findAll", Exam.class);
    }

    public List<Exam> findBySchoolYearId(Long id) {
        return dao.findAll("Exam.findBySchoolYearId", Exam.class, id);
    }

    public List<Exam> findBySchoolYearIdAndTermWeek(Long id, Long id1) {
        return dao.findAll("Exam.findBySchoolYearIdAndTermWeek", Exam.class, id, id1);
    }

    public List<Exam> findBySchoolYearIdAndTermMonth(Long id, Long id1) {
        return dao.findAll("Exam.findBySchoolYearIdAndTermMonth", Exam.class, id, id1);
    }
    public List<Exam> findBySchoolYearIdAndIsActive(Long id) {
        return dao.findAll("Exam.findBySchoolYearIdAndIsActive", Exam.class, id);
    }

    public Exam find(Long id) {
        return dao.find(id, Exam.class);
    }

    public void update(Exam obj) {
        dao.update(obj);
    }

    public void delete(Exam obj) {
        dao.delete(obj);
    }
}
