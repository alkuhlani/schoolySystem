/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Exam.findAll", query = "SELECT q FROM Exam q")
    ,
@NamedQuery(name = "Exam.findBySchoolYearId", query = "SELECT q FROM Exam q where q.schoolYear.id=:id ORDER BY q.id DESC"),
@NamedQuery(name = "Exam.findBySchoolYearIdAndIsActive", query = "SELECT q FROM Exam q where q.schoolYear.id=:id AND q.isActive=TRUE ORDER BY q.id DESC"),
@NamedQuery(name = "Exam.findBySchoolYearIdAndTermWeek", query = "SELECT q FROM Exam q where q.schoolYear.id=:id and q.termWeek.id=:id1 ORDER BY q.id DESC"),
@NamedQuery(name = "Exam.findBySchoolYearIdAndTermMonth", query = "SELECT q FROM Exam q where q.schoolYear.id=:id and q.termMonth.id=:id1 ORDER BY q.id DESC")})
public class Exam implements Serializable {

    @OneToMany(mappedBy = "exam")
    private List<ExamTable> examTables;

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
    @ManyToOne
    private TermWeek termWeek;
    @ManyToOne
    private SchoolMonth schoolMonth;
    @ManyToOne
    private SchoolWeek schoolWeek;
    private Character type;
    private Boolean isActive;

    //0=اختبار الاسبوع - نظام ثلاثة اترام -له علاقة بعمود SchoolWeek
    //1=اختبار نصف الترم - نظام ثلاثة ترم- له علاقة بعمود TermWeek
    //2= اختبار نهاية الترم- نظام ثلاثة ترم- له علاقة بعمود TermWeek
    //3= اختبار شهري- نظام ترمين- له علاقة بعمود SchoolMonth
    //4= اختبار نصف السنة - يعني نهاية الترم الاول- نظام ترمين- له علاقة بعمود TermMonth
    //5= اختبار نهاية السنه - يعني نهاية الترم الثاني- نظام ترمين-له علاقة بعمود TermMonth
    
    public Exam(Long id) {
        this.id = id;
    }

    public Exam() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
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

    public TermMonth getTermMonth() {
        return termMonth;
    }

    public void setTermMonth(TermMonth termMonth) {
        this.termMonth = termMonth;
    }

    public TermWeek getTermWeek() {
        return termWeek;
    }

    public void setTermWeek(TermWeek termWeek) {
        this.termWeek = termWeek;
    }

    public SchoolMonth getSchoolMonth() {
        return schoolMonth;
    }

    public void setSchoolMonth(SchoolMonth schoolMonth) {
        this.schoolMonth = schoolMonth;
    }

    public SchoolWeek getSchoolWeek() {
        return schoolWeek;
    }

    public void setSchoolWeek(SchoolWeek schoolWeek) {
        this.schoolWeek = schoolWeek;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public List<ExamTable> getExamTables() {
        return examTables;
    }

    public void setExamTables(List<ExamTable> examTables) {
        this.examTables = examTables;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }
  
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.Exam[ id=" + id + " ]";
    }

}
