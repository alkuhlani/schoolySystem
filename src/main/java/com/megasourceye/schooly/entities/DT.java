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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
// Day Table يكون فية ايام الاسبوع
@Entity
@NamedQueries({
    @NamedQuery(name = "DT.findAll", query = "SELECT q FROM DT q")})
public class DT implements Serializable {

    @OneToMany(mappedBy = "dt")
    private List<OH> oHs;

    


    @OneToMany(mappedBy = "day")
    private List<SubjectTable> subjectTables;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name_ar;
    @Size(max = 255)
    private String name_en;

    public DT() {
    }

    public DT(Long id) {
        this.id = id;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public List<SubjectTable> getSubjectTables() {
        return subjectTables;
    }

    public void setSubjectTables(List<SubjectTable> subjectTables) {
        this.subjectTables = subjectTables;
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

    public List<OH> getoHs() {
        return oHs;
    }

    public void setoHs(List<OH> oHs) {
        this.oHs = oHs;
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
        if (!(object instanceof DT)) {
            return false;
        }
        DT other = (DT) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.DT[ id=" + id + " ]";
    }
    
}
