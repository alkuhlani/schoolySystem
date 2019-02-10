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
@Entity
@NamedQueries({
    @NamedQuery(name = "SchoolMonth.findAll", query = "SELECT q FROM SchoolMonth q ")
    ,
@NamedQuery(name = "SchoolMonth.findBySchoolYearId", query = "SELECT q FROM SchoolMonth q WHERE q.schoolYear.id=:id"),
@NamedQuery(name = "SchoolMonth.findBySchoolYearIdAndTermMonthId", query = "SELECT q FROM SchoolMonth q WHERE q.schoolYear.id=:id AND q.termMonth.id=:id1")
    ,
@NamedQuery(name = "SchoolMonth.findByTermId", query = "SELECT q FROM SchoolMonth q WHERE q.termMonth.id=:id")})
public class SchoolMonth implements Serializable {

    @OneToMany(mappedBy = "schoolMonth")
    private List<Exam> exams;

    @OneToOne(mappedBy = "schoolMonth")
    private SystemVariables systemVariables;

    @OneToMany(mappedBy = "schoolMonth")
    private List<MonthMark> monthMarks;

    @OneToMany(mappedBy = "schoolMonth")
    private List<SystemVariables> systemVariabless;


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name_ar;
    @Size(max = 255)
    private String name_en;
    @ManyToOne
    private SchoolYear schoolYear;
    @ManyToOne
    private TermMonth termMonth;
    @Temporal(TemporalType.DATE)
    private Date firstDate; // تاريخ بداية الشهر
    @Temporal(TemporalType.DATE)
    private Date endDate; // تاريخ نهاية الشهر

    public SchoolMonth() {
    }

    public SchoolMonth(Long id) {
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

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

    public TermMonth getTermMonth() {
        return termMonth;
    }

    public void setTermMonth(TermMonth termMonth) {
        this.termMonth = termMonth;
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

    public List<SystemVariables> getSystemVariabless() {
        return systemVariabless;
    }

    public void setSystemVariabless(List<SystemVariables> systemVariabless) {
        this.systemVariabless = systemVariabless;
    }

    public List<MonthMark> getMonthMarks() {
        return monthMarks;
    }

    public void setMonthMarks(List<MonthMark> monthMarks) {
        this.monthMarks = monthMarks;
    }

    public SystemVariables getSystemVariables() {
        return systemVariables;
    }

    public void setSystemVariables(SystemVariables systemVariables) {
        this.systemVariables = systemVariables;
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
        if (!(object instanceof SchoolMonth)) {
            return false;
        }
        SchoolMonth other = (SchoolMonth) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.SchoolMonth[ id=" + id + " ]";
    }

}
