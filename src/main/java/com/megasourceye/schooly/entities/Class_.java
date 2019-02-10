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
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
//add the school
@Entity
@NamedQueries({
    @NamedQuery(name = "Class_.findAll", query = "SELECT q FROM Class_ q")
    ,
@NamedQuery(name = "Class_.findBySchoolId", query = "SELECT q FROM Class_ q WHERE q.school.id=:id ORDER BY q.noClass ASC ")})
public class Class_ implements Serializable {

    @OneToMany(mappedBy = "class_")
    private List<ExamTable> examTables;

    @OneToMany(mappedBy = "class_")
    private List<TeacherAssign> teacherAssigns;

    @OneToMany(mappedBy = "class_")
    private List<StudentSchool> studentSchools;

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
    @ManyToOne
    private Department department;
    private Double fee;
    private Short noClass;
    private Character subClass ;
    @ManyToOne
    private Branch branch;
    @ManyToOne
    private StaticLevel staticLevel;
    @ManyToOne
    private StaticClass staticClass;
    @ManyToOne
    private StaticSubClass staticSubClass;
    

    public Class_() {
    }

    public Class_(Long id) {
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

    public List<StudentSchool> getStudentSchools() {
        return studentSchools;
    }

    public void setStudentSchools(List<StudentSchool> studentSchools) {
        this.studentSchools = studentSchools;
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

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<ExamTable> getExamTables() {
        return examTables;
    }

    public void setExamTables(List<ExamTable> examTables) {
        this.examTables = examTables;
    }

    public Short getNoClass() {
        return noClass;
    }

    public void setNoClass(Short noClass) {
        this.noClass = noClass;
    }
    public Character getSubClass() {
        return subClass;
    }

    public void setSubClass(Character subClass) {
        this.subClass = subClass;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public StaticLevel getStaticLevel() {
        return staticLevel;
    }

    public void setStaticLevel(StaticLevel staticLevel) {
        this.staticLevel = staticLevel;
    }

    public StaticClass getStaticClass() {
        return staticClass;
    }

    public void setStaticClass(StaticClass staticClass) {
        this.staticClass = staticClass;
    }

    public StaticSubClass getStaticSubClass() {
        return staticSubClass;
    }

    public void setStaticSubClass(StaticSubClass staticSubClass) {
        this.staticSubClass = staticSubClass;
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
        if (!(object instanceof Class_)) {
            return false;
        }
        Class_ other = (Class_) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.Class_[ id=" + id + " ]";
    }

}
