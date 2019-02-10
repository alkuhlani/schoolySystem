/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.MonthMark;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class MonthMarkBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(MonthMark obj) {
        dao.create(obj);
    }

    public List<MonthMark> findAll() {
        return dao.findAll("MonthMark.findAll", MonthMark.class);
    }
    public List<MonthMark> findBySchoolYearId(Long id) {
        return dao.findAll("MonthMark.findBySchoolYearId", MonthMark.class,id);
    }
    public List<MonthMark> findBySchoolYearIdAndTeacherId(Long id,Long id1) {
        return dao.findAll("MonthMark.findBySchoolYearIdAndTeacherId", MonthMark.class,id,id1);
    }
    public List<MonthMark> findBySchoolYearIdAndStudentId(Long id,Long id1) {
        return dao.findAll("MonthMark.findBySchoolYearIdAndStudentId", MonthMark.class,id,id1);
    }
    public List<MonthMark> findByStudentSIdAndTeacherAssignAndSchoolW(Long id, Long id1, Long id2) {
        return dao.findAll("MonthMark.findByStudentSIdAndTeacherAssignAndSchoolW", MonthMark.class, id, id1, id2);
    }
    public List<MonthMark> findByStudentSIdAndSubjectIdAndTermMonth(Long id, Long id1, Long id2) {
        return dao.findAll("MonthMark.findByStudentSIdAndSubjectIdAndTermMonth", MonthMark.class, id, id1, id2);
    }

    public MonthMark find(Long id) {
        return dao.find(id, MonthMark.class);
    }

    public void update(MonthMark obj) {
        dao.update(obj);
    }

    public void delete(MonthMark obj) {
        dao.delete(obj);
    }
}
