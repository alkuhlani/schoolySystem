/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "MonthTopMark.findAll", query = "SELECT q FROM MonthTopMark q"),
@NamedQuery(name = "MonthTopMark.findBySchoolId", query = "SELECT q FROM MonthTopMark q WHERE q.school.id=:id")})
public class MonthTopMark implements Serializable {

    @OneToOne(mappedBy = "monthTopMark")
    private SystemVariables systemVariables;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name;
    @ManyToOne
    private StudentSchool studentSchool;
    @ManyToOne
    private Subject_t subject_t;
    private Float hw;
    private Float writing;
    private Float reading;
    private Float behavior;
    private Float total; // المجموع'
    @ManyToOne
    private School school;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   

    public StudentSchool getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(StudentSchool studentSchool) {
        this.studentSchool = studentSchool;
    }

    public Subject_t getSubject_t() {
        return subject_t;
    }

    public void setSubject_t(Subject_t subject_t) {
        this.subject_t = subject_t;
    }

    public Float getHw() {
        return hw;
    }

    public void setHw(Float hw) {
        this.hw = hw;
    }

    public Float getWriting() {
        return writing;
    }

    public void setWriting(Float writing) {
        this.writing = writing;
    }

    public Float getReading() {
        return reading;
    }

    public void setReading(Float reading) {
        this.reading = reading;
    }

    public Float getBehavior() {
        return behavior;
    }

    public void setBehavior(Float behavior) {
        this.behavior = behavior;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public SystemVariables getSystemVariables() {
        return systemVariables;
    }

    public void setSystemVariables(SystemVariables systemVariables) {
        this.systemVariables = systemVariables;
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
        if (!(object instanceof MonthTopMark)) {
            return false;
        }
        MonthTopMark other = (MonthTopMark) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.MonthTopMark[ id=" + id + " ]";
    }
    
}
