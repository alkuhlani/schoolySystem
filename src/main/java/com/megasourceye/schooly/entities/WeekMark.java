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

/**
 *
 * @author said
 */
//جدول الدرجات الاسبوعية بالنسبة لنظام ثلاثة اترام
// اضافة الاسبوع من التقويم
@Entity
@NamedQueries({
    @NamedQuery(name = "WeekMark.findAll", query = "SELECT q FROM WeekMark q"),
@NamedQuery(name = "WeekMark.findByStudentSIdAndTeacherAssignAndSchoolW", query = "SELECT q FROM WeekMark q WHERE q.studentSchool.id=:id AND q.teacherAssign.id=:id1 AND q.schoolWeek.id=:id2"),
@NamedQuery(name = "WeekMark.findByStudentIdAndSchoolW", query = "SELECT q FROM WeekMark q WHERE q.studentSchool.student.id=:id  AND q.schoolWeek.id=:id1"),
@NamedQuery(name = "WeekMark.findBySchoolYearIdAndStudentId", query = "SELECT q FROM WeekMark q WHERE q.studentSchool.schoolYear.id=:id AND q.studentSchool.student.id=:id1"),
@NamedQuery(name = "WeekMark.findByStudentSIdAndSubjectIdAndTermWeekAndTermT", query = "SELECT q FROM WeekMark q WHERE q.studentSchool.id=:id AND q.teacherAssign.subject_t.id=:id1 AND q.schoolWeek.termWeek.id=:id2 and q.schoolWeek.termType=:id3"),
@NamedQuery(name = "WeekMark.findByTeacherIdAndSchoolWeek", query = "SELECT q FROM WeekMark q WHERE q.teacherAssign.teacher.id=:id AND q.schoolWeek.id=:id1"),
@NamedQuery(name = "WeekMark.findBySchoolYearAndTeacherId", query = "SELECT q FROM WeekMark q WHERE q.teacherAssign.schoolYear.id=:id AND q.teacherAssign.teacher.id=:id1"),
@NamedQuery(name = "WeekMark.findBySchoolYearAndStudentId", query = "SELECT q FROM WeekMark q WHERE q.teacherAssign.schoolYear.id=:id AND q.studentSchool.student.id=:id1"),
@NamedQuery(name = "WeekMark.findBySchoolYearId", query = "SELECT q FROM WeekMark q WHERE q.teacherAssign.schoolYear.id=:id"),
@NamedQuery(name = "WeekMark.findBySchoolYearIdAndSchoolWeek", query = "SELECT q FROM WeekMark q WHERE q.teacherAssign.schoolYear.id=:id AND q.schoolWeek.id=:id1")})
public class WeekMark implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float quize;//الاختبار الاسبوعي - علية عشرين درجة
    private Float hw; //  الواجبات
    private Float behavior; // السلوك علية 1
    private Float attendance; // الحضور - علية درجتين - ويتم احتسابها من التحضير
    @Temporal(TemporalType.DATE) 
    private Date dataEntryDate; // تاريخ تسجيل البيانات
    private Float total; // المجموع
    private Boolean isApproval;//true if he approval
    @ManyToOne
    private StudentSchool studentSchool;
    @ManyToOne
    private TeacherAssign teacherAssign;
    @ManyToOne
    private SchoolWeek schoolWeek;
    
    
    
    
   
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDataEntryDate() {
        return dataEntryDate;
    }

    public void setDataEntryDate(Date dataEntryDate) {
        this.dataEntryDate = dataEntryDate;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Boolean getIsApproval() {
        return isApproval;
    }

    public void setIsApproval(Boolean isApproval) {
        this.isApproval = isApproval;
    }


    public StudentSchool getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(StudentSchool studentSchool) {
        this.studentSchool = studentSchool;
    }

    public TeacherAssign getTeacherAssign() {
        return teacherAssign;
    }

    public void setTeacherAssign(TeacherAssign teacherAssign) {
        this.teacherAssign = teacherAssign;
    }

    public SchoolWeek getSchoolWeek() {
        return schoolWeek;
    }

    public void setSchoolWeek(SchoolWeek schoolWeek) {
        this.schoolWeek = schoolWeek;
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
        if (!(object instanceof WeekMark)) {
            return false;
        }
        WeekMark other = (WeekMark) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.WeekMark[ id=" + id + " ]";
    }

 
    
}
