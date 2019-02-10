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
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "WeekTopMark.findAll", query = "SELECT q FROM WeekTopMark q"),
@NamedQuery(name = "WeekTopMark.findBySchoolId", query = "SELECT q FROM WeekTopMark q WHERE q.school.id=:id")})
public class WeekTopMark implements Serializable {

    @OneToOne(mappedBy = "weekTopMark")
    private SystemVariables systemVariables;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name;
    private Float quize;//الاختبار الاسبوعي - علية عشرين درجة
    private Float hw; //  الواجبات
    private Float behavior; // السلوك علية 1
    private Float attendance; // الحضور - علية درجتين - ويتم احتسابها من التحضير
    private Float total;
    @ManyToOne
    private School school;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SystemVariables getSystemVariables() {
        return systemVariables;
    }

    public void setSystemVariables(SystemVariables systemVariables) {
        this.systemVariables = systemVariables;
    }

    public Float getQuize() {
        return quize;
    }

    public void setQuize(Float quize) {
        this.quize = quize;
    }

    public Float getHw() {
        return hw;
    }

    public void setHw(Float hw) {
        this.hw = hw;
    }

    public Float getBehavior() {
        return behavior;
    }

    public void setBehavior(Float behavior) {
        this.behavior = behavior;
    }

    public Float getAttendance() {
        return attendance;
    }

    public void setAttendance(Float attendance) {
        this.attendance = attendance;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof WeekTopMark)) {
            return false;
        }
        WeekTopMark other = (WeekTopMark) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.WeekTopMark[ id=" + id + " ]";
    }
    
}
