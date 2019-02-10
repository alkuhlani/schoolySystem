/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.ClassBean;
import com.megasourceye.schooly.ejbs.HomeWorkBaen;
import com.megasourceye.schooly.ejbs.StudentSchoolBean;
import com.megasourceye.schooly.ejbs.SubjectBean;
import com.megasourceye.schooly.ejbs.SubjectTableBean;
import com.megasourceye.schooly.ejbs.TeacherAssignBean;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.HomeWork;
import com.megasourceye.schooly.entities.StudentSchool;
import com.megasourceye.schooly.entities.SubjectTable;
import com.megasourceye.schooly.entities.Subject_t;
import com.megasourceye.schooly.entities.TeacherAssign;
import java.io.Serializable;
import java.util.Date;
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
@Named(value = "homeWorkMB")
@ViewScoped
public class HomeWorkMB implements Serializable {

    /**
     * Creates a new instance of HomeWorkMB
     */
    public HomeWorkMB() {
    }

    private HomeWork item;
    private List<HomeWork> items;
    private List<TeacherAssign> teacherAssigns;
    private List<SubjectTable> subjectTables;
    private List<StudentSchool> studentSchools;
    private List<Class_> class_s;
    private List<Subject_t> subject_ts;
    private long subjectId;
    private long classId;
    private long teacherAssignId;
    private Date startDate;
    private Date endDate;

