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
    @NamedQuery(name = "Activity.findAll", query = "SELECT q FROM Activity q ORDER BY q.id DESC")
    ,

@NamedQuery(name = "Activity.findBySchoolYearId", query = "SELECT q FROM Activity q WHERE q.schoolYear.id=:id ORDER BY q.id DESC" ),
@NamedQuery(name = "Activity.findBySchoolYearIdAndSeenTypeAndTypeAndIsActive", query = "SELECT q FROM Activity q WHERE q.schoolYear.id=:id AND q.seenType=:id1 and q.type=:id2 AND q.isActive=TRUE ORDER BY q.priorityNo DESC" ),
@NamedQuery(name = "Activity.findBySchoolYearIdAndTypeAndIsActive", query = "SELECT q FROM Activity q WHERE q.schoolYear.id=:id  and q.type=:id1 AND q.isActive=TRUE ORDER BY q.priorityNo DESC" )})
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String title;
    @Temporal(TemporalType.DATE)
    private Date creation_date;
    private Character type;
    //0=albumImage
    //1=activity
    //2=studyDescision
    private Boolean isActive;
    private Float priorityNo;
    @ManyToOne
    private SchoolYear schoolYear;
    @Size(max = 255)
    private String imagePath;
    private Character seenType;
    //0=allAndVisitor
    //1=all
    //2=employeeOnly
    //3=teacherOnly
    //4=studentOnly
    //5=parentOnly
    
    
    

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Float getPriorityNo() {
        return priorityNo;
    }

    public void setPriorityNo(Float priorityNo) {
        this.priorityNo = priorityNo;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Character getSeenType() {
        return seenType;
    }

    public void setSeenType(Character seenType) {
        this.seenType = seenType;
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
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.Activity[ id=" + id + " ]";
    }
    
}
