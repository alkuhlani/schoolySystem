/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
//add string imageFilePath
//add value is active or not - and for every table
@Entity
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT q FROM Users q")
    ,
     @NamedQuery(name = "Users.findBySchoolId", query = "SELECT q FROM Users q WHERE q.school.id=:id")
    ,
@NamedQuery(name = "Users.findByUsername", query = "SELECT q FROM Users q WHERE q.username=:id")})
public class Users implements Serializable {

    @OneToMany(mappedBy = "users")
    private List<AccessTracking> accessTrackings;

    @OneToMany(mappedBy = "users")
    private List<MessagingSchool> messagingSchools;

    @OneToMany(mappedBy = "users1")
    private List<Messaging> messagings1;

    @OneToMany(mappedBy = "users")
    private List<Messaging> messagings;

    @OneToOne(mappedBy = "user")
    private Employee employee;

    @OneToOne(mappedBy = "user")
    private Teacher teacher;

    @OneToOne(mappedBy = "user")
    private Student student;

    @OneToOne(mappedBy = "user")
    private Parent parent;

    @OneToMany(mappedBy = "user")
    private List<Audit> audits;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name_ar;
    @Size(max = 255)
    private String name_en;
    @Size(max = 255)
    @Column(unique = true)
    private String username;
    @Size(max = 255)
    private String password;
    @Size(max = 255)
    private String role_;
    @Size(max = 20)
    private String phone;
    @Size(max = 255)
    private String imagePath;
    @ManyToOne
    private School school;
    @ManyToMany
    private List<Roles> roles;//update to many to one

    public Users(Long id) {
        this.id = id;
    }

    public Users() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole_() {
        return role_;
    }

    public void setRole_(String role_) {
        this.role_ = role_;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Audit> getAudits() {
        return audits;
    }

    public void setAudits(List<Audit> audits) {
        this.audits = audits;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Messaging> getMessagings() {
        return messagings;
    }

    public void setMessagings(List<Messaging> messagings) {
        this.messagings = messagings;
    }

    public List<Messaging> getMessagings1() {
        return messagings1;
    }

    public void setMessagings1(List<Messaging> messagings1) {
        this.messagings1 = messagings1;
    }

    public List<MessagingSchool> getMessagingSchools() {
        return messagingSchools;
    }

    public void setMessagingSchools(List<MessagingSchool> messagingSchools) {
        this.messagingSchools = messagingSchools;
    }

    public List<AccessTracking> getAccessTrackings() {
        return accessTrackings;
    }

    public void setAccessTrackings(List<AccessTracking> accessTrackings) {
        this.accessTrackings = accessTrackings;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.Users[ id=" + id + " ]";
    }

}
