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
//جدول ثابت
// Subject Table حيث يكون فية المواد جميعها
//add the school
@Entity
@NamedQueries({
    @NamedQuery(name = "Subject_t.findAll", query = "SELECT q FROM Subject_t q")
    ,
@NamedQuery(name = "Subject_t.findBySchoolId", query = "SELECT q FROM Subject_t q WHERE q.school.id=:id")})
public class Subject_t implements Serializable {

    @OneToMany(mappedBy = "subject_t")
    private List<MonthMark> monthMarks;

    @OneToMany(mappedBy = "subject_t")
    private List<Term2MArk> term2MArks;

    @OneToMany(mappedBy = "subject_t")
    private List<Term3Mark> term3Marks;

    @OneToMany(mappedBy = "subject_t")
    private List<ExamTable> examTables;

    @OneToMany(mappedBy = "subject_t")
    private List<TeacherAssign> teacherAssigns;

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name_ar;
    @Size(max = 255)
    private String name_en;
    @ManyToOne
    private School school;
    @Size(max = 45)
    private String shortcuts_en;
    @Size(max = 45)
    private String shortcuts_ar;

    public Subject_t() {
    }

    public Subject_t(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TeacherAssign> getTeacherAssigns() {
        return teacherAssigns;
    }

    public void setTeacherAssigns(List<TeacherAssign> teacherAssigns) {
        this.teacherAssigns = teacherAssigns;
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

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<MonthMark> getMonthMarks() {
        return monthMarks;
    }

    public void setMonthMarks(List<MonthMark> monthMarks) {
        this.monthMarks = monthMarks;
    }

    public List<Term2MArk> getTerm2MArks() {
        return term2MArks;
    }

    public void setTerm2MArks(List<Term2MArk> term2MArks) {
        this.term2MArks = term2MArks;
    }

    public List<Term3Mark> getTerm3Marks() {
        return term3Marks;
    }

    public void setTerm3Marks(List<Term3Mark> term3Marks) {
        this.term3Marks = term3Marks;
    }

    public List<ExamTable> getExamTables() {
        return examTables;
    }

    public void setExamTables(List<ExamTable> examTables) {
        this.examTables = examTables;
    }

    public String getShortcuts_en() {
        return shortcuts_en;
    }

    public void setShortcuts_en(String shortcuts_en) {
        this.shortcuts_en = shortcuts_en;
    }

    public String getShortcuts_ar() {
        return shortcuts_ar;
    }

    public void setShortcuts_ar(String shortcuts_ar) {
        this.shortcuts_ar = shortcuts_ar;
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
        if (!(object instanceof Subject_t)) {
            return false;
        }
        Subject_t other = (Subject_t) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.Subject_t[ id=" + id + " ]";
    }

}
