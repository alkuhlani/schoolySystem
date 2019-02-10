/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.SchoolMonth;
import com.megasourceye.schooly.entities.Term2MArk;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class Term2MarkBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Term2MArk obj) {
        dao.create(obj);
    }

    public List<Term2MArk> findAll() {
        return dao.findAll("Term2MArk.findAll", Term2MArk.class);
    }

    public List<Term2MArk> findBySchoolYearId(Long id) {
        return dao.findAll("Term2MArk.findBySchoolYearId", Term2MArk.class, id);
    }

    public List<Term2MArk> findByStudentSAndSubjectId(Long StudentId,Long SubjectId) {
        return dao.findAll("Term2MArk.findByStudentSAndSubjectId", Term2MArk.class, StudentId,SubjectId);
    }
    public List<Term2MArk> findByStudentSAndSubjectIdAndTermId(Long StudentId,Long SubjectId,Long TermId) {
        return dao.findAll("Term2MArk.findByStudentSAndSubjectIdAndTermId", Term2MArk.class, StudentId,SubjectId,TermId);
    }

    public Term2MArk find(Long id) {
        return dao.find(id, Term2MArk.class);
    }

    public void update(Term2MArk obj) {
        dao.update(obj);
    }

    public void delete(Term2MArk obj) {
        dao.delete(obj);
    }
}
