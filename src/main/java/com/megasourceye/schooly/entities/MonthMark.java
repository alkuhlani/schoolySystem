/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "MonthMark.findAll", query = "SELECT q FROM MonthMark q")
    ,
    @NamedQuery(name = "MonthMark.findBySchoolYearId", query = "SELECT q FROM MonthMark q where q.studentSchool.schoolYear.id=:id")
    ,
    @NamedQuery(name = "MonthMark.findBySchoolYearIdAndTeacherId", query = "SELECT q FROM MonthMark q where q.studentSchool.schoolYear.id=:id and q.teacherAssign.teacher.id=:id1")
    ,
    @NamedQuery(name = "MonthMark.findBySchoolYearIdAndStudentId", query = "SELECT q FROM MonthMark q where q.studentSchool.schoolYear.id=:id and q.studentSchool.student.id=:id1")
    ,
    
@NamedQuery(name = "MonthMark.findByStudentSIdAndTeacherAssignAndSchoolW", query = "SELECT q FROM MonthMark q WHERE q.studentSchool.id=:id AND q.teacherAssign.id=:id1 AND q.schoolMonth.id=:id2")
    ,
@NamedQuery(name = "MonthMark.findByStudentSIdAndSubjectIdAndTermMonth", query = "SELECT q FROM MonthMark q WHERE q.studentSchool.id=:id AND q.teacherAssign.subject_t.id=:id1 AND q.schoolMonth.termMonth.id=:id2")})
public class MonthMark implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name;
    @ManyToOne
    private StudentSchool studentSchool;
    @ManyToOne
    private Subject_t subject_t;
    private Float hw;
    private Float writing;
    private Float reading;
    private Float behavior;
    private Float total; // المجموع
    private Boolean isApproval;//true if he approval
    @ManyToOne
    private TeacherAssign teacherAssign;
    @ManyToOne
    private SchoolMonth schoolMonth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public StudentSchool getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(StudentSchool studentSchool) {
        this.studentSchool = studentSchool;
    }

    public Subject_t getSubject_t() {
        return subject_t;
    }

    public void setSubject_t(Subject_t subject_t) {
        this.subject_t = subject_t;
    }

    public Float getHw() {
        return hw;
    }

    public void setHw(Float hw) {
        this.hw = hw;
    }

    public Float getWriting() {
        return writing;
    }

    public void setWriting(Float writing) {
        this.writing = writing;
    }

    public Float getReading() {
        return reading;
    }

    public void setReading(Float reading) {
        this.reading = reading;
    }

    public Float getBehavior() {
        return behavior;
    }

    public void setBehavior(Float behavior) {
        this.behavior = behavior;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Boolean getIsApproval() {
        return isApproval;
    }

    public void setIsApproval(Boolean isApproval) {
        this.isApproval = isApproval;
    }

  

    public TeacherAssign getTeacherAssign() {
        return teacherAssign;
    }

    public void setTeacherAssign(TeacherAssign teacherAssign) {
        this.teacherAssign = teacherAssign;
    }

    public SchoolMonth getSchoolMonth() {
        return schoolMonth;
    }

    public void setSchoolMonth(SchoolMonth schoolMonth) {
        this.schoolMonth = schoolMonth;
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
        if (!(object instanceof MonthMark)) {
            return false;
        }
        MonthMark other = (MonthMark) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.MonthMark[ id=" + id + " ]";
    }

}
