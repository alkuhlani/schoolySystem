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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author said
 */
// اضافة القسم عربي انجليزي
@Entity
@NamedQueries({
    @NamedQuery(name = "StudentSchool.findAll", query = "SELECT q FROM StudentSchool q")
    ,
@NamedQuery(name = "StudentSchool.findBySchoolYear", query = "SELECT q FROM StudentSchool q WHERE q.schoolYear.id=:id and q.deaActive=FALSE ORDER BY q.student.name_en")
    ,
@NamedQuery(name = "StudentSchool.findBySchoolYearAndClassId", query = "SELECT q FROM StudentSchool q WHERE q.schoolYear.id=:id and q.class_.id=:id1 and q.deaActive=FALSE  ORDER BY q.student.name_en")
    ,
@NamedQuery(name = "StudentSchool.findBySchoolYearAndStudentId", query = "SELECT q FROM StudentSchool q WHERE q.schoolYear.id=:id and q.student.id=:id1 and q.deaActive=FALSE")
    ,
@NamedQuery(name = "StudentSchool.findBySchoolYearAndParentId", query = "SELECT q FROM StudentSchool q WHERE q.schoolYear.id=:id and q.student.parent.id=:id1 and q.deaActive=FALSE")})
public class StudentSchool implements Serializable {

    @OneToMany(mappedBy = "studentSchool")
    private List<Vacation> vacations;

    @OneToMany(mappedBy = "studentSchool")
    private List<NoteSpec> noteSpecs;

    @OneToMany(mappedBy = "studentSchool")
    private List<NoteDaily> noteDailys;

    @OneToMany(mappedBy = "studentSchool")
    private List<MonthMark> monthMarks;

    @OneToMany(mappedBy = "studentSchool")
    private List<Term2MArk> term2MArks;

    @OneToMany(mappedBy = "studentSchool")
    private List<Term3Mark> term3Marks;

    @OneToMany(mappedBy = "studentSchool")
    private List<WeekMark> weekMarks;

 

    @OneToMany(mappedBy = "studentSchool")
    private List<Attendance> attendances;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Student student;
    @ManyToOne
    private School school;
    @ManyToOne
    private SchoolYear schoolYear;
    @ManyToOne
    private Class_ class_;
    private Character subClass;
    private Float discount;
    @OneToOne
    private Audit audit;
    @ManyToOne
    private Department department;
    private Boolean deaActive=false;//1 = is not active ; 0= is active

    public StudentSchool(Long id) {
        this.id = id;
    }

    public StudentSchool() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public SchoolYear getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Class_ getClass_() {
        return class_;
    }

    public void setClass_(Class_ class_) {
        this.class_ = class_;
    }

    public Character getSubClass() {
        return subClass;
    }

    public void setSubClass(Character subClass) {
        this.subClass = subClass;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
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

    public List<MonthMark> getMonthMarks() {
        return monthMarks;
    }

    public void setMonthMarks(List<MonthMark> monthMarks) {
        this.monthMarks = monthMarks;
    }

    public List<Term2MArk> getTerm2MArks() {
        return term2MArks;
    }

    public void setTerm2MArks(List<Term2MArk> term2MArks) {
        this.term2MArks = term2MArks;
    }

    public List<Term3Mark> getTerm3Marks() {
        return term3Marks;
    }

    public void setTerm3Marks(List<Term3Mark> term3Marks) {
        this.term3Marks = term3Marks;
    }

    public List<WeekMark> getWeekMarks() {
        return weekMarks;
    }

    public void setWeekMarks(List<WeekMark> weekMarks) {
        this.weekMarks = weekMarks;
    }

    public List<Vacation> getVacations() {
        return vacations;
    }

    public void setVacations(List<Vacation> vacations) {
        this.vacations = vacations;
    }

    public Boolean getDeaActive() {
        return deaActive;
    }

    public void setDeaActive(Boolean deaActive) {
        this.deaActive = deaActive;
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
        if (!(object instanceof StudentSchool)) {
            return false;
        }
        StudentSchool other = (StudentSchool) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.StudentSchool[ id=" + id + " ]";
    }

}
