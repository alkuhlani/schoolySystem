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
    @NamedQuery(name = "Term3Mark.findAll", query = "SELECT q FROM Term3Mark q ORDER BY q.id DESC"),
@NamedQuery(name = "Term3Mark.findBySchoolYearId", query = "SELECT q FROM Term3Mark q WHERE q.studentSchool.schoolYear.id=:id ORDER BY q.id DESC"),
@NamedQuery(name = "Term3Mark.findByStudentSAndSubjectId", query = "SELECT q FROM Term3Mark q WHERE q.studentSchool.id=:id AND q.subject_t.id=:id1 ORDER BY q.id DESC"),
@NamedQuery(name = "Term3Mark.findByStudentSAndSubjectIdAndTermId", query = "SELECT q FROM Term3Mark q WHERE q.studentSchool.id=:id AND q.subject_t.id=:id1 and q.termWeek.id=:id2 ORDER BY q.id DESC"),
@NamedQuery(name = "Term3Mark.findBySchoolYearIdAndStudentId", query = "SELECT q FROM Term3Mark q WHERE q.studentSchool.schoolYear.id=:id AND q.studentSchool.student.id=:id1 ORDER BY q.id DESC")})
public class Term3Mark implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name1;
    @Size(max = 255)
    private String name2;
    private Float firstHalfMark;
    private Float firstHalfExam;
    private Float SecondHalfMark;
    private Float SecondHalfExam;
    private Boolean isHalfTerm1;//true if approval - false if not
    private Boolean isHalfTerm2;//true if approval - false if not
    @ManyToOne
    private StudentSchool studentSchool;
    @ManyToOne
    private Subject_t subject_t;
    @ManyToOne
    private TermWeek termWeek;
    private Boolean isApproval;//true if he approval

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public Float getFirstHalfMark() {
        return firstHalfMark;
    }

    public void setFirstHalfMark(Float firstHalfMark) {
        this.firstHalfMark = firstHalfMark;
    }

    public Float getFirstHalfExam() {
        return firstHalfExam;
    }

    public void setFirstHalfExam(Float firstHalfExam) {
        this.firstHalfExam = firstHalfExam;
    }

    public Float getSecondHalfMark() {
        return SecondHalfMark;
    }

    public void setSecondHalfMark(Float SecondHalfMark) {
        this.SecondHalfMark = SecondHalfMark;
    }

    public Float getSecondHalfExam() {
        return SecondHalfExam;
    }

    public void setSecondHalfExam(Float SecondHalfExam) {
        this.SecondHalfExam = SecondHalfExam;
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

    public TermWeek getTermWeek() {
        return termWeek;
    }

    public void setTermWeek(TermWeek termWeek) {
        this.termWeek = termWeek;
    }

    public Boolean getIsApproval() {
        return isApproval;
    }

    public void setIsApproval(Boolean isApproval) {
        this.isApproval = isApproval;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public Boolean getIsHalfTerm1() {
        return isHalfTerm1;
    }

    public void setIsHalfTerm1(Boolean isHalfTerm1) {
        this.isHalfTerm1 = isHalfTerm1;
    }

    public Boolean getIsHalfTerm2() {
        return isHalfTerm2;
    }

    public void setIsHalfTerm2(Boolean isHalfTerm2) {
        this.isHalfTerm2 = isHalfTerm2;
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
        if (!(object instanceof Term3Mark)) {
            return false;
        }
        Term3Mark other = (Term3Mark) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.Term3Mark[ id=" + id + " ]";
    }

}
