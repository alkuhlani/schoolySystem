/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ExamTable.findAll", query = "SELECT q FROM ExamTable q")
    ,
    @NamedQuery(name = "ExamTable.findBySchoolYearId", query = "SELECT q FROM ExamTable q where q.exam.schoolYear.id=:id ORDER BY q.id DESC")
    ,
@NamedQuery(name = "ExamTable.findBySchoolYearIdAndSubjectIdAndClassId", query = "SELECT q FROM ExamTable q where q.exam.schoolYear.id=:id and q.subject_t.id=:id1 and q.class_.id=:id2 ORDER BY q.id DESC")})

public class ExamTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name;
    @ManyToOne
    private Class_ class_;
    @ManyToOne
    private Subject_t subject_t;
    private Character termHalfType;// if in the first of ter =1 - if in last half will be =2
    //willbe removed
    @Temporal(TemporalType.TIME)
    private Date beginTime;
    @Temporal(TemporalType.DATE)
    private Date examDate;
    @Temporal(TemporalType.TIME)
    private Date examTimeDuring;
    @ManyToOne
    private Exam exam;

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

    public Class_ getClass_() {
        return class_;
    }

    public void setClass_(Class_ class_) {
        this.class_ = class_;
    }

    public Subject_t getSubject_t() {
        return subject_t;
    }

    public void setSubject_t(Subject_t subject_t) {
        this.subject_t = subject_t;
    }

    public Character getTermHalfType() {
        return termHalfType;
    }

    public void setTermHalfType(Character termHalfType) {
        this.termHalfType = termHalfType;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Date getExamTimeDuring() {
        return examTimeDuring;
    }

    public void setExamTimeDuring(Date examTimeDuring) {
        this.examTimeDuring = examTimeDuring;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
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
        if (!(object instanceof ExamTable)) {
            return false;
        }
        ExamTable other = (ExamTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.ExamTable[ id=" + id + " ]";
    }

}
