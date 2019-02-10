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
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
// جدول العام الدراسي 
@Entity
@NamedQueries({
    @NamedQuery(name = "SchoolYear.findAll", query = "SELECT q FROM SchoolYear q")
    ,
@NamedQuery(name = "SchoolYear.findBySchoolId", query = "SELECT q FROM SchoolYear q WHERE q.school.id=:id")})
public class SchoolYear implements Serializable {

    @OneToMany(mappedBy = "schoolYear")
    private List<Messaging> messagings;
    @OneToMany(mappedBy = "schoolYear")
    private List<MessagingSchool> messagingSchools;
    @OneToMany(mappedBy = "schoolYear")
    private List<SchoolWeek> schoolWeeks;
    @OneToMany(mappedBy = "schoolYear")
    private List<TermMonth> termMonths;
    @OneToMany(mappedBy = "schoolYear")
    private List<OH> oHs;
    @OneToMany(mappedBy = "schoolYear")
    private List<SchoolMonth> schoolMonths;
    @OneToMany(mappedBy = "schoolYear")
    private List<TermWeek> termWeeks;

    @OneToOne(mappedBy = "schoolYear")
    private SystemVariables systemVariables;

    @OneToMany(mappedBy = "schoolYear")
    private List<TeacherAssign> teacherAssigns;

    @OneToMany(mappedBy = "schoolYear")
    private List<StudentSchool> studentSchools;

    @OneToMany(mappedBy = "schoolYear")
    private List<Exam> exams;
    @OneToMany(mappedBy = "schoolYear")
    private List<Activity> activitys;

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

    public SchoolYear() {
    }

    public SchoolYear(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TermWeek> getTermWeeks() {
        return termWeeks;
    }

    public void setTermWeeks(List<TermWeek> termWeeks) {
        this.termWeeks = termWeeks;
    }

    public SystemVariables getSystemVariables() {
        return systemVariables;
    }

    public void setSystemVariables(SystemVariables systemVariables) {
        this.systemVariables = systemVariables;
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

    public List<SchoolWeek> getSchoolWeeks() {
        return schoolWeeks;
    }

    public void setSchoolWeeks(List<SchoolWeek> schoolWeeks) {
        this.schoolWeeks = schoolWeeks;
    }

    public List<TermMonth> getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(List<TermMonth> termMonths) {
        this.termMonths = termMonths;
    }

    public List<OH> getoHs() {
        return oHs;
    }

    public void setoHs(List<OH> oHs) {
        this.oHs = oHs;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<SchoolMonth> getSchoolMonths() {
        return schoolMonths;
    }

    public void setSchoolMonths(List<SchoolMonth> schoolMonths) {
        this.schoolMonths = schoolMonths;
    }

    public List<Activity> getActivitys() {
        return activitys;
    }

    public void setActivitys(List<Activity> activitys) {
        this.activitys = activitys;
    }

    public List<Messaging> getMessagings() {
        return messagings;
    }

    public void setMessagings(List<Messaging> messagings) {
        this.messagings = messagings;
    }

    public List<MessagingSchool> getMessagingSchools() {
        return messagingSchools;
    }

    public void setMessagingSchools(List<MessagingSchool> messagingSchools) {
        this.messagingSchools = messagingSchools;
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
        if (!(object instanceof SchoolYear)) {
            return false;
        }
        SchoolYear other = (SchoolYear) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.SchoolYear[ id=" + id + " ]";
    }

}
