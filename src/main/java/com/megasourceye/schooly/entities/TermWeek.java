/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
//عدد الاسابيع في الترم الواحد     
//for system 2 term called TermMonth
@Entity
@NamedQueries({
        
    @NamedQuery(name = "TermWeek.findAll", query = "SELECT q FROM TermWeek q"),
@NamedQuery(name = "TermWeek.findBySchoolYearId", query = "SELECT q FROM TermWeek q WHERE q.schoolYear.id=:id ORDER BY q.term.id ASC"),
@NamedQuery(name = "TermWeek.findBySchoolYearIdAndTermId", query = "SELECT q FROM TermWeek q WHERE q.schoolYear.id=:id and q.term.id=:id1")})
public class TermWeek implements Serializable {

    @OneToMany(mappedBy = "termWeek")
    private List<Exam> exams;

    @OneToMany(mappedBy = "termWeek")
    private List<Term3Mark> term3Marks;

    

    @OneToMany(mappedBy = "termWeek")
    private List<SchoolWeek> schoolWeeks;

    @OneToOne(mappedBy = "termWeek")
    private SystemVariables systemVariables;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name_ar;
    @Size(max = 255)
    private String name_en;
    @ManyToOne
    private Term term; // الترم المقصود
    @ManyToOne
    private SchoolYear schoolYear;
    @ManyToOne
    private School school; // ليس له داعي يتم احتساب المدرسة من العام الدراسي
    private Short noFirst; //عدد اسابيع النصف الاول من الترم
    @Temporal(TemporalType.DATE) 
    private Date first1Date; // تاريخ بداية النص الاول من الترم
    @Temporal(TemporalType.DATE) 
    private Date end1Date; // تاريخ نهاية النص الاول من الترم
    private Short noEnd; //عدد اسابيع النصف الثاني من الترم
    @Temporal(TemporalType.DATE) 
    private Date first2Date; // تاريخ بداية النص الثاني من الترم
    @Temporal(TemporalType.DATE) 
    private Date end2Date; // تاريخ نهاية النص الثاني من الترم

    public TermWeek() {
    }

    public TermWeek(Long id) {
        this.id = id;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SystemVariables getSystemVariables() {
        return systemVariables;
    }

    public void setSystemVariables(SystemVariables systemVariables) {
        this.systemVariables = systemVariables;
    }

    public String getName_ar() {
        return name_ar;
    }

    public void setName_ar(String name_ar) {
        this.name_ar = name_ar;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Short getNoFirst() {
        return noFirst;
    }

    public void setNoFirst(Short noFirst) {
        this.noFirst = noFirst;
    }

    public Date getFirst1Date() {
        return first1Date;
    }

    public void setFirst1Date(Date first1Date) {
        this.first1Date = first1Date;
    }

    public Date getEnd1Date() {
        return end1Date;
    }

    public void setEnd1Date(Date end1Date) {
        this.end1Date = end1Date;
    }

    public Short getNoEnd() {
        return noEnd;
    }

    public void setNoEnd(Short noEnd) {
        this.noEnd = noEnd;
    }

    public Date getFirst2Date() {
        return first2Date;
    }

    public void setFirst2Date(Date first2Date) {
        this.first2Date = first2Date;
    }

    public Date getEnd2Date() {
        return end2Date;
    }

    public void setEnd2Date(Date end2Date) {
        this.end2Date = end2Date;
    }

    public List<SchoolWeek> getSchoolWeeks() {
        return schoolWeeks;
    }

    public void setSchoolWeeks(List<SchoolWeek> schoolWeeks) {
        this.schoolWeeks = schoolWeeks;
    }

    public List<Term3Mark> getTerm3Marks() {
        return term3Marks;
    }

    public void setTerm3Marks(List<Term3Mark> term3Marks) {
        this.term3Marks = term3Marks;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
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
        if (!(object instanceof TermWeek)) {
            return false;
        }
        TermWeek other = (TermWeek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.TermWeek[ id=" + id + " ]";
    }
    
}
