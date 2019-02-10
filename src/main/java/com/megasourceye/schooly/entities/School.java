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
@Entity
@NamedQueries({
    @NamedQuery(name = "School.findAll", query = "SELECT q  FROM School q")
    ,
    @NamedQuery(name = "School.findAll2", query = "SELECT q , q.systemVariables.school.name_en  FROM School q")
    ,
@NamedQuery(name = "School.findById", query = "SELECT q FROM School q WHERE q.id=:id")})
public class School implements Serializable {


    @OneToMany(mappedBy = "school")
    private List<Branch> branchs;

    @OneToMany(mappedBy = "school")
    private List<OH> oHs;

    @OneToMany(mappedBy = "school")
    private List<Term2TopMark> term2TopMarks;

    @OneToMany(mappedBy = "school")
    private List<MonthTopMark> monthTopMarks;

    @OneToMany(mappedBy = "school")
    private List<TermTopMark> termTopMarks;

    @OneToMany(mappedBy = "school")
    private List<WeekTopMark> weekTopMarks;

    @OneToMany(mappedBy = "school")
    private List<Parent> parents;

    @OneToMany(mappedBy = "school")
    private List<Subject_t> subject_ts;

    @OneToMany(mappedBy = "school")
    private List<Employee> employees;

    @OneToMany(mappedBy = "school")
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "school")
    private List<Student> students;

    @OneToMany(mappedBy = "school")
    private List<Users> userss;

    @OneToMany(mappedBy = "school")
    private List<TermWeek> termWeeks;

    @OneToOne(mappedBy = "school")
    private SystemVariables systemVariables;

    @OneToMany(mappedBy = "school")
    private List<Class_> class_s;

    @OneToMany(mappedBy = "school")
    private List<StudentSchool> studentSchools;

    @OneToMany(mappedBy = "school")
    private List<SchoolYear> schoolYears;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name_ar;
    @Size(max = 255)
    private String name_en;
    @Size(max = 255)
    private String fb;
    @Size(max = 255)
    private String iconFile;
    @Size(max = 255)
    private String webSiteUrl;
    @Size(max = 50)
    private String email;
    @Size(max = 255)
    private String location;
    @Size(max = 255)
    private String phoneContact;
    @Size(max = 255)
    private String managerName;

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

    public List<Class_> getClass_s() {
        return class_s;
    }

    public void setClass_s(List<Class_> class_s) {
        this.class_s = class_s;
    }

    public List<StudentSchool> getStudentSchools() {
        return studentSchools;
    }

    public void setStudentSchools(List<StudentSchool> studentSchools) {
        this.studentSchools = studentSchools;
    }

    public List<SchoolYear> getSchoolYears() {
        return schoolYears;
    }

    public void setSchoolYears(List<SchoolYear> schoolYears) {
        this.schoolYears = schoolYears;
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

    public List<Users> getUserss() {
        return userss;
    }

    public void setUserss(List<Users> userss) {
        this.userss = userss;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getIconFile() {
        return iconFile;
    }

    public void setIconFile(String iconFile) {
        this.iconFile = iconFile;
    }

    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    public void setWebSiteUrl(String webSiteUrl) {
        this.webSiteUrl = webSiteUrl;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Subject_t> getSubject_ts() {
        return subject_ts;
    }

    public void setSubject_ts(List<Subject_t> subject_ts) {
        this.subject_ts = subject_ts;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public List<MonthTopMark> getMonthTopMarks() {
        return monthTopMarks;
    }

    public void setMonthTopMarks(List<MonthTopMark> monthTopMarks) {
        this.monthTopMarks = monthTopMarks;
    }

    public List<TermTopMark> getTermTopMarks() {
        return termTopMarks;
    }

    public void setTermTopMarks(List<TermTopMark> termTopMarks) {
        this.termTopMarks = termTopMarks;
    }

    public List<WeekTopMark> getWeekTopMarks() {
        return weekTopMarks;
    }

    public void setWeekTopMarks(List<WeekTopMark> weekTopMarks) {
        this.weekTopMarks = weekTopMarks;
    }

    public List<Term2TopMark> getTerm2TopMarks() {
        return term2TopMarks;
    }

    public void setTerm2TopMarks(List<Term2TopMark> term2TopMarks) {
        this.term2TopMarks = term2TopMarks;
    }

    public List<OH> getoHs() {
        return oHs;
    }

    public void setoHs(List<OH> oHs) {
        this.oHs = oHs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Branch> getBranchs() {
        return branchs;
    }

    public void setBranchs(List<Branch> branchs) {
        this.branchs = branchs;
    }

  

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneContact() {
        return phoneContact;
    }

    public void setPhoneContact(String phoneContact) {
        this.phoneContact = phoneContact;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
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
        if (!(object instanceof School)) {
            return false;
        }
        School other = (School) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.School[ id=" + id + " ]";
    }

}
