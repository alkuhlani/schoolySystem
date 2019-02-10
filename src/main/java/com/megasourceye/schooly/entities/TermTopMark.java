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
@Entity
@NamedQueries({
    @NamedQuery(name = "TermTopMark.findAll", query = "SELECT q FROM TermTopMark q"),
@NamedQuery(name = "TermTopMark.findBySchoolId", query = "SELECT q FROM TermTopMark q WHERE q.school.id=:id")})
public class TermTopMark implements Serializable {

    @OneToOne(mappedBy = "termTopMark")
    private SystemVariables systemVariables;

 

   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name;
    private Float firstHalfMark;
    private Float firstHalfExam;
    private Float SecondHalfMark;
    private Float SecondHalfExam;
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


    public Float getFirstHalfMark() {
        return firstHalfMark;
    }

    public void setFirstHalfMark(Float firstHalfMark) {
        this.firstHalfMark = firstHalfMark;
    }

    public Float getFirstHalfExam() {
        return firstHalfExam;
    }

    public void setFirstHalfExam(Float firstHalfExam) {
        this.firstHalfExam = firstHalfExam;
    }

    public Float getSecondHalfMark() {
        return SecondHalfMark;
    }

    public void setSecondHalfMark(Float SecondHalfMark) {
        this.SecondHalfMark = SecondHalfMark;
    }

    public Float getSecondHalfExam() {
        return SecondHalfExam;
    }

    public void setSecondHalfExam(Float SecondHalfExam) {
        this.SecondHalfExam = SecondHalfExam;
    }

    public SystemVariables getSystemVariables() {
        return systemVariables;
    }

    public void setSystemVariables(SystemVariables systemVariables) {
        this.systemVariables = systemVariables;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
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
        if (!(object instanceof TermTopMark)) {
            return false;
        }
        TermTopMark other = (TermTopMark) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.TermTopMark[ id=" + id + " ]";
    }
    
}
