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
    @NamedQuery(name = "SchoolWeek.findAll", query = "SELECT q FROM SchoolWeek q ")
    ,
@NamedQuery(name = "SchoolWeek.findBySchoolYearId", query = "SELECT q FROM SchoolWeek q WHERE q.schoolYear.id=:id")
    ,
@NamedQuery(name = "SchoolWeek.findByTermId", query = "SELECT q FROM SchoolWeek q WHERE q.termWeek.id=:id")
    ,
@NamedQuery(name = "SchoolWeek.findByTermIdandTermType", query = "SELECT q FROM SchoolWeek q WHERE q.termWeek.id=:id and q.termType=:id1")})
public class SchoolWeek implements Serializable {

    @OneToMany(mappedBy = "schoolWeek")
    private List<Exam> exams;

    @OneToOne(mappedBy = "schoolWeek")
    private SystemVariables systemVariables;

    @OneToMany(mappedBy = "schoolWeek")
    private List<WeekMark> weekMarks;

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
    private TermWeek termWeek;
    @Temporal(TemporalType.DATE)
    private Date firstDate; // تاريخ بداية الاسبوع
    @Temporal(TemporalType.DATE)
    private Date endDate; // تاريخ نهاية الاسبوع
    private Character termType; // نوع الترم - نصف الترم الاول= 1 - النصف الثاني من الترم =2

    public SchoolWeek() {
    }

    public SchoolWeek(Long id) {
        this.id = id;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<WeekMark> getWeekMarks() {
        return weekMarks;
    }

    public void setWeekMarks(List<WeekMark> weekMarks) {
        this.weekMarks = weekMarks;
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

    public TermWeek getTermWeek() {
        return termWeek;
    }

    public void setTermWeek(TermWeek termWeek) {
        this.termWeek = termWeek;
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

    public Character getTermType() {
        return termType;
    }

    public void setTermType(Character termType) {
        this.termType = termType;
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
        if (!(object instanceof SchoolWeek)) {
            return false;
        }
        SchoolWeek other = (SchoolWeek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.SchoolWeek[ id=" + id + " ]";
    }

}
