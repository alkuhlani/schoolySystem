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
    @NamedQuery(name = "NoteDaily.findAll", query = "SELECT q FROM NoteDaily q ORDER BY q.id DESC "),
@NamedQuery(name = "NoteDaily.findBySchoolYearAndDate", query = "SELECT q FROM NoteDaily q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id AND q.writeDate=:id1 ORDER BY q.id DESC"),
@NamedQuery(name = "NoteDaily.findBySchoolYearAndTeahcerId", query = "SELECT q FROM NoteDaily q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id AND q.subjectTable.teacherAssign.teacher.id=:id1  ORDER BY q.id DESC"),
@NamedQuery(name = "NoteDaily.findBySchoolYearAndTeahcerIdAndDate", query = "SELECT q FROM NoteDaily q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id AND q.subjectTable.teacherAssign.teacher.id=:id1 AND q.writeDate=:id2  ORDER BY q.id DESC"),
@NamedQuery(name = "NoteDaily.findBySchoolYear", query = "SELECT q FROM NoteDaily q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id ORDER BY q.id DESC"),
@NamedQuery(name = "NoteDaily.findBySchoolYearAndClass", query = "SELECT q FROM NoteDaily q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id AND q.subjectTable.teacherAssign.class_.id=:id1 ORDER BY q.id DESC"),
@NamedQuery(name = "NoteDaily.findBySchoolYearAndClassAnd2Date", query = "SELECT q FROM NoteDaily q WHERE q.subjectTable.teacherAssign.schoolYear.id=:id AND q.subjectTable.teacherAssign.class_.id=:id1 AND q.writeDate>=:id2 AND q.writeDate<=:id3 ORDER BY q.id DESC"),
@NamedQuery(name = "NoteDaily.findBySubjectTAndEntryDate", query = "SELECT q FROM NoteDaily q WHERE q.subjectTable.id=:id AND q.writeDate=:id1 ORDER BY q.id DESC"),
@NamedQuery(name = "NoteDaily.findBySubjectTAndStudentSIdEntryDate", query = "SELECT q FROM NoteDaily q WHERE q.subjectTable.id=:id AND q.studentSchool.id=:id1 AND q.writeDate=:id2 ORDER BY q.id DESC"),
@NamedQuery(name = "NoteDaily.findBySchoolYearIdAndStudentId", query = "SELECT q FROM NoteDaily q WHERE q.studentSchool.schoolYear.id=:id AND q.studentSchool.student.id=:id1 ORDER BY q.id DESC"),
@NamedQuery(name = "NoteDaily.findBySchoolYearIdAndStudentIdAndDate", query = "SELECT q FROM NoteDaily q WHERE q.studentSchool.schoolYear.id=:id AND q.studentSchool.student.id=:id1 AND q.writeDate=:id2 ORDER BY q.id DESC"),
@NamedQuery(name = "NoteDaily.findBySchoolYearIdAndParentIdAndDate", query = "SELECT q FROM NoteDaily q WHERE q.studentSchool.schoolYear.id=:id AND q.studentSchool.student.parent.id=:id1 AND q.writeDate=:id2 ORDER BY q.id DESC"),
@NamedQuery(name = "NoteDaily.findBySchoolYearIdAndStudentIdAnd2Date", query = "SELECT q FROM NoteDaily q WHERE q.studentSchool.schoolYear.id=:id AND q.studentSchool.student.id=:id1 AND q.writeDate>=:id2 AND q.writeDate<=:id3 ORDER BY q.id DESC"),
@NamedQuery(name = "NoteDaily.findBySchoolYearIdAndParentIdAnd2Date", query = "SELECT q FROM NoteDaily q WHERE q.studentSchool.schoolYear.id=:id AND q.studentSchool.student.parent.id=:id1 AND q.writeDate>=:id2 AND q.writeDate<=:id3 ORDER BY q.id DESC"),
@NamedQuery(name = "NoteDaily.findBySchoolYearIdAndSubjectIdAndStudentIdAnd2Date", query = "SELECT q FROM NoteDaily q WHERE q.studentSchool.schoolYear.id=:id AND q.subjectTable.teacherAssign.subject_t.id=:id1 AND q.studentSchool.student.id=:id2 AND q.writeDate>=:id3 AND q.writeDate<=:id4 ORDER BY q.id DESC")})
public class NoteDaily implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String name;
    @ManyToOne
    private SubjectTable subjectTable;
    @ManyToOne
    private StudentSchool studentSchool;
    @Temporal(TemporalType.DATE)
    private Date writeDate;
    private Boolean read_;// true if yes
    private Boolean forStudent;
    private Boolean forParent;
    private Boolean student_seen;
    private Boolean parent_seen;
    private Boolean isSpec;

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

    public Boolean getStudent_seen() {
        return student_seen;
    }

    public void setStudent_seen(Boolean student_seen) {
        this.student_seen = student_seen;
    }

    public Boolean getParent_seen() {
        return parent_seen;
    }

    public void setParent_seen(Boolean parent_seen) {
        this.parent_seen = parent_seen;
    }

   

    public SubjectTable getSubjectTable() {
        return subjectTable;
    }

    public void setSubjectTable(SubjectTable subjectTable) {
        this.subjectTable = subjectTable;
    }

    public StudentSchool getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(StudentSchool studentSchool) {
        this.studentSchool = studentSchool;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public Boolean getRead_() {
        return read_;
    }

    public void setRead_(Boolean read_) {
        this.read_ = read_;
    }

    public Boolean getForStudent() {
        return forStudent;
    }

    public void setForStudent(Boolean forStudent) {
        this.forStudent = forStudent;
    }

    public Boolean getForParent() {
        return forParent;
    }

    public void setForParent(Boolean forParent) {
        this.forParent = forParent;
    }

    public Boolean getIsSpec() {
        return isSpec;
    }

    public void setIsSpec(Boolean isSpec) {
        this.isSpec = isSpec;
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
        if (!(object instanceof NoteDaily)) {
            return false;
        }
        NoteDaily other = (NoteDaily) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.NoteDaily[ id=" + id + " ]";
    }
    
}
