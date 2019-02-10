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
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
//ولي الامر والطالب
@Entity
@NamedQueries({
    @NamedQuery(name = "StudentParent.findAll", query = "SELECT q FROM StudentParent q")
    ,
@NamedQuery(name = "StudentParent.findBySchoolId", query = "SELECT q FROM StudentParent q WHERE q.student.school.id=:id")
    ,
@NamedQuery(name = "StudentParent.findByStudentId", query = "SELECT q FROM StudentParent q WHERE q.student.id=:id")
    ,
@NamedQuery(name = "StudentParent.findByParentId", query = "SELECT q FROM StudentParent q WHERE q.parent.id=:id")})
public class StudentParent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name_ar;
    @Size(max = 255)
    private String name_en;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Parent parent;

    private Character type;
    @Size(max = 255)
    private String otherType;

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

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }


    

    public String getOtherType() {
        return otherType;
    }

    public void setOtherType(String otherType) {
        this.otherType = otherType;
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
        if (!(object instanceof StudentParent)) {
            return false;
        }
        StudentParent other = (StudentParent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.StudentParent[ id=" + id + " ]";
    }

}
