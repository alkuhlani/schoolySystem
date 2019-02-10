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
    @NamedQuery(name = "Term2MArk.findAll", query = "SELECT q FROM Term2MArk q"),
@NamedQuery(name = "Term2MArk.findBySchoolYearId", query = "SELECT q FROM Term2MArk q WHERE q.studentSchool.schoolYear.id=:id"),
@NamedQuery(name = "Term2MArk.findByStudentSAndSubjectId", query = "SELECT q FROM Term2MArk q WHERE q.studentSchool.id=:id AND q.subject_t.id=:id1"),
@NamedQuery(name = "Term2MArk.findByStudentSAndSubjectIdAndTermId", query = "SELECT q FROM Term2MArk q WHERE q.studentSchool.id=:id AND q.subject_t.id=:id1 and q.termMonth.id=:id2")})
public class Term2MArk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name;
    private Float Mark;
    private Float Exam;
    
    @ManyToOne
    private StudentSchool studentSchool;
    @ManyToOne
    private Subject_t subject_t;
    @ManyToOne
    private TermMonth termMonth;
    private Boolean isApproval;//true if he approval

    public Term2MArk() {
    }

    public Term2MArk(Long id) {
        this.id = id;
    }

    
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

   

    public Float getMark() {
        return Mark;
    }

    public void setMark(Float Mark) {
        this.Mark = Mark;
    }

    public Float getExam() {
        return Exam;
    }

    public void setExam(Float Exam) {
        this.Exam = Exam;
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

    public TermMonth getTermMonth() {
        return termMonth;
    }

    public void setTermMonth(TermMonth termMonth) {
        this.termMonth = termMonth;
    }

    public Boolean getIsApproval() {
        return isApproval;
    }

    public void setIsApproval(Boolean isApproval) {
        this.isApproval = isApproval;
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
        if (!(object instanceof Term2MArk)) {
            return false;
        }
        Term2MArk other = (Term2MArk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.Term2MArk[ id=" + id + " ]";
    }

}
