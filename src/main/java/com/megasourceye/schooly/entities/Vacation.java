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
    @NamedQuery(name = "Vacation.findAll", query = "SELECT q FROM Vacation q"),
@NamedQuery(name = "Vacation.findBySchoolyYearId", query = "SELECT q FROM Vacation q WHERE q.studentSchool.schoolYear.id=:id ORDER BY q.state_ DESC"),
@NamedQuery(name = "Vacation.findByParentId", query = "SELECT q FROM Vacation q WHERE q.studentSchool.student.parent.id=:id ORDER BY q.state_ DESC"),
@NamedQuery(name = "Vacation.findBySchoolyYearIdAndDate2", query = "SELECT q FROM Vacation q WHERE q.studentSchool.schoolYear.id=:id AND q.start_date>=:id1 AND q.start_date<=:id2 ORDER BY q.state_ DESC"),
@NamedQuery(name = "Vacation.findBySchoolyYearIdAndStudentIdDate2", query = "SELECT q FROM Vacation q WHERE q.studentSchool.schoolYear.id=:id AND q.studentSchool.student.id=:id1 AND q.start_date>=:id2 AND q.start_date<=:id3 ORDER BY q.state_ DESC"),
@NamedQuery(name = "Vacation.findBySchoolyYearIdAndParentIdDate2", query = "SELECT q FROM Vacation q WHERE q.studentSchool.schoolYear.id=:id AND q.parent.id=:id1 AND q.start_date>=:id2 AND q.start_date<=:id3 ORDER BY q.state_ DESC"),
@NamedQuery(name = "Vacation.findBySchoolyYearIdAndParentId", query = "SELECT q FROM Vacation q WHERE q.studentSchool.schoolYear.id=:id AND q.parent.id=:id1 ORDER BY q.state_ DESC"),
@NamedQuery(name = "Vacation.findBySchoolyYearIdAndStudentId", query = "SELECT q FROM Vacation q WHERE q.studentSchool.schoolYear.id=:id AND q.studentSchool.student.id=:id1 ORDER BY q.state_ DESC"),
@NamedQuery(name = "Vacation.findBySchoolyYearIdAndStudentIdAndTrue", query = "SELECT q FROM Vacation q WHERE q.studentSchool.schoolYear.id=:id AND q.studentSchool.id=:id1 AND q.state_=TRUE  AND q.start_date<=:id2 AND q.end_date>=:id2 ORDER BY q.state_ DESC")})
public class Vacation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date request_date;//date of request the 
    @Temporal(TemporalType.DATE) 
    private Date start_date;//date of starting the vacation
    @Temporal(TemporalType.DATE) 
    private Date end_date;//date of ending the vacation
    private Short number;//number of date    
    private Short active_day;//number of date is attandenced   
    private Boolean state_;//if approved 1 else 0
    @ManyToOne
    private StudentSchool studentSchool;
    @ManyToOne
    private Parent parent; 
    @Size(max = 255)
    private String reason;
 
   
    
    public Vacation() {
    }

    public Vacation(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    

    

    public Date getRequest_date() {
        return request_date;
    }

    public void setRequest_date(Date request_date) {
        this.request_date = request_date;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Short getActive_day() {
        return active_day;
    }

    public void setActive_day(Short active_day) {
        this.active_day = active_day;
    }

  

    public Short getNumber() {
        return number;
    }

    public void setNumber(Short number) {
        this.number = number;
    }

    

    public Boolean getState_() {
        return state_;
    }

    public void setState_(Boolean state_) {
        this.state_ = state_;
    }

    public StudentSchool getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(StudentSchool studentSchool) {
        this.studentSchool = studentSchool;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
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
        if (!(object instanceof Vacation)) {
            return false;
        }
        Vacation other = (Vacation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.Vacation[ id=" + id + " ]";
    }
    
}
