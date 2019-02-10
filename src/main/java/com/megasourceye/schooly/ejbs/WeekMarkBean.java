/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Vacation;
import com.megasourceye.schooly.entities.WeekMark;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class WeekMarkBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(WeekMark obj) {
        dao.create(obj);
    }

    public List<WeekMark> findAll() {
        return dao.findAll("WeekMark.findAll", WeekMark.class);
    }

    public List<WeekMark> findByStudentSIdAndTeacherAssignAndSchoolW(Long id,Long id1,Long id2) {
        return dao.findAll("WeekMark.findByStudentSIdAndTeacherAssignAndSchoolW", WeekMark.class, id,id1,id2);
    }
    public List<WeekMark> findByStudentSIdAndSubjectIdAndTermWeekAndTermT(Long id,Long id1,Long id2,Character id3) {
        return dao.findAll("WeekMark.findByStudentSIdAndSubjectIdAndTermWeekAndTermT", WeekMark.class, id,id1,id2,id3);
    }
    public List<WeekMark> findByTeacherIdAndSchoolWeek(Long id,Long id1) {
        return dao.findAll("WeekMark.findByTeacherIdAndSchoolWeek", WeekMark.class, id,id1);
    }
    public List<WeekMark> findBySchoolYearAndTeacherId(Long id,Long id1) {
        return dao.findAll("WeekMark.findBySchoolYearAndTeacherId", WeekMark.class, id,id1);
    }
    public List<WeekMark> findBySchoolYearAndStudentId(Long id,Long id1) {
        return dao.findAll("WeekMark.findBySchoolYearAndStudentId", WeekMark.class, id,id1);
    }
    public List<WeekMark> findBySchoolYearIdAndSchoolWeek(Long id,Long id1) {
        return dao.findAll("WeekMark.findBySchoolYearIdAndSchoolWeek", WeekMark.class, id,id1);
    }
    public List<WeekMark> findBySchoolYearId(Long id) {
        return dao.findAll("WeekMark.findBySchoolYearId", WeekMark.class, id);
    }
    public List<WeekMark> findByStudentIdAndSchoolW(Long id,Long id1) {
        return dao.findAll("WeekMark.findByStudentIdAndSchoolW", WeekMark.class, id,id1);
    }
    public List<WeekMark> findBySchoolYearIdAndStudentId(Long id,Long id1) {
        return dao.findAll("WeekMark.findBySchoolYearIdAndStudentId", WeekMark.class, id,id1);
    }
    
            

    public WeekMark find(Long id) {
        return dao.find(id, WeekMark.class);
    }

    public void update(WeekMark obj) {
        dao.update(obj);
    }

    public void delete(WeekMark obj) {
        dao.delete(obj);
    }
}
