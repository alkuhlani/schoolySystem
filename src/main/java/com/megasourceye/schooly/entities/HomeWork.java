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
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "HomeWork.findAll", query = "SELECT q FROM HomeWork q ORDER BY q.id DESC")
    ,
@NamedQuery(name = "HomeWork.findBySchoolYearAndTeahcerId", query = "SELECT q FROM HomeWork q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id AND q.subjectTable.teacherAssign.teacher.id=:id1 ORDER BY q.id DESC")
    ,
@NamedQuery(name = "HomeWork.findBySchoolYearAndTeahcerAssignIdAnd2Date", query = "SELECT q FROM HomeWork q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id AND q.subjectTable.teacherAssign.id=:id1 AND q.dateEntry>=:id2 AND q.dateEntry<=:id3 ORDER BY q.id DESC")
    ,
@NamedQuery(name = "HomeWork.findBySchoolYearAndTeahcerIdAndDate", query = "SELECT q FROM HomeWork q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id AND q.subjectTable.teacherAssign.teacher.id=:id1 AND q.dateEntry=:id2 ORDER BY q.id DESC")
    ,
@NamedQuery(name = "HomeWork.findBySchoolYear", query = "SELECT q FROM HomeWork q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id ORDER BY q.id DESC")
    ,
@NamedQuery(name = "HomeWork.findBySchoolYearAndDate", query = "SELECT q FROM HomeWork q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id AND q.dateEntry=:id1 ORDER BY q.id DESC")
    ,
@NamedQuery(name = "HomeWork.findBySchoolYearAndClass", query = "SELECT q FROM HomeWork q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id AND q.subjectTable.teacherAssign.class_.id=:id1 ORDER BY q.id DESC")
    ,
@NamedQuery(name = "HomeWork.findBySchoolYearAndClassAnd2Date", query = "SELECT q FROM HomeWork q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id AND q.subjectTable.teacherAssign.class_.id=:id1 AND q.dateEntry>=:id2 AND q.dateEntry<=:id3 ORDER BY q.id DESC")
    ,
@NamedQuery(name = "HomeWork.findBySchoolYearAndClassAndDate", query = "SELECT q FROM HomeWork q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id AND q.subjectTable.teacherAssign.class_.id=:id1 AND q.dateEntry=:id2 ORDER BY q.id DESC")
    ,
@NamedQuery(name = "HomeWork.findBySchoolYearAndClassAndSubjectIdAnd2Date", query = "SELECT q FROM HomeWork q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id AND q.subjectTable.teacherAssign.class_.id=:id1 AND q.subjectTable.teacherAssign.subject_t.id=:id2 AND q.dateEntry>=:id3 AND q.dateEntry<=:id4 ORDER BY q.id DESC")
    ,
@NamedQuery(name = "HomeWork.findBySubjectTAndDate", query = "SELECT q FROM HomeWork q WHERE q.subjectTable.id=:id AND q.dateEntry=:id1 ORDER BY q.id DESC")})
public class HomeWork implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name;
    @ManyToOne
    private SubjectTable subjectTable;
    @Temporal(TemporalType.DATE)
    private Date dateEntry;
    @Temporal(TemporalType.DATE)
    private Date deliveryDate;
  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubjectTable getSubjectTable() {
        return subjectTable;
    }

    public void setSubjectTable(SubjectTable subjectTable) {
        this.subjectTable = subjectTable;
    }

    public Date getDateEntry() {
        return dateEntry;
    }

    public void setDateEntry(Date dateEntry) {
        this.dateEntry = dateEntry;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HomeWork)) {
            return false;
        }
        HomeWork other = (HomeWork) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.HomeWork[ id=" + id + " ]";
    }

}
