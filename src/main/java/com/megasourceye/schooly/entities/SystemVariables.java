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

/**
 *
 * @author said
 */
//جدول متغيرات النظام حيث سيكون هنا سجل واحد لكل مدرسة
//اضافة القسم عربي انجليزي
//add first id for student-teacher-employee
//add schoolMonth - add SchoolWeek
@Entity
@NamedQueries({
    @NamedQuery(name = "SystemVariables.findAll", query = "SELECT q FROM SystemVariables q"),
    @NamedQuery(name = "SystemVariables.findBySchool", query = "SELECT q FROM SystemVariables q WHERE q.school.id=:id"),
    @NamedQuery(name = "SystemVariables.findBySchoolId", query = "SELECT q FROM SystemVariables q WHERE q.school.id=:id")
})
public class SystemVariables implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   
    private Long firstID;
    @ManyToOne
    private TermType termType; //نوع نظام الاترام ترمين او ثلاثة اترام
    @OneToOne
    private School school;
    @OneToOne
    private SchoolYear schoolYear; // العام الدراسي التي ستجري البيانات علية
    @OneToOne
    private OH officeHoliday; // ايام الاجازة 
    @OneToOne
    private TermWeek termWeek; // عدد الاسابيع الترم الواحد - الترم الحالي التي تعمل علية الجامعه     
    @ManyToOne
     private TermMonth termMonth;// الترم الحالي - عدد الاشهر
    private Character currentTermHalf;// ترم النصف الحالي- اذا هم في النصف الاول من التر =1 - اذا كان في النصف الثاني من الترم =2
    @OneToOne
    private WeekTopMark weekTopMark;
    @OneToOne
    private MonthTopMark monthTopMark;
    @OneToOne
    private Term2TopMark term2TopMark;
    @OneToOne
    private TermTopMark termTopMark;
    @OneToOne
    private SchoolWeek schoolWeek;
    @OneToOne
    private SchoolMonth schoolMonth;
    private Character termHalf;
    private Character chatStatus;
    //=1 ; parent yes ; student no
    //=2 ; parent no ; student yes
    //=3 ; parent yes ; student yes
    //=4 ; parent no ; student no
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public TermType getTermType() {
        return termType;
    }

    public void setTermType(TermType termType) {
        this.termType = termType;
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

    public OH getOfficeHoliday() {
        return officeHoliday;
    }

    public void setOfficeHoliday(OH officeHoliday) {
        this.officeHoliday = officeHoliday;
    }

    public TermWeek getTermWeek() {
        return termWeek;
    }

    public void setTermWeek(TermWeek termWeek) {
        this.termWeek = termWeek;
    }

    public Character getCurrentTermHalf() {
        return currentTermHalf;
    }

    public void setCurrentTermHalf(Character currentTermHalf) {
        this.currentTermHalf = currentTermHalf;
    }

    public WeekTopMark getWeekTopMark() {
        return weekTopMark;
    }

    public void setWeekTopMark(WeekTopMark weekTopMark) {
        this.weekTopMark = weekTopMark;
    }

    public TermTopMark getTermTopMark() {
        return termTopMark;
    }

    public void setTermTopMark(TermTopMark termTopMark) {
        this.termTopMark = termTopMark;
    }

    public Long getFirstID() {
        return firstID;
    }

    public void setFirstID(Long firstID) {
        this.firstID = firstID;
    }

    public SchoolWeek getSchoolWeek() {
        return schoolWeek;
    }

    public void setSchoolWeek(SchoolWeek schoolWeek) {
        this.schoolWeek = schoolWeek;
    }

    public SchoolMonth getSchoolMonth() {
        return schoolMonth;
    }

    public void setSchoolMonth(SchoolMonth schoolMonth) {
        this.schoolMonth = schoolMonth;
    }

    public Character getTermHalf() {
        return termHalf;
    }

    public void setTermHalf(Character termHalf) {
        this.termHalf = termHalf;
    }

    public TermMonth getTermMonth() {
        return termMonth;
    }

    public void setTermMonth(TermMonth termMonth) {
        this.termMonth = termMonth;
    }

    public MonthTopMark getMonthTopMark() {
        return monthTopMark;
    }

    public void setMonthTopMark(MonthTopMark monthTopMark) {
        this.monthTopMark = monthTopMark;
    }

    public Term2TopMark getTerm2TopMark() {
        return term2TopMark;
    }

    public void setTerm2TopMark(Term2TopMark term2TopMark) {
        this.term2TopMark = term2TopMark;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Character getChatStatus() {
        return chatStatus;
    }

    public void setChatStatus(Character chatStatus) {
        this.chatStatus = chatStatus;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SystemVariables)) {
            return false;
        }
        SystemVariables other = (SystemVariables) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.megasourceye.schooly.entities.SystemVariables[ id=" + id + " ]";
    }
    
}
