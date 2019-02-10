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
    @NamedQuery(name = "Term2TopMark.findAll", query = "SELECT q FROM Term2TopMark q")
    ,
@NamedQuery(name = "Term2TopMark.findBySchoolId", query = "SELECT q FROM Term2TopMark q WHERE q.school.id=:id")})
public class Term2TopMark implements Serializable {

    @OneToOne(mappedBy = "term2TopMark")
    private SystemVariables systemVariables;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name;
    private Float Mark;
    private Float Exam;
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


    public Float getMark() {
        return Mark;
    }

    public void setMark(Float Mark) {
        this.Mark = Mark;
    }

    public Float getExam() {
        return Exam;
    }

    public void setExam(Float Exam) {
        this.Exam = Exam;
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
        if (!(object instanceof Term2TopMark)) {
            return false;
        }
        Term2TopMark other = (Term2TopMark) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.Term2TopMark[ id=" + id + " ]";
    }

}
