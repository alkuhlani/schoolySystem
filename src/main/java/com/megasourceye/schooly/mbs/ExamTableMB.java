/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.ClassBean;
import com.megasourceye.schooly.ejbs.ExamBean;
import com.megasourceye.schooly.ejbs.ExamTableBean;
import com.megasourceye.schooly.ejbs.SubjectBean;
import com.megasourceye.schooly.ejbs.TeacherAssignBean;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.Exam;
import com.megasourceye.schooly.entities.ExamTable;
import com.megasourceye.schooly.entities.Subject_t;
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
@Named(value = "examTableMB")
@ViewScoped
public class ExamTableMB implements Serializable {

    /**
     * Creates a new instance of ExamTableMB
     */
    public ExamTableMB() {
    }
    private ExamTable item;
    private List<ExamTable> items;
    private List<Class_> class_s;
    private List<Subject_t> subject_ts;
    private List<Exam> exams;

    @Inject
    private ExamTableBean examTableBean;
    @Inject
    private ClassBean classBean;
    @Inject
    private SubjectBean subjectBean;
    @Inject
    private ExamBean examBean;
    @Inject
    private TeacherAssignBean teacherAssignBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = examTableBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = examTableBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                int size = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId()).size();
                if (size >= 1) {
                    for (int i = 0; i < size; i++) {
                        long subjectId
                                = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                        loginMB.getUser().getTeacher().getId()).get(i).getSubject_t().getId();
                        long classId
                                = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                        loginMB.getUser().getTeacher().getId()).get(i).getClass_().getId();
                        items.addAll(examTableBean.findBySchoolYearIdAndSubjectIdAndClassId(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                subjectId,
                                classId));

                    }

                }

                teacherAssignBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
            }
        }

    }

    public void prepareCreate() {
        System.out.println("1");
        System.out.println("1");
        System.out.println("1");
        System.out.println("1");
        item = new ExamTable();
        item.setSubject_t(new Subject_t());
        item.setClass_(new Class_());
        class_s = classBean.findBySchoolId(
                loginMB.getUser().getSchool().getId());
        subject_ts = subjectBean.findBySchoolId(
                loginMB.getUser().getSchool().getId());
        if (loginMB.getUser().getSchool().getSystemVariables().getTermType().getId() == 1) {
            System.out.println("8");
            System.out.println("8");
            System.out.println("8");
            System.out.println("8");
            exams = examBean.findBySchoolYearIdAndTermMonth(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getId());
            System.out.println(exams.size());
            if (exams.size() >= 1) {
                item.setExam(exams.get(0));
            } else {
                item.setExam(new Exam());
            }
        } else if (loginMB.getUser().getSchool().getSystemVariables().getTermType().getId() == 2) {
            System.out.println("6");
            System.out.println("6");
            System.out.println("6");
            System.out.println("6");
            exams = examBean.findBySchoolYearIdAndTermWeek(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getId());
            System.out.println("exams size" + exams.size());
            System.out.println("exams size" + exams.size());
            System.out.println("exams size" + exams.size());
            System.out.println("exams size" + exams.size());
            if (exams.size() >= 1) {
                item.setExam(exams.get(0));
            } else {
                item.setExam(new Exam());
            }
        }
    }

    public void prepareCreate(long examId,
            long classId,
            long subjectId,
            Date examDate,
            Date beginTime,
            Date examTimeDuring) {
        item = new ExamTable();
        item.setSubject_t(new Subject_t());
        item.setClass_(new Class_());
        item.setExamDate(examDate);
        item.setBeginTime(beginTime);
        item.setExamTimeDuring(examTimeDuring);
        class_s = classBean.findBySchoolId(
                loginMB.getUser().getSchool().getId());
        subject_ts = subjectBean.findBySchoolId(
                loginMB.getUser().getSchool().getId());
        if (loginMB.getUser().getSchool().getSystemVariables().getTermType().getId() == 1) {
            exams = examBean.findBySchoolYearIdAndTermMonth(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    loginMB.getUser().getSchool().getSystemVariables().getTermMonth().getId());
            if (exams.size() >= 1) {
                item.setExam(exams.get(0));
            } else {
                item.setExam(new Exam());
            }
        }
        if (loginMB.getUser().getSchool().getSystemVariables().getTermType().getId() == 2) {
            exams = examBean.findBySchoolYearIdAndTermMonth(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getId());
            if (exams.size() >= 1) {
                item.setExam(exams.get(0));
            } else {
                item.setExam(new Exam());
            }
        }
    }

    public void prepareUpdate(Long id2) {
        item = examTableBean.find(id2);
    }

    public void create() {
        examTableBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        prepareCreate(
                item.getExam().getId(),
                item.getClass_().getId(),
                item.getSubject_t().getId(),
                item.getExamDate(),
                item.getBeginTime(),
                item.getExamTimeDuring());
        //  return "items?faces-redirect=true";
    }

    public String update() {
        examTableBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        examTableBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public ExamTable getItem() {
        return item;
    }

    public void setItem(ExamTable item) {
        this.item = item;
    }

    public List<ExamTable> getItems() {
        return items;
    }

    public void setItems(List<ExamTable> items) {
        this.items = items;
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

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

}
