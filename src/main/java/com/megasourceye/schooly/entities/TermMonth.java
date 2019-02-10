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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "TermMonth.findAll", query = "SELECT q FROM TermMonth q")
    ,
@NamedQuery(name = "TermMonth.findBySchoolYearId", query = "SELECT q FROM TermMonth q WHERE q.schoolYear.id=:id ORDER BY q.term.id ASC")
    ,
@NamedQuery(name = "TermMonth.findBySchoolYearIdAndTermId", query = "SELECT q FROM TermMonth q WHERE q.schoolYear.id=:id and q.term.id=:id1")})
public class TermMonth implements Serializable {

    @OneToMany(mappedBy = "termMonth")
    private List<Exam> exams;

    @OneToMany(mappedBy = "termMonth")
    private List<Term2MArk> term2MArks;

    @OneToMany(mappedBy = "termMonth")
    private List<SystemVariables> systemVariabless;

    @OneToMany(mappedBy = "termMonth")
    private List<SchoolMonth> schoolMonths;

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
    private Short noMonths; //عدد اشهر الترم 
    @Temporal(TemporalType.DATE)
    private Date firstDate; // تاريخ بداية الترم
    @Temporal(TemporalType.DATE)
    private Date endDate; // تاريخ نهاية الترم

    public TermMonth() {
    }

    public TermMonth(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Short getNoMonths() {
        return noMonths;
    }

    public void setNoMonths(Short noMonths) {
        this.noMonths = noMonths;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<SchoolMonth> getSchoolMonths() {
        return schoolMonths;
    }

    public void setSchoolMonths(List<SchoolMonth> schoolMonths) {
        this.schoolMonths = schoolMonths;
    }

    public List<SystemVariables> getSystemVariabless() {
        return systemVariabless;
    }

    public void setSystemVariabless(List<SystemVariables> systemVariabless) {
        this.systemVariabless = systemVariabless;
    }

    public List<Term2MArk> getTerm2MArks() {
        return term2MArks;
    }

    public void setTerm2MArks(List<Term2MArk> term2MArks) {
        this.term2MArks = term2MArks;
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
        if (!(object instanceof TermMonth)) {
            return false;
        }
        TermMonth other = (TermMonth) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.TermMonth[ id=" + id + " ]";
    }

}
