/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.ClassBean;
import com.megasourceye.schooly.ejbs.StudentSchoolBean;
import com.megasourceye.schooly.ejbs.SubjectBean;
import com.megasourceye.schooly.ejbs.TeacherAssignBean;
import com.megasourceye.schooly.ejbs.TeacherBean;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.SchoolYear;
import com.megasourceye.schooly.entities.Subject_t;
import com.megasourceye.schooly.entities.Teacher;
import com.megasourceye.schooly.entities.TeacherAssign;
import java.io.Serializable;
import java.util.AbstractList;
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
@Named(value = "teacherAssignMB")
@ViewScoped
public class TeacherAssignMB implements Serializable {

    /**
     * Creates a new instance of TeacherAssignMB
     */
    public TeacherAssignMB() {
    }
    private TeacherAssign item;
    private List<TeacherAssign> items;
    private List<Class_> classes;
    private List<Teacher> teachers;
    private List<Subject_t> subject_ts;

    @Inject
    private TeacherAssignBean teacherAssignBean;
    @Inject
    private ClassBean classBean;
    @Inject
    private TeacherBean teacherBean;
    @Inject
    private SubjectBean subjectBean;
    @Inject
    private StudentSchoolBean studentSchoolBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        System.out.println("1");
        System.out.println("1");
        System.out.println("1");
        if (loginMB.getUser() != null) {
            System.out.println("1");
            System.out.println("1");
            System.out.println("1");
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = teacherAssignBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = teacherAssignBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = teacherAssignBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                items = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                System.out.println("1");
                System.out.println("1");
                System.out.println("1");
                System.out.println(studentSchoolBean.findBySchoolYearAndStudentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getStudent().getId()).get(0).getId());
                System.out.println(studentSchoolBean.findBySchoolYearAndStudentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getStudent().getId()).get(0).getClass_().getId());
                items = teacherAssignBean.findBySchoolYearIdAndClassId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        studentSchoolBean.findBySchoolYearAndStudentId(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                loginMB.getUser().getStudent().getId()).get(0).getClass_().getId());
            }
        }
    }

    public void prepareCreate() {
        item = new TeacherAssign();
        item.setClass_(new Class_());
        item.setTeacher(new Teacher());
        item.setSubject_t(new Subject_t());
        item.setSchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear());

        classes = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        teachers = teacherBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
    }

    public void prepareCreate(Long teacherId, Long classId, Long subjectId) {
        System.out.println("1");
        item = new TeacherAssign();
        System.out.println("2");
        item.setClass_(new Class_(classId));
        System.out.println("3");
        item.setTeacher(new Teacher(teacherId));
        System.out.println("4");
        item.setSubject_t(new Subject_t(subjectId));
        System.out.println("5");
        item.setSchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear());
        System.out.println("6");
        classes = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        System.out.println("7");
        teachers = teacherBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        System.out.println("8");
        subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        System.out.println("19");
    }

    public void prepareUpdate(Long id) {
        item = teacherAssignBean.find(id);
        classes = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        teachers = teacherBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
    }

    public void create() {
        System.out.println(item.getSchoolYear().getId());
        System.out.println(item.getClass_().getId());
        System.out.println(item.getSubject_t().getId());

        if (teacherAssignBean.findBySchoolYearIdAndClassIdAndSubjectId(item.getSchoolYear().getId(),
                item.getClass_().getId(), item.getSubject_t().getId()).size() >= 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Record is Exist"));
        } else {
            if (teacherAssignBean.findBySchoolYearIdAndClassIdAndTeacherIdAndSubjectId(item.getSchoolYear().getId(),
                    item.getClass_().getId(), item.getTeacher().getId(), item.getSubject_t().getId()).size() >= 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Record is Exist"));

            } else {
                teacherAssignBean.create(item);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));

            }
        }
        System.out.println(" before prepareCreate ");
        prepareCreate(
                item.getTeacher().getId(),
                item.getClass_().getId(),
                item.getSubject_t().getId());
        System.out.println(" after prepareCreate ");
        //  return "items?faces-redirect=true";
    }

    public String update() {
        teacherAssignBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        teacherAssignBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public void updateMsgListner() {

    }

    public TeacherAssign getItem() {
        return item;
    }

    public void setItem(TeacherAssign item) {
        this.item = item;
    }

    public List<TeacherAssign> getItems() {
        return items;
    }

    public void setItems(List<TeacherAssign> items) {
        this.items = items;
    }

    public List<Class_> getClasses() {
        return classes;
    }

    public void setClasses(List<Class_> classes) {
        this.classes = classes;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public List<Subject_t> getSubject_ts() {
        return subject_ts;
    }

    public void setSubject_ts(List<Subject_t> subject_ts) {
        this.subject_ts = subject_ts;
    }

}
