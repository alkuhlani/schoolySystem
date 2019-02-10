/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.ClassBean;
import com.megasourceye.schooly.ejbs.StudentSchoolBean;
import com.megasourceye.schooly.ejbs.TeacherAssignBean;
import com.megasourceye.schooly.ejbs.Term3MarkBean;
import com.megasourceye.schooly.ejbs.TermBean;
import com.megasourceye.schooly.ejbs.TermWeekBean;
import com.megasourceye.schooly.ejbs.WeekMarkBean;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.StudentSchool;
import com.megasourceye.schooly.entities.Subject_t;
import com.megasourceye.schooly.entities.TeacherAssign;
import com.megasourceye.schooly.entities.Term;
import com.megasourceye.schooly.entities.Term3Mark;
import com.megasourceye.schooly.entities.TermWeek;
import com.megasourceye.schooly.entities.WeekMark;
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
@Named(value = "term3MarkMB")
@ViewScoped
public class Term3MarkMB implements Serializable {

    /**
     * Creates a new instance of Term3MarkMB
     */
    public Term3MarkMB() {
    }
    private Term3Mark item;
    private Class_ class_;
    private List<Term3Mark> items;
    private List<Class_> class_s;
    private List<TeacherAssign> teacherAssigns;
    private List<StudentSchool> studentSchools;
    private List<WeekMark> weekMarks;
    private List<TermWeek> termWeeks;
    private Long teacherAssignId;
    private Long subjectId;

