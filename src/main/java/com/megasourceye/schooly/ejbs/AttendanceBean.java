/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.ejbs;

import com.megasourceye.schooly.entities.Attendance;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.StudentSchool;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Stateless
public class AttendanceBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Inject
    private DataAccessObject dao;

    public void create(Attendance obj) {
        dao.create(obj);
    }

    public List<Attendance> findAll() {
        return dao.findAll("Attendance.findAll", Attendance.class);
    }

    public List<Attendance> findBySchoolId(Long id) {
        return dao.findAll("Attendance.findBySchoolId", Attendance.class, id);
    }

    public List<Attendance> findBySchoolYearId(Long id) {
        return dao.findAll("Attendance.findBySchoolYearId", Attendance.class, id);
    }

    public List<Attendance> findBySchoolYearIdAndDate(Long id,Date id1) {
        return dao.findAll("Attendance.findBySchoolYearIdAndDate", Attendance.class, id,id1);
    }
    public List<Attendance> findBySchoolYearIdAnd2Date(Long id,Date id1,Date id2) {
        return dao.findAll("Attendance.findBySchoolYearIdAnd2Date", Attendance.class, id,id1,id2);
    }
  
    public List<Attendance> findBySchoolYearIdAndClassId(Long id, Long id1) {
        return dao.findAll("Attendance.findBySchoolYearIdAndClassId", Attendance.class, id, id1);
    }

    public List<Attendance> findBySchoolYearIdAndClassIdAnd2Date(Long id, Long id1, Date id2, Date id3) {
        return dao.findAll("Attendance.findBySchoolYearIdAndClassIdAnd2Date", Attendance.class, id, id1, id2, id3);
    }
    public List<Attendance> findBySchoolYearIdAndClassIdAndDate(Long id, Long id1, Date id2) {
        return dao.findAll("Attendance.findBySchoolYearIdAndClassIdAndDate", Attendance.class, id, id1, id2);
    }

    public List<Attendance> findByStudentIdAndDate(Long id, Date id1) {
        return dao.findAll("Attendance.findByStudentIdAndDate", Attendance.class, id, id1);
    }

    public List<Attendance> findByClassIdAndDate(Long id, Date id1) {
        return dao.findAll("Attendance.findByClassIdAndDate", Attendance.class, id, id1);
    }

    public List<Attendance> findBySchoolIdAndDate(Long id, Date id1) {
        return dao.findAll("Attendance.findBySchoolIdAndDate", Attendance.class, id, id1);
    }

    public List<Attendance> findByStudentSIdAnd2Date(Long id, Date id1, Date id2) {
        return dao.findAll("Attendance.findByStudentSIdAnd2Date", Attendance.class, id, id1, id2);
    }

    public Attendance find(Long id) {
        return dao.find(id, Attendance.class);
    }

    public void update(Attendance obj) {
        dao.update(obj);
    }

    public void delete(Attendance obj) {
        dao.delete(obj);
    }

}
