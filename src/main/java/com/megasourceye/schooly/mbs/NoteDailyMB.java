/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.ClassBean;
import com.megasourceye.schooly.ejbs.NoteDailyBean;
import com.megasourceye.schooly.ejbs.StudentSchoolBean;
import com.megasourceye.schooly.ejbs.SubjectBean;
import com.megasourceye.schooly.ejbs.SubjectTableBean;
import com.megasourceye.schooly.ejbs.TeacherAssignBean;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.NoteDaily;
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
@Named(value = "noteDailyMB")
@ViewScoped
public class NoteDailyMB implements Serializable {

    /**
     * Creates a new instance of NoteDailyMB
     */
    public NoteDailyMB() {
    }
    private NoteDaily item;
    private SubjectTable subjectTable;
    private StudentSchool studentSchool;
    private List<NoteDaily> items;
    private List<TeacherAssign> teacherAssigns;
    private List<SubjectTable> subjectTables;
    private List<StudentSchool> studentSchools;
    private List<Class_> class_s;
    private List<Subject_t> subject_ts;
    private long classId;
    private long studentId;
    private long subjectId;
    private Date startDate;
    private Date endDate;

    @Inject
    private NoteDailyBean noteDailyBean;
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

            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = noteDailyBean.findBySchoolYearAndDate(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        new java.util.Date());
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = noteDailyBean.findBySchoolYearAndDate(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        new java.util.Date());
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                items = noteDailyBean.findBySchoolYearAndTeahcerIdAndDate(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId(),
                        new java.util.Date());
                teacherAssigns = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
                items = noteDailyBean.findBySchoolYearIdAndParentIdAndDate(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getParent().getId(),
                        new java.util.Date());
                studentSchools = studentSchoolBean.findBySchoolYearAndParentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getParent().getId());
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                items = noteDailyBean.findBySchoolYearIdAndStudentIdAndDate(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getStudent().getId(),
                        new java.util.Date());
                teacherAssigns = teacherAssignBean.findBySchoolYearIdAndClassId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        studentSchoolBean.findBySchoolYearAndStudentId(
                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                                loginMB.getUser().getStudent().getId()).get(0).getClass_().getId());
            }
        }
    }

    public void prepareCreate() {
        System.out.println("1");
        item = new NoteDaily();
        item.setForParent(true);
        item.setForStudent(true);
        System.out.println("2");
        item.setSubjectTable(new SubjectTable());
        item.setStudentSchool(new StudentSchool());
//        item.setEntryDate(new java.util.Date());
        item.setWriteDate(new java.util.Date());
        System.out.println("3");
        if (loginMB.getUser() != null) {
            System.out.println("4");
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                System.out.println("5");
                items = noteDailyBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                System.out.println("6");
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());

            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                System.out.println("7");
                subjectTables = subjectTableBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
            }
        }
    }

    public void prepareUpdate(Long id2, Long id3) {
        item = noteDailyBean.find(id2);
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = noteDailyBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                subjectTables = subjectTableBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
                studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        id3);
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                subjectTables = subjectTableBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
                studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        id3);
            }
        }
    }

    public void create() {

        if (item.getStudentSchool().getId() == -1) {
            item.setIsSpec(false);
            item.setStudentSchool(null);
            noteDailyBean.create(item);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        } else {
            item.setIsSpec(true);
            if (noteDailyBean.findBySubjectTAndStudentSIdEntryDate(
                    item.getSubjectTable().getId(),
                    item.getStudentSchool().getId(),
                    item.getWriteDate()).size() >= 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Is founded"));
            } else {
                noteDailyBean.create(item);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
            }
        }
        System.out.println("-------" + noteDailyBean.findBySubjectTAndEntryDate(
                item.getSubjectTable().getId(),
                item.getWriteDate()));

    }

    public String update() {
        noteDailyBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        noteDailyBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public void classListner() {
        System.out.println(item.getWriteDate());
        System.out.println(item.getStudentSchool());
        System.out.println("1");
        System.out.println("1");
        System.out.println("1");
        System.out.println("1");
        System.out.println("1");
        System.out.println(item.getSubjectTable());
        System.out.println(item.getSubjectTable());
        subjectTable = subjectTableBean.find(item.getSubjectTable().getId());
        System.out.println(subjectTable.getTeacherAssign());
//        System.out.println(item.getSubjectTable().getTeacherAssign());
//        System.out.println(item.getSubjectTable().getTeacherAssign().getTeacher().getId());
//        System.out.println(item.getSubjectTable().getTeacherAssign().getClass_());
        studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                subjectTable.getTeacherAssign().getClass_().getId());
//        studentSchools=studentSchoolBean.findAll
    }

    public void studentListner() {
        if (loginMB.getUser().getRole_().equals("ADMIN")) {
            studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    classId);
        } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
            studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    classId);
        }

        if (loginMB.getUser().getRole_().equals("TEACHER")) {
            studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    classId);
        }
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
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(classId);
        System.out.println(studentId);
        if (loginMB.getUser().getRole_().equals("TEACHER")) {
            if (studentId == -1) {
                items = noteDailyBean.findBySchoolYearAndClassAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId,
                        startDate,
                        endDate);
            } else {
                items = noteDailyBean.findBySchoolYearIdAndStudentIdAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        studentId,
                        startDate,
                        endDate);
            }
        } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
            if (studentId == -1) {
                items = noteDailyBean.findBySchoolYearAndClassAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId,
                        startDate,
                        endDate);
            } else {
                items = noteDailyBean.findBySchoolYearIdAndStudentIdAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        studentId,
                        startDate,
                        endDate);
            }
        } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
            if (studentId == -1) {
                items = noteDailyBean.findBySchoolYearAndClassAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId,
                        startDate,
                        endDate);
            } else {
                items = noteDailyBean.findBySchoolYearIdAndStudentIdAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        studentId,
                        startDate,
                        endDate);
            }
        } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
            System.out.println("1111");
            System.out.println("1111");
            System.out.println("1111");
            System.out.println("1111");
            if (classId == -1) {
                System.out.println("22222");
                items = noteDailyBean.findBySchoolYearIdAndStudentIdAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getStudent().getId(),
                        startDate,
                        endDate);
                System.out.println("22222");
            } else {
                System.out.println("33333");
                items = noteDailyBean.findBySchoolYearIdAndSubjectIdAndStudentIdAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId,
                        loginMB.getUser().getStudent().getId(),
                        startDate,
                        endDate);
                System.out.println(classId);
                System.out.println("33333");
            }
        } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            if (studentId == -1) {
                items = noteDailyBean.findBySchoolYearIdAndParentIdAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getParent().getId(),
                        startDate,
                        endDate);
            } else {
                items = noteDailyBean.findBySchoolYearIdAndStudentIdAnd2Date(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        studentId,
                        startDate,
                        endDate);
            }

        }
    }

    public void calenderLisner() {
        System.out.println(startDate);
        System.out.println(startDate);
        System.out.println(startDate);
        System.out.println(startDate);
    }

    public NoteDaily getItem() {
        return item;
    }

    public void setItem(NoteDaily item) {
        this.item = item;
    }

    public List<NoteDaily> getItems() {
        return items;
    }

    public void setItems(List<NoteDaily> items) {
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

    public SubjectTable getSubjectTable() {
        return subjectTable;
    }

    public void setSubjectTable(SubjectTable subjectTable) {
        this.subjectTable = subjectTable;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public StudentSchool getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(StudentSchool studentSchool) {
        this.studentSchool = studentSchool;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
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

}