    @Inject
    private HomeWorkBaen homeWorkBaen;
    @Inject
    private TeacherAssignBean teacherAssignBean;
    @Inject
    private SubjectTableBean subjectTableBean;
    @Inject
    private StudentSchoolBean studentSchoolBean;
    @Inject
    private ClassBean classBean;
    @Inject
    private SubjectBean subjectBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {

//        items = schoolBean.findAll();
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = homeWorkBaen.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {

                items = homeWorkBaen.findBySchoolYearAndDate(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        new java.util.Date());
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = homeWorkBaen.findBySchoolYearAndDate(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        new java.util.Date());
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                items = homeWorkBaen.findBySchoolYearAndTeahcerIdAndDate(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId(),
                        new java.util.Date());
                teacherAssigns = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
                subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                studentSchools = studentSchoolBean.findBySchoolYearAndParentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getParent().getId());
                int s = studentSchools.size();
                System.out.println("1111");
                System.out.println(s);
                System.out.println(s);
                System.out.println(s);
                System.out.println("1111");
                System.out.println("1111");
                System.out.println(studentSchools.get(0).getClass_().getId());
                if (s >= 1) {
                    System.out.println("1111000");
                    items = homeWorkBaen.findBySchoolYearAndClassAndDate(
                            loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                            studentSchools.get(0).getClass_().getId(),
                            new Date());
                    for (int i = 1; i < s; i++) {
                        System.out.println("1111222");
                        items.addAll(homeWorkBaen.findBySchoolYearAndClassAndDate(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                studentSchools.get(i).getClass_().getId(),
                                new Date()));
                    }
                }
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {

                items = homeWorkBaen.findBySchoolYearAndClassAndDate(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        studentSchoolBean.findBySchoolYearAndStudentId(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                loginMB.getUser().getStudent().getId()).get(0).getClass_().getId(),
                        new java.util.Date()
                );
                subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                System.out.println(studentSchoolBean.findBySchoolYearAndStudentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getStudent().getId()).get(0).getClass_().getId());
                System.out.println(studentSchoolBean.findBySchoolYearAndStudentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getStudent().getId()).get(0).getClass_().getId());
                System.out.println(studentSchoolBean.findBySchoolYearAndStudentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getStudent().getId()).get(0).getClass_().getId());
            }
        }

    }

    public void prepareCreate() {
        System.out.println("1");
        item = new HomeWork();
        System.out.println("2");
        item.setSubjectTable(new SubjectTable());
        item.setDateEntry(new java.util.Date());
        System.out.println("3");
        if (loginMB.getUser() != null) {
            System.out.println("4");
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                System.out.println("5");
                items = homeWorkBaen.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                System.out.println("6");
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                System.out.println("7");
                subjectTables = subjectTableBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                System.out.println("6");
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            }
        }
    }

    public void prepareUpdate(Long id2) {
        item = homeWorkBaen.find(id2);
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = homeWorkBaen.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                subjectTables = subjectTableBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                subjectTables = subjectTableBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
            }
        }
    }

    public void create() {
        System.out.println("-------" + homeWorkBaen.findBySubjectTAndDate(
                item.getSubjectTable().getId(),
                item.getDateEntry()));
        if (homeWorkBaen.findBySubjectTAndDate(item.getSubjectTable().getId(),
                item.getDateEntry()).size() >= 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Is founded"));
        } else {
            homeWorkBaen.create(item);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        }

        //  return "items?faces-redirect=true";
    }

    public String update() {
        homeWorkBaen.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        homeWorkBaen.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public void subjectTableListner() {
        if (classId > 0 && subjectId > 0) {
            subjectTables = subjectTableBean.findBySchoolYearIdAndSubjectIdAndClassId(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    subjectId,
                    classId);
        }
    }

    public void search() {
        if (loginMB.getUser().getRole_().equals("ADMIN")) {
            System.err.println("111");
            System.err.println(classId);
            System.err.println(subjectId);
            if (classId > 0 && subjectId > 0) {
                System.err.println("222");
                items = homeWorkBaen.findBySchoolYearAndClassAndSubjectIdAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId,
                        subjectId,
                        startDate,
                        endDate);
            } else if (subjectId == -1) {
                System.err.println("333");
                items = homeWorkBaen.findBySchoolYearAndClassAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId,
                        startDate,
                        endDate);
            }
        } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
            if (classId > 0 && subjectId > 0) {
                items = homeWorkBaen.findBySchoolYearAndClassAndSubjectIdAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId,
                        subjectId,
                        startDate,
                        endDate);
            } else if (subjectId == -1) {
                items = homeWorkBaen.findBySchoolYearAndClassAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId,
                        startDate,
                        endDate);
            }
        } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
            items = homeWorkBaen.findBySchoolYearAndTeahcerAssignIdAnd2Date(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    teacherAssignId,
                    startDate,
                    endDate);
        } else if (loginMB.getUser().getRole_().equals("PARENT")) {
            if (classId > 0 && subjectId > 0) {
                items = homeWorkBaen.findBySchoolYearAndClassAndSubjectIdAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId,
                        subjectId,
                        startDate,
                        endDate);
            } else if (subjectId == -1) {
                items = homeWorkBaen.findBySchoolYearAndClassAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId,
                        startDate,
                        endDate);
            }
        } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
            System.out.println("subject = " + subjectId);
            if (subjectId > 0) {
                items = homeWorkBaen.findBySchoolYearAndClassAndSubjectIdAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        studentSchoolBean.findBySchoolYearAndStudentId(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                loginMB.getUser().getStudent().getId()).get(0).getClass_().getId(),
                        subjectId,
                        startDate,
                        endDate);
            } else if (subjectId == -1) {
                items = homeWorkBaen.findBySchoolYearAndClassAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        studentSchoolBean.findBySchoolYearAndStudentId(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                loginMB.getUser().getStudent().getId()).get(0).getClass_().getId(),
                        startDate,
                        endDate);
            }
        }

    }

    public HomeWork getItem() {
        return item;
    }

    public void setItem(HomeWork item) {
        this.item = item;
    }

    public List<HomeWork> getItems() {
        return items;
    }

    public void setItems(List<HomeWork> items) {
        this.items = items;
    }

    public List<TeacherAssign> getTeacherAssigns() {
        return teacherAssigns;
    }

    public void setTeacherAssigns(List<TeacherAssign> teacherAssigns) {
        this.teacherAssigns = teacherAssigns;
    }

    public List<SubjectTable> getSubjectTables() {
        return subjectTables;
    }

    public void setSubjectTables(List<SubjectTable> subjectTables) {
        this.subjectTables = subjectTables;
    }

    public List<StudentSchool> getStudentSchools() {
        return studentSchools;
    }

    public void setStudentSchools(List<StudentSchool> studentSchools) {
        this.studentSchools = studentSchools;
    }

    public List<Class_> getClass_s() {
        return class_s;
    }

    public void setClass_s(List<Class_> class_s) {
        this.class_s = class_s;
    }

    public List<Subject_t> getSubject_ts() {
        return subject_ts;
    }

    public void setSubject_ts(List<Subject_t> subject_ts) {
        this.subject_ts = subject_ts;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public long getTeacherAssignId() {
        return teacherAssignId;
    }

    public void setTeacherAssignId(long teacherAssignId) {
        this.teacherAssignId = teacherAssignId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
