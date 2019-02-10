/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;
import javax.validation.constraints.Size;

/**
 *
 * @author said
 */
//add nomber of the lecture
@Entity
@NamedQueries({
    @NamedQuery(name = "SubjectTable.findAll", query = "SELECT q FROM SubjectTable q")
    ,
        @NamedQuery(name = "SubjectTable.findBySchoolId", query = "SELECT q FROM SubjectTable q WHERE q.teacherAssign.schoolYear.school.id=:id")
    ,
@NamedQuery(name = "SubjectTable.findBySchoolYearId", query = "SELECT q FROM SubjectTable q WHERE q.teacherAssign.schoolYear.id=:id")
    ,
@NamedQuery(name = "SubjectTable.findBySchoolYearIdAndTeacherId", query = "SELECT q FROM SubjectTable q WHERE q.teacherAssign.schoolYear.id=:id and q.teacherAssign.teacher.id=:id1"),
@NamedQuery(name = "SubjectTable.findBySchoolYearIdAndSubjectIdAndClassId", query = "SELECT q FROM SubjectTable q WHERE q.teacherAssign.schoolYear.id=:id and q.teacherAssign.subject_t.id=:id1 and q.teacherAssign.class_.id=:id2")
    ,
@NamedQuery(name = "SubjectTable.findBySchoolYearIdAndClassId", query = "SELECT q FROM SubjectTable q WHERE q.teacherAssign.schoolYear.id=:id and q.teacherAssign.class_.id=:id1"),
@NamedQuery(name = "SubjectTable.findBySchoolYearIdAndClassIdAndTeacherId", query = "SELECT q FROM SubjectTable q WHERE q.teacherAssign.schoolYear.id=:id and q.teacherAssign.class_.id=:id1 and q.teacherAssign.teacher.id=:id2"),
@NamedQuery(name = "SubjectTable.findBySchoolYearIdAndTeacherAIdAndDayIdAndLectureNo", query = "SELECT q FROM SubjectTable q WHERE q.teacherAssign.schoolYear.id=:id and q.teacherAssign.id=:id1 AND q.day.id=:id2 AND q.lectureNo=:id3"),
@NamedQuery(name = "SubjectTable.findBySchoolYearIdAndTeacherAIdAndiDayIdAndLectureNo", query = "SELECT q FROM SubjectTable q WHERE q.teacherAssign.schoolYear.id=:id and q.teacherAssign.id=:id1 AND q.day.id=:id2 AND q.lectureNo=:id3")})
public class SubjectTable implements Serializable {

    @OneToMany(mappedBy = "subjectTable")
    private List<HomeWork> homeWorks;

    @OneToMany(mappedBy = "subjectTable")
    private List<NoteSpec> noteSpecs;

    @OneToMany(mappedBy = "subjectTable")
    private List<NoteDaily> noteDailys;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name_ar;
    @Size(max = 255)
    private String name_en;
    @ManyToOne
    private TeacherAssign teacherAssign;
    @ManyToOne
    private DT day;
    private Character lectureNo;
    private Boolean stateDeActive;//true if the record deactive=deactive instead of delete

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

    public TeacherAssign getTeacherAssign() {
        return teacherAssign;
    }

    public void setTeacherAssign(TeacherAssign teacherAssign) {
        this.teacherAssign = teacherAssign;
    }

    public DT getDay() {
        return day;
    }

    public void setDay(DT day) {
        this.day = day;
    }

    public Character getLectureNo() {
        return lectureNo;
    }

    public void setLectureNo(Character lectureNo) {
        this.lectureNo = lectureNo;
    }

    public List<NoteSpec> getNoteSpecs() {
        return noteSpecs;
    }

    public void setNoteSpecs(List<NoteSpec> noteSpecs) {
        this.noteSpecs = noteSpecs;
    }

    public List<NoteDaily> getNoteDailys() {
        return noteDailys;
    }

    public void setNoteDailys(List<NoteDaily> noteDailys) {
        this.noteDailys = noteDailys;
    }

    public Boolean getStateDeActive() {
        return stateDeActive;
    }

    public void setStateDeActive(Boolean stateDeActive) {
        this.stateDeActive = stateDeActive;
    }

    public List<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(List<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
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
        if (!(object instanceof SubjectTable)) {
            return false;
        }
        SubjectTable other = (SubjectTable) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.SubjectTable[ id=" + id + " ]";
    }

}
