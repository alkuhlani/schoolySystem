/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.AttendanceBean;
import com.megasourceye.schooly.ejbs.ClassBean;
import com.megasourceye.schooly.ejbs.SchoolWeekBean;
import com.megasourceye.schooly.ejbs.StudentSchoolBean;
import com.megasourceye.schooly.ejbs.SubjectBean;
import com.megasourceye.schooly.ejbs.TeacherAssignBean;
import com.megasourceye.schooly.ejbs.TermWeekBean;
import com.megasourceye.schooly.ejbs.WeekMarkBean;
import com.megasourceye.schooly.entities.Attendance;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.SchoolWeek;
import com.megasourceye.schooly.entities.StudentSchool;
import com.megasourceye.schooly.entities.Subject_t;
import com.megasourceye.schooly.entities.TeacherAssign;
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
@Named(value = "weekMarkMB")
@ViewScoped
public class WeekMarkMB implements Serializable {

    /**
     * Creates a new instance of WeekMarkMB
     */
    public WeekMarkMB() {
    }

    private WeekMark item;
    private TeacherAssign teacherAssign;
    private List<WeekMark> items;
    private List<SchoolWeek> schoolWeeks;
    private List<TeacherAssign> teacherAssigns;
    private List<StudentSchool> studentSchools;
    private List<TermWeek> termWeeks;
    private List<Attendance> attendances;
    private List<Class_> class_s;
    private List<Subject_t> subject_ts;
    private TermWeek termWeek;
    private long classId;
    private long subjectId;

    @Inject
    private WeekMarkBean weekMarkBean;
    @Inject
    private SchoolWeekBean schoolWeekBean;
    @Inject
    private TeacherAssignBean teacherAssignBean;
    @Inject
    private StudentSchoolBean studentSchoolBean;
    @Inject
    private TermWeekBean termWeekBean;
    @Inject
    private AttendanceBean attendanceBean;
    @Inject
    private ClassBean classBean;
    @Inject
    private SubjectBean subjectBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
//                items = weekMarkBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = weekMarkBean.findBySchoolYearIdAndSchoolWeek(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek().getId());

            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = weekMarkBean.findBySchoolYearIdAndSchoolWeek(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                items = weekMarkBean.findByTeacherIdAndSchoolWeek(
                        loginMB.getUser().getTeacher().getId(),
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                System.out.println("1111");
                System.out.println("22222");
                System.out.println("33333");
                System.out.println("1111");
                System.out.println("1111");
                System.out.println("1111");
                System.out.println("1111");
                System.out.println("1111");
                items = weekMarkBean.findByStudentIdAndSchoolW(
                        loginMB.getUser().getStudent().getId(),
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek().getId());
            }
        }

    }

    public void prepare2Items() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
//                items = weekMarkBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = weekMarkBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = weekMarkBean.findBySchoolYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                items = weekMarkBean.findBySchoolYearAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                items = weekMarkBean.findBySchoolYearAndStudentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getStudent().getId());
            }
        }
    }

    public void prepareCreate() {
        termWeek = new TermWeek();
        if (loginMB.getUser().getSchool().getSystemVariables().getTermWeek() != null) {
            termWeek = loginMB.getUser().getSchool().getSystemVariables().getTermWeek();
            termWeeks = termWeekBean.findBySchoolYearId(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            schoolWeeks = schoolWeekBean.findByTermId(
                    loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getId());
        }
        item = new WeekMark();
        if (loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek() != null) {
            item.setSchoolWeek(loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek());
        }
        item.setQuize(0.0F);
        item.setHw(0.0F);
        item.setAttendance(0.0F);
        item.setBehavior(0.0F);
        if (loginMB.getUser().getRole_().equals("TEACHER")) {
            teacherAssigns = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    loginMB.getUser().getTeacher().getId());
        } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
            class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        }
