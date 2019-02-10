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
import javax.security.auth.Subject;
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
@Entity
@NamedQueries({
    
    @NamedQuery(name = "TeacherAssign.findAll", query = "SELECT q FROM TeacherAssign q"),
@NamedQuery(name = "TeacherAssign.findBySchoolYearId", query = "SELECT q FROM TeacherAssign q WHERE q.schoolYear.id=:id"),
@NamedQuery(name = "TeacherAssign.findBySchoolYearIdAndTeacherId", query = "SELECT q FROM TeacherAssign q WHERE q.schoolYear.id=:id and q.teacher.id=:id1"),
@NamedQuery(name = "TeacherAssign.findBySchoolYearIdAndClassId", query = "SELECT q FROM TeacherAssign q WHERE q.schoolYear.id=:id and q.class_.id=:id1"),
@NamedQuery(name = "TeacherAssign.findBySchoolYearIdAndClassIdAndTeacherIdAndSubjectId", query = "SELECT q FROM TeacherAssign q WHERE q.schoolYear.id=:id and q.class_.id=:id1 AND q.teacher.id=:id2 AND q.subject_t.id=:id3"),
@NamedQuery(name = "TeacherAssign.findBySchoolYearIdAndClassIdAndSubjectId", query = "SELECT q FROM TeacherAssign q WHERE q.schoolYear.id=:id and q.class_.id=:id1 AND q.subject_t.id=:id2")})
public class TeacherAssign implements Serializable {

    @OneToMany(mappedBy = "teacherAssign")
    private List<MonthMark> monthMarks;

    

    @OneToMany(mappedBy = "teacherAssign")
    private List<WeekMark> weekMarks;



    @OneToMany(mappedBy = "teacherAssign")
    private List<SubjectTable> subjectTables;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name_ar;
    @Size(max = 255)
    private String name_en;
    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private Subject_t subject_t;
    @ManyToOne
    private Class_ class_;
    @ManyToOne
    private Term term;
    @ManyToOne
    private SchoolYear schoolYear;
  
    public TeacherAssign(Long id) {
        this.id = id;
    }

    public TeacherAssign() {
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SubjectTable> getSubjectTables() {
        return subjectTables;
    }

    public void setSubjectTables(List<SubjectTable> subjectTables) {
        this.subjectTables = subjectTables;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject_t getSubject_t() {
        return subject_t;
    }

    public void setSubject_t(Subject_t subject_t) {
        this.subject_t = subject_t;
    }

    public Class_ getClass_() {
        return class_;
    }

    public void setClass_(Class_ class_) {
        this.class_ = class_;
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

    

    public List<MonthMark> getMonthMarks() {
        return monthMarks;
    }

    public void setMonthMarks(List<MonthMark> monthMarks) {
        this.monthMarks = monthMarks;
    }

    public List<WeekMark> getWeekMarks() {
        return weekMarks;
    }

    public void setWeekMarks(List<WeekMark> weekMarks) {
        this.weekMarks = weekMarks;
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
        if (!(object instanceof TeacherAssign)) {
            return false;
        }
        TeacherAssign other = (TeacherAssign) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.TeacherAssign[ id=" + id + " ]";
    }
    
}
