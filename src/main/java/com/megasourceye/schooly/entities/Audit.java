/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
//جدول مراقبة الجداول
@Entity
@NamedQueries({
    @NamedQuery(name = "Audit.findAll", query = "SELECT q FROM Audit q")})
public class Audit implements Serializable {

    @OneToOne(mappedBy = "audit")
    private StudentSchool studentSchool;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String firstData;
    @Size(max = 255)
    private String lastData;
    @Temporal(TemporalType.TIMESTAMP) 
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP) 
    private Date lastUpdateDate;
    @ManyToOne
    private Users user;
    private Character typeState; //i=insert ; u=update ; d=delet

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentSchool getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(StudentSchool studentSchool) {
        this.studentSchool = studentSchool;
    }

    public String getFirstData() {
        return firstData;
    }

    public void setFirstData(String firstData) {
        this.firstData = firstData;
    }

    public String getLastData() {
        return lastData;
    }

    public void setLastData(String lastData) {
        this.lastData = lastData;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Character getTypeState() {
        return typeState;
    }

    public void setTypeState(Character typeState) {
        this.typeState = typeState;
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
        if (!(object instanceof Audit)) {
            return false;
        }
        Audit other = (Audit) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.Audit[ id=" + id + " ]";
    }
    
}