//        studentSchools = studentSchoolBean.findBySchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());

        item.setStudentSchool(new StudentSchool());
        item.setTeacherAssign(new TeacherAssign());

    }

    public void prepareCreate(Long termWeekId, Long schoolWeekId, Long taecherAssignId, Long studentId) {
        item = new WeekMark();
        termWeek = new TermWeek();
        termWeeks = termWeekBean.findBySchoolYearId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        schoolWeeks = schoolWeekBean.findByTermId(
                termWeekId);
        termWeek = new TermWeek(termWeekId);
        
        item.setSchoolWeek(new SchoolWeek(schoolWeekId));
        item.setStudentSchool(new StudentSchool(studentId));
        item.setTeacherAssign(new TeacherAssign(taecherAssignId));

        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                schoolWeeks = schoolWeekBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                schoolWeeks = schoolWeekBean.findByTermId(
                        termWeekId);
                teacherAssign = teacherAssignBean.find(taecherAssignId);
                classId = teacherAssign.getClass_().getId();
                subjectId = teacherAssign.getSubject_t().getId();
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                if (loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek() != null) {
                    schoolWeeks = schoolWeekBean.findByTermIdandTermType(loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getId(), loginMB.getUser().getSchool().getSystemVariables().getTermHalf());
                } else {
                    schoolWeeks = schoolWeekBean.findByTermId(
                            termWeekId);
                }
                teacherAssigns = teacherAssignBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                if (loginMB.getUser().getSchool().getSystemVariables().getTermHalf() != null) {
                    schoolWeeks = schoolWeekBean.findByTermIdandTermType(loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getId(), loginMB.getUser().getSchool().getSystemVariables().getTermHalf());
                } else {
                    schoolWeeks = schoolWeekBean.findByTermId(
                            termWeekId);
                }
            }
        }
    }

    public void studentListener() {
        System.out.println("1111");
        System.out.println("1111");
        System.out.println("" + studentSchoolBean.findBySchoolYearAndClassId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                teacherAssignBean.find(item.getTeacherAssign().getId()).getClass_().getId()).size());
        studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                teacherAssignBean.find(item.getTeacherAssign().getId()).getClass_().getId());

    }

    public void calcAttendance() {
        int ab = 0;
        int at = 0;
        int m = 0;
        int to = 2;
        attendances = attendanceBean.findByStudentSIdAnd2Date(item.getStudentSchool().getId(),
                item.getSchoolWeek().getFirstDate(),
                item.getSchoolWeek().getEndDate());
        int g = attendances.size();
        System.out.println(g);
        for (int i = 0; i < g; i++) {

            if (attendances.get(i).getStatus() == '1') {
                at++;
            } else if (attendances.get(i).getStatus() == '0') {
                ab++;
            } else if (attendances.get(i).getStatus() == '2') {
                m++;
            }

        }
        if (m >= 3) {
            to--;
        }
        for (int j = 0; j < ab; j++) {
            to--;
        }
        item.setAttendance(to * 1.0F);
    }

    public void weekListener() {
        System.out.println("3222");
        schoolWeeks = schoolWeekBean.findByTermId(termWeek.getId());
    }

    public void prepareUpdate(Long id2) {

        termWeeks = termWeekBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        item = weekMarkBean.find(id2);
        termWeeks = termWeekBean.findBySchoolYearId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
        schoolWeeks = schoolWeekBean.findAll();
        classId = item.getStudentSchool().getClass_().getId();
        subjectId = item.getTeacherAssign().getSubject_t().getId();

        teacherAssigns = teacherAssignBean.findBySchoolYearIdAndClassIdAndSubjectId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                classId,
                subjectId);
        termWeek = new TermWeek(item.getSchoolWeek().getTermWeek().getId());
        if (loginMB.getUser().getRole_().equals("TEACHER")) {
            teacherAssigns = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    loginMB.getUser().getTeacher().getId());
        } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
            class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            subject_ts = subjectBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        }
//        studentSchools = studentSchoolBean.findBySchoolYear(
//loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
    }

    public void create() {
        System.out.println("getStudentSchool" + item.getStudentSchool().getId());
        System.out.println("getTeacherAssign" + item.getTeacherAssign().getId());
        System.out.println("getSchoolWeek" + loginMB.getUser().getSchool().getSystemVariables().getSchoolWeek().getId());

        System.out.println(weekMarkBean.findByStudentSIdAndTeacherAssignAndSchoolW(
                item.getStudentSchool().getId(),
                item.getTeacherAssign().getId(),
                item.getSchoolWeek().getId()).size());
        System.out.println("11111");
        if (weekMarkBean.findByStudentSIdAndTeacherAssignAndSchoolW(
                item.getStudentSchool().getId(),
                item.getTeacherAssign().getId(),
                item.getSchoolWeek().getId()).size() >= 1) {
            System.out.println("1.1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "is Founded"));

        } else {
            System.out.println("2");

            System.out.println("3");
            weekMarkBean.create(item);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
            prepareCreate(termWeek.getId(),  item.getSchoolWeek().getId(), item.getTeacherAssign().getId(),item.getStudentSchool().getId());
        }

        //  return "items?faces-redirect=true";
    }

    public String update() {
        System.out.println(weekMarkBean.findByStudentSIdAndTeacherAssignAndSchoolW(
                item.getStudentSchool().getId(),
                item.getTeacherAssign().getId(),
                item.getSchoolWeek().getId()).size());
        System.out.println("11111");
        if (weekMarkBean.findByStudentSIdAndTeacherAssignAndSchoolW(
                item.getStudentSchool().getId(),
                item.getTeacherAssign().getId(),
                item.getSchoolWeek().getId()).size() >= 2) {
            System.out.println("1.1");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Info", "is Founded"));
            return "items?faces-redirect=true";

        } else {
            weekMarkBean.update(item);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
            return "items?faces-redirect=true";
        }

    }

    public String delete() {
        weekMarkBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public void subjectTableListner() {
        if (classId > 0 && subjectId > 0) {
            teacherAssigns = teacherAssignBean.findBySchoolYearIdAndClassIdAndSubjectId(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    classId,
                    subjectId);
            item.setTeacherAssign(teacherAssigns.get(0));
            studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    classId);
        }

    }

    public WeekMark getItem() {
        return item;
    }

    public void setItem(WeekMark item) {
        this.item = item;
    }

    public List<WeekMark> getItems() {
        return items;
    }

    public void setItems(List<WeekMark> items) {
        this.items = items;
    }

    public List<SchoolWeek> getSchoolWeeks() {
        return schoolWeeks;
    }

    public void setSchoolWeeks(List<SchoolWeek> schoolWeeks) {
        this.schoolWeeks = schoolWeeks;
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

    public List<TermWeek> getTermWeeks() {
        return termWeeks;
    }

    public void setTermWeeks(List<TermWeek> termWeeks) {
        this.termWeeks = termWeeks;
    }

    public TermWeek getTermWeek() {
        return termWeek;
    }

    public void setTermWeek(TermWeek termWeek) {
        this.termWeek = termWeek;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
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

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

}
