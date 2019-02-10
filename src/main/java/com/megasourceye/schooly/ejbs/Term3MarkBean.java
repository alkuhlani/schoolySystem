/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Term2MArk;
import com.megasourceye.schooly.entities.Term3Mark;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class Term3MarkBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Term3Mark obj) {
        dao.create(obj);
    }

    public List<Term3Mark> findAll() {
        return dao.findAll("Term3Mark.findAll", Term3Mark.class);
    }

    public List<Term3Mark> findBySchoolYearId(Long id) {
        return dao.findAll("Term3Mark.findBySchoolYearId", Term3Mark.class, id);
    }

    public List<Term3Mark> findByTermId(Long id) {
        return dao.findAll("Term3Mark.findByTermId", Term3Mark.class, id);
    }
    public List<Term3Mark> findBySchoolYearIdAndStudentId(Long id,Long id1) {
        return dao.findAll("Term3Mark.findBySchoolYearIdAndStudentId", Term3Mark.class, id,id1);
    }
    public List<Term3Mark> findBySchoolYearIdAndStudentIdAndTermID(Long schoolYearId,Long studentId,Long termId) {
        return dao.findAll("Term3Mark.findBySchoolYearIdAndStudentIdAndTermID", Term3Mark.class, schoolYearId,studentId,termId);
    }
    public List<Term3Mark> findByStudentSAndSubjectIdAndTermId(Long studentId,Long subjectId,Long termId) {
        return dao.findAll("Term3Mark.findByStudentSAndSubjectIdAndTermId", Term3Mark.class, studentId,subjectId,termId);
    }

    
    public Term3Mark find(Long id) {
        return dao.find(id, Term3Mark.class);
    }

    public void update(Term3Mark obj) {
        dao.update(obj);
    }

    public void delete(Term3Mark obj) {
        dao.delete(obj);
    }
}
