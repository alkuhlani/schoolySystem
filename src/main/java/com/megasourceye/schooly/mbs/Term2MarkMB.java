/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.ClassBean;
import com.megasourceye.schooly.ejbs.MonthMarkBean;
import com.megasourceye.schooly.ejbs.SchoolMonthBean;
import com.megasourceye.schooly.ejbs.StudentSchoolBean;
import com.megasourceye.schooly.ejbs.SubjectBean;
import com.megasourceye.schooly.ejbs.TeacherAssignBean;
import com.megasourceye.schooly.ejbs.Term2MarkBean;
import com.megasourceye.schooly.ejbs.TermMonthBean;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.MonthMark;
import com.megasourceye.schooly.entities.StudentSchool;
import com.megasourceye.schooly.entities.Subject_t;
import com.megasourceye.schooly.entities.TeacherAssign;
import com.megasourceye.schooly.entities.Term2MArk;
import com.megasourceye.schooly.entities.TermMonth;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Named(value = "term2MarkMB")
@ViewScoped
public class Term2MarkMB implements Serializable {

    /**
     * Creates a new instance of Term2MArkMB
     */
    public Term2MarkMB() {
    }
    private Term2MArk item;
    private Class_ class_;
    private TeacherAssign teacherAssign;
    private List<Term2MArk> items;
    private List<StudentSchool> studentSchools;
    private List<Subject_t> subject_ts;
    private List<Class_> class_s;
    private List<TeacherAssign> teacherAssigns;
    private List<TermMonth> termMonths;
    private List<MonthMark> monthMarks;

    @Inject
    private Term2MarkBean term2MarkBean;
    @Inject
    private StudentSchoolBean studentSchoolBean;
    @Inject
    private SubjectBean subjectBean;
    @Inject
    private ClassBean classBean;
    @Inject
    private TeacherAssignBean teacherAssignBean;
    @Inject
    private SchoolMonthBean schoolMonthBean;
    @Inject
    private TermMonthBean termMonthBean;
    @Inject
    private MonthMarkBean monthMarkBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
//        items = schoolBean.findAll();
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = term2MarkBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
//                items = term2MarkBean.findAll();
                items = term2MarkBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {

            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {

            }
        }

    }

    public void prepareCreate() {
        item = new Term2MArk();
        item.setStudentSchool(new StudentSchool());
        item.setSubject_t(new Subject_t());
        class_ = new Class_();
        item.setTermMonth(new TermMonth());
        if (loginMB.getUser().getSchool().getSystemVariables().getTermMonth() != null) {
            item.setTermMonth(loginMB.getUser().getSchool().getSystemVariables().getTermMonth());
        } else {
            item.setTermMonth(new TermMonth());
        }
        class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        System.out.println(class_s.size());
        termMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        termMonths = termMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
    }
    public void prepareCreate(long termId,long classId,long subjectId,long studentId) {
        
        item = new Term2MArk();
        item.setTermMonth(new TermMonth(termId));
        item.setStudentSchool(new StudentSchool(studentId));
        item.setSubject_t(new Subject_t(subjectId));
        class_=new Class_(classId);
        class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        System.out.println(class_s.size());
        termMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        termMonths = termMonthBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
    }

    public void prepareUpdate(Long id2) {
        item = term2MarkBean.find(id2);
    }

    public void create() {
        if (item.getStudentSchool().getId() == null
                || item.getSubject_t().getId() == null
                || item.getTermMonth().getId() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Is Emopty"));
        } else if (term2MarkBean.findByStudentSAndSubjectIdAndTermId(
                item.getStudentSchool().getId(),
                item.getSubject_t().getId(),
                item.getTermMonth().getId()).size() >= 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Is Founded"));
        } else {
            System.out.println("1");
            System.out.println("1");
            System.out.println("1");
            System.out.println("1");
            term2MarkBean.create(item);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
            prepareCreate(item.getTermMonth().getId(),class_.getId(),item.getSubject_t().getId(),item.getStudentSchool().getId());
        }

        //  return "items?faces-redirect=true";
    }

    public String update() {
        term2MarkBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        term2MarkBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public void classListner() {
        studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                class_.getId());
        teacherAssigns = teacherAssignBean.findBySchoolYearIdAndClassId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                class_.getId());
    }

    public void calcTermMarkListner() {
        if (item.getStudentSchool().getId() != null && item.getSubject_t().getId() != null && item.getTermMonth().getId() != null) {
            System.out.println(item.getStudentSchool().getId());
            System.out.println(item.getSubject_t().getId());
            System.out.println(item.getTermMonth().getId());
            float to = 0.0F;
            System.out.println(to);
            monthMarks = monthMarkBean.findByStudentSIdAndSubjectIdAndTermMonth(item.getStudentSchool().getId(),
                    item.getSubject_t().getId(), item.getTermMonth().getId());
            for (int i = 0; i < monthMarks.size(); i++) {
                to += monthMarks.get(i).getWriting()
                        + monthMarks.get(i).getReading()
                        + monthMarks.get(i).getBehavior()
                        + monthMarks.get(i).getHw();
                System.out.println(to);
            }
            System.out.println(to);
            to = to / item.getTermMonth().getNoMonths();
            System.out.println(to);
            to = to / 5;
            System.out.println(to);
            item.setMark(to);
            System.out.println(to);
        } else {
            item.setMark(0.0F);
        }

    }

    public void calc2() {
        System.out.println(item.getStudentSchool().getId());
    }

    public void calc3() {
        System.out.println(item.getSubject_t().getId());
    }

    public Term2MArk getItem() {
        return item;
    }

    public void setItem(Term2MArk item) {
        this.item = item;
    }

    public List<Term2MArk> getItems() {
        return items;
    }

    public void setItems(List<Term2MArk> items) {
        this.items = items;
    }

    public List<StudentSchool> getStudentSchools() {
        return studentSchools;
    }

    public void setStudentSchools(List<StudentSchool> studentSchools) {
        this.studentSchools = studentSchools;
    }

    public List<Subject_t> getSubject_ts() {
        return subject_ts;
    }

    public void setSubject_ts(List<Subject_t> subject_ts) {
        this.subject_ts = subject_ts;
    }

    public List<Class_> getClass_s() {
        return class_s;
    }

    public void setClass_s(List<Class_> class_s) {
        this.class_s = class_s;
    }

    public Class_ getClass_() {
        return class_;
    }

    public void setClass_(Class_ class_) {
        this.class_ = class_;
    }

    public List<TeacherAssign> getTeacherAssigns() {
        return teacherAssigns;
    }

    public void setTeacherAssigns(List<TeacherAssign> teacherAssigns) {
        this.teacherAssigns = teacherAssigns;
    }

    public List<TermMonth> getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(List<TermMonth> termMonths) {
        this.termMonths = termMonths;
    }

    public List<MonthMark> getMonthMarks() {
        return monthMarks;
    }

    public void setMonthMarks(List<MonthMark> monthMarks) {
        this.monthMarks = monthMarks;
    }

    public TeacherAssign getTeacherAssign() {
        return teacherAssign;
    }

    public void setTeacherAssign(TeacherAssign teacherAssign) {
        this.teacherAssign = teacherAssign;
    }

}
