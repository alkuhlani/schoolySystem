/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Attendance.findAll", query = "SELECT q FROM Attendance q ORDER BY q.id DESC")
    ,
@NamedQuery(name = "Attendance.findByStudentIdAndDate", query = "SELECT q FROM Attendance q WHERE q.studentSchool.id=:id and q.attendanceDate=:id1 ORDER BY q.id DESC")
    ,
@NamedQuery(name = "Attendance.findBySchoolYearId", query = "SELECT q FROM Attendance q WHERE q.studentSchool.schoolYear.id=:id ORDER BY q.id DESC" ),
@NamedQuery(name = "Attendance.findBySchoolYearIdAndDate", query = "SELECT q FROM Attendance q WHERE q.studentSchool.schoolYear.id=:id and q.attendanceDate=:id1 ORDER BY q.id DESC"),
@NamedQuery(name = "Attendance.findBySchoolYearIdAnd2Date", query = "SELECT q FROM Attendance q WHERE q.studentSchool.schoolYear.id=:id and q.attendanceDate>=:id1 AND q.attendanceDate<=:id2 ORDER BY q.id DESC") // school year 2 date
    ,
@NamedQuery(name = "Attendance.findBySchoolYearIdAndClassId", query = "SELECT q FROM Attendance q WHERE q.studentSchool.schoolYear.id=:id AND q.studentSchool.class_.id=:id1 ORDER BY q.id DESC")
    ,
@NamedQuery(name = "Attendance.findBySchoolYearIdAndClassIdAnd2Date", query = "SELECT q FROM Attendance q WHERE q.studentSchool.schoolYear.id=:id AND q.studentSchool.class_.id=:id1  AND q.attendanceDate>=:id2 AND q.attendanceDate<=:id3 ORDER BY q.id DESC"),
@NamedQuery(name = "Attendance.findBySchoolYearIdAndClassIdAndDate", query = "SELECT q FROM Attendance q WHERE q.studentSchool.schoolYear.id=:id AND q.studentSchool.class_.id=:id1  AND q.attendanceDate=:id2 ORDER BY q.id DESC")
    ,
@NamedQuery(name = "Attendance.findBySchoolIdAndDate", query = "SELECT q FROM Attendance q WHERE q.studentSchool.schoolYear.school.id=:id and q.attendanceDate=:id1 ORDER BY q.id DESC")
    ,
@NamedQuery(name = "Attendance.findByClassIdAndDate", query = "SELECT q FROM Attendance q WHERE  q.studentSchool.class_.id=:id and q.attendanceDate=:id1  ORDER BY q.id DESC")
    ,
@NamedQuery(name = "Attendance.findByStudentSIdAnd2Date", query = "SELECT q FROM Attendance q WHERE q.studentSchool.student.id=:id AND q.attendanceDate>=:id1 AND q.attendanceDate<=:id2 ORDER BY q.id DESC")})
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "attendanceDate")
    @Temporal(TemporalType.DATE)
    private Date attendanceDate;
    @ManyToOne
    private StudentSchool studentSchool;
    @Column(name = "status")
    private Character status;//0 ab; 1 at; 2 si

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  
    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public StudentSchool getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(StudentSchool studentSchool) {
        this.studentSchool = studentSchool;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attendance)) {
            return false;
        }
        Attendance other = (Attendance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.Attendance[ id=" + id + " ]";
    }

}