    @Inject
    private Term3MarkBean term3MarkBean;
    @Inject
    private ClassBean classBean;
    @Inject
    private TeacherAssignBean teacherAssignBean;
    @Inject
    private StudentSchoolBean studentSchoolBean;
    @Inject
    private WeekMarkBean weekMarkBean;
    @Inject
    private TermWeekBean termWeekBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
//        items = schoolBean.findAll();
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = term3MarkBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = term3MarkBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = term3MarkBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEA-EMP")) {
                items = term3MarkBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                items = term3MarkBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                items = term3MarkBean.findBySchoolYearIdAndStudentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getStudent().getId());
            }
        }
    }

    public void prepareCreate() {
        item = new Term3Mark();
        class_ = new Class_();
        item.setStudentSchool(new StudentSchool());
        item.setSubject_t(new Subject_t());
        if (loginMB.getUser().getSchool().getSystemVariables().getTermWeek() != null) {
            item.setTermWeek(loginMB.getUser().getSchool().getSystemVariables().getTermWeek());
        } else {
            item.setTermWeek(new TermWeek());
        }
        termWeeks = termWeekBean.findBySchoolYearId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEA-EMP")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                teacherAssigns = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
            }
        }

    }

    public void prepareCreate(Long termWeekId,
            Long studentSchoolId,
            Long classId,
            Long subjectId) {
        item = new Term3Mark();
        class_ = classBean.find(classId);
        item.setStudentSchool(new StudentSchool(studentSchoolId));
        item.setSubject_t(new Subject_t(subjectId));
        if (loginMB.getUser().getSchool().getSystemVariables().getTermWeek() != null) {
            item.setTermWeek(loginMB.getUser().getSchool().getSystemVariables().getTermWeek());
        } else {
            item.setTermWeek(new TermWeek());
        }
        termWeeks = termWeekBean.findBySchoolYearId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                classId);
        teacherAssigns = teacherAssignBean.findBySchoolYearIdAndClassId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                classId);
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEA-EMP")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                teacherAssigns = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
            }
        }

    }

    public void prepareUpdate(Long id2) {
        item = term3MarkBean.find(id2);
        class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        class_ = new Class_(item.getStudentSchool().getClass_().getId());

        termWeeks = termWeekBean.findBySchoolYearId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                class_.getId());
        teacherAssigns = teacherAssignBean.findBySchoolYearIdAndClassId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                class_.getId());
    }

    public void create() {
        if (term3MarkBean.findByStudentSAndSubjectIdAndTermId(
                item.getStudentSchool().getId(),
                item.getSubject_t().getId(),
                item.getTermWeek().getId()).size() >= 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Is Existing"));
        } else {
            term3MarkBean.create(item);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
            prepareCreate(item.getTermWeek().getId(),
                item.getStudentSchool().getId(),
                class_.getId(),
                item.getSubject_t().getId());
        }
        
        //  return "items?faces-redirect=true";
    }

    public String update() {
        System.out.println("11111");
        term3MarkBean.update(item);
        System.out.println("2222222222");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        term3MarkBean.delete(item);
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

    public void studentListner() {
        System.out.println("teacherAssignId="+teacherAssignId);
        subjectId = teacherAssignBean.find(teacherAssignId).getSubject_t().getId();
        class_=new Class_();
        class_=classBean.find(teacherAssignBean.find(teacherAssignId).getClass_().getId());
        System.out.println("subjectId="+subjectId);
        System.out.println("class_="+class_.getId());
        item.setSubject_t(new Subject_t(subjectId));
        studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                class_.getId());
        System.out.println("studentSchools.size="+studentSchools.size());
    }

    public void calcTermMarkHalf1Listner() {
        System.out.println(" ------------1");
        if (item.getStudentSchool().getId() != null
                && item.getSubject_t().getId() != null
                && item.getTermWeek().getId() != null) {
            System.out.println("------------2");
            System.out.println(item.getStudentSchool().getId());
            System.out.println(item.getSubject_t().getId());
            System.out.println(item.getTermWeek().getId());
            System.out.println("------------3");

            float to = 0.0F;
            System.out.println(to);
            System.out.println("------------4");
            weekMarks = weekMarkBean.findByStudentSIdAndSubjectIdAndTermWeekAndTermT(
                    item.getStudentSchool().getId(),
                    item.getSubject_t().getId(),
                    item.getTermWeek().getId(), '1');
            System.out.println("------------" + weekMarks.size());
            System.out.println("------------5");
            for (int i = 0; i < weekMarks.size(); i++) {
                System.out.println("------------6");
                to += weekMarks.get(i).getQuize()
                        + weekMarks.get(i).getAttendance()
                        + weekMarks.get(i).getBehavior()
                        + weekMarks.get(i).getHw();
                System.out.println(to);
                System.out.println("------------7");
            }
            if (weekMarks.size() != item.getTermWeek().getNoFirst()) {
                System.out.println("------------8");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Weeks is less"));
            } else {
                System.out.println("------------9");
                System.out.println(to);
                to = to / item.getTermWeek().getNoFirst();
                System.out.println(to);
                float t = 0;
                if (loginMB.getUser().getSchool().getSystemVariables().getWeekTopMark().getTotal() > loginMB.getUser().getSchool().getSystemVariables().getTermTopMark().getFirstHalfMark()) {
                    t = loginMB.getUser().getSchool().getSystemVariables().getWeekTopMark().getTotal()
                            / loginMB.getUser().getSchool().getSystemVariables().getTermTopMark().getFirstHalfMark();
                    to = to / t;
                } else {
                    t = loginMB.getUser().getSchool().getSystemVariables().getTermTopMark().getFirstHalfMark()
                            / loginMB.getUser().getSchool().getSystemVariables().getWeekTopMark().getTotal();
                    to = to * t;
                }
                System.out.println(to);
                item.setFirstHalfMark(to);
                System.out.println(to);
                System.out.println("------------10");
            }

        } else {
            System.out.println("------------100");
            item.setFirstHalfMark(0.0F);
        }

    }

    public void calcTermMarkHalf2Listner() {
        if (item.getStudentSchool().getId() != null
                && item.getSubject_t().getId() != null
                && item.getTermWeek().getId() != null) {
            System.out.println(item.getStudentSchool().getId());
            System.out.println(item.getSubject_t().getId());
            System.out.println(item.getTermWeek().getId());
            float to = 0.0F;
            System.out.println(to);
            weekMarks = weekMarkBean.findByStudentSIdAndSubjectIdAndTermWeekAndTermT(
                    item.getStudentSchool().getId(),
                    item.getSubject_t().getId(),
                    item.getTermWeek().getId(), '2');
            for (int i = 0; i < weekMarks.size(); i++) {
                to += weekMarks.get(i).getQuize()
                        + weekMarks.get(i).getAttendance()
                        + weekMarks.get(i).getBehavior()
                        + weekMarks.get(i).getHw();
                System.out.println(to);
            }
            if (weekMarks.size() != item.getTermWeek().getNoEnd()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "Weeks is less"));
            } else {
                System.out.println(to);
                to = to / item.getTermWeek().getNoFirst();
                System.out.println(to);
                float t = 0;
                if (loginMB.getUser().getSchool().getSystemVariables().getWeekTopMark().getTotal() > loginMB.getUser().getSchool().getSystemVariables().getTermTopMark().getFirstHalfMark()) {
                    t = loginMB.getUser().getSchool().getSystemVariables().getWeekTopMark().getTotal()
                            / loginMB.getUser().getSchool().getSystemVariables().getTermTopMark().getFirstHalfMark();
                    to = to / t;
                } else {
                    t = loginMB.getUser().getSchool().getSystemVariables().getTermTopMark().getFirstHalfMark()
                            / loginMB.getUser().getSchool().getSystemVariables().getWeekTopMark().getTotal();
                    to = to * t;
                }
                System.out.println(to);
                item.setFirstHalfMark(to);
                System.out.println(to);
            }
        } else {
            item.setFirstHalfMark(0.0F);
        }

    }

    public void calc2() {
        System.out.println(item.getStudentSchool().getId());
    }

    public void calc3() {
        System.out.println(item.getSubject_t().getId());
    }

    public Term3Mark getItem() {
        return item;
    }

    public void setItem(Term3Mark item) {
        this.item = item;
    }

    public List<Term3Mark> getItems() {
        return items;
    }

    public void setItems(List<Term3Mark> items) {
        this.items = items;
    }

    public Class_ getClass_() {
        return class_;
    }

    public void setClass_(Class_ class_) {
        this.class_ = class_;
    }

    public List<Class_> getClass_s() {
        return class_s;
    }

    public void setClass_s(List<Class_> class_s) {
        this.class_s = class_s;
    }

    public List<TeacherAssign> getTeacherAssigns() {
        return teacherAssigns;
    }

    public void setTeacherAssigns(List<TeacherAssign> teacherAssigns) {
        this.teacherAssigns = teacherAssigns;
    }

    public List<StudentSchool> getStudentSchools() {
        return studentSchools;
    }

    public void setStudentSchools(List<StudentSchool> studentSchools) {
        this.studentSchools = studentSchools;
    }

    public List<WeekMark> getWeekMarks() {
        return weekMarks;
    }

    public void setWeekMarks(List<WeekMark> weekMarks) {
        this.weekMarks = weekMarks;
    }

    public List<TermWeek> getTermWeeks() {
        return termWeeks;
    }

    public void setTermWeeks(List<TermWeek> termWeeks) {
        this.termWeeks = termWeeks;
    }

    public Long getTeacherAssignId() {
        return teacherAssignId;
    }

    public void setTeacherAssignId(Long teacherAssignId) {
        this.teacherAssignId = teacherAssignId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

}
