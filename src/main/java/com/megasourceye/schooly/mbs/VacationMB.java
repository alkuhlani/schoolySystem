/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.AttendanceBean;
import com.megasourceye.schooly.ejbs.ClassBean;
import com.megasourceye.schooly.ejbs.ParentBean;
import com.megasourceye.schooly.ejbs.StudentSchoolBean;
import com.megasourceye.schooly.ejbs.SubjectBean;
import com.megasourceye.schooly.ejbs.SubjectTableBean;
import com.megasourceye.schooly.ejbs.TeacherAssignBean;
import com.megasourceye.schooly.ejbs.VacationBean;
import com.megasourceye.schooly.entities.Attendance;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.Parent;
import com.megasourceye.schooly.entities.StudentSchool;
import com.megasourceye.schooly.entities.SubjectTable;
import com.megasourceye.schooly.entities.Subject_t;
import com.megasourceye.schooly.entities.TeacherAssign;
import com.megasourceye.schooly.entities.Vacation;
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
@Named(value = "vacationMB")
@ViewScoped
public class VacationMB implements Serializable {

    /**
     * Creates a new instance of VacationMB
     */
    public VacationMB() {
    }

    private Vacation item;
    private Parent parent;
    private StudentSchool studentSchool;
    private Attendance attendance;
    private List<Vacation> items;
    private List<TeacherAssign> teacherAssigns;
    private List<SubjectTable> subjectTables;
    private List<StudentSchool> studentSchools;
    private List<Class_> class_s;
    private List<Subject_t> subject_ts;
    private List<Attendance> attendances;
    private long subjectId;
    private long classId;
    private long studentId;
    private long teacherAssignId;
    private Date startDate;
    private Date endDate;
    private Date today = new Date();

    @Inject
    private VacationBean vacationBaen;
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
    private ParentBean parentBean;
    @Inject
    private AttendanceBean attendanceBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        System.out.println("l");
        System.out.println("l");
        System.out.println("l");
        System.out.println("l");
//        items = schoolBean.findAll();
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = vacationBaen.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                items = vacationBaen.findBySchoolyYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = vacationBaen.findBySchoolyYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {

            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
                items = vacationBaen.findBySchoolyYearIdAndParentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getParent().getId());
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                items = vacationBaen.findBySchoolyYearIdAndParentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getStudent().getId());
            }
        }

    }

       public void prepareCreate() {
        today = new Date();
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                item = new Vacation();
                item.setStudentSchool(new StudentSchool());
                item.setParent(new Parent());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {

            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {

            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
item = new Vacation();
                item.setStudentSchool(new StudentSchool());
                item.setParent(new Parent());
                studentSchools=studentSchoolBean.findBySchoolYearAndParentId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(), loginMB.getUser().getParent().getId());
            }
        }
    }

    public void prepareUpdate(Long id2) {
        today = new Date();
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());

                item = vacationBaen.find(id2);
                classId = item.getStudentSchool().getClass_().getId();
                parent = parentBean.find(item.getParent().getId());
                studentId = item.getStudentSchool().getId();
                studentSchool = studentSchoolBean.find(item.getStudentSchool().getId());
                studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        classId);
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {

            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {

            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            }
        }
    }

    public String create() {

        item.setRequest_date(new Date());
        item.setState_(false);
        item.setActive_day(Short.valueOf("0"));
        vacationBaen.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        return "items?faces-redirect=true";
    }

    public String update() {
        vacationBaen.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        vacationBaen.delete(item);
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
            System.err.println(classId);
            System.err.println(subjectId);
            if (classId > 0 && studentId > 0) {
//                items = homeWorkBaen.findBySchoolYearAndClassAndSubjectIdAnd2Date(
//                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
//                        classId,
//                        subjectId,
//                        startDate,
//                        endDate);
            } else if (subjectId == -1) {
//                items = homeWorkBaen.findBySchoolYearAndClassAnd2Date(
//                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
//                        classId,
//                        startDate,
//                        endDate);
            }
        } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
            if (classId > 0 && subjectId > 0) {
//                items = homeWorkBaen.findBySchoolYearAndClassAndSubjectIdAnd2Date(
//                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
//                        classId,
//                        subjectId,
//                        startDate,
//                        endDate);
            } else if (subjectId == -1) {
//                items = homeWorkBaen.findBySchoolYearAndClassAnd2Date(
//                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
//                        classId,
//                        startDate,
//                        endDate);
            }
        } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
//            items = homeWorkBaen.findBySchoolYearAndTeahcerAssignIdAnd2Date(
//                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
//                    teacherAssignId,
//                    startDate,
//                    endDate);
        } else if (loginMB.getUser().getRole_().equals("PARENT")) {
            if (classId > 0 && subjectId > 0) {
//                items = homeWorkBaen.findBySchoolYearAndClassAndSubjectIdAnd2Date(
//                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
//                        classId,
//                        subjectId,
//                        startDate,
//                        endDate);
            } else if (subjectId == -1) {
//                items = homeWorkBaen.findBySchoolYearAndClassAnd2Date(
//                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
//                        classId,
//                        startDate,
//                        endDate);
            }
        } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
            System.out.println("subject = " + subjectId);
            if (subjectId > 0) {
//                items = homeWorkBaen.findBySchoolYearAndClassAndSubjectIdAnd2Date(
//                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
//                        studentSchoolBean.findBySchoolYearAndStudentId(
//                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
//                                loginMB.getUser().getStudent().getId()).get(0).getClass_().getId(),
//                        subjectId,
//                        startDate,
//                        endDate);
            } else if (subjectId == -1) {
//                items = homeWorkBaen.findBySchoolYearAndClassAnd2Date(
//                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
//                        studentSchoolBean.findBySchoolYearAndStudentId(
//                                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
//                                loginMB.getUser().getStudent().getId()).get(0).getClass_().getId(),
//                        startDate,
//                        endDate);
            }
        }

    }

    public void studentListner() {
        if (classId > 0) {
            studentSchools = studentSchoolBean.findBySchoolYearAndClassId(
                    loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                    classId);
        }
    }

    public void parentListner() {
        if (item.getStudentSchool().getId() > 0) {
            studentSchool = studentSchoolBean.find(item.getStudentSchool().getId());
            parent = parentBean.find(studentSchool.getStudent().getParent().getId());
            item.setParent(parent);
        }
    }

    public void approval(Long id) {
        System.out.println("ssss" + id);
        System.out.println("ssss" + id);
        System.out.println("ssss" + id);
        System.out.println("ssss" + id);
        item = vacationBaen.find(id);
        item.setState_(true);
        vacationBaen.update(item);
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = vacationBaen.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                items = vacationBaen.findBySchoolyYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = vacationBaen.findBySchoolyYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {

            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
                items = vacationBaen.findBySchoolyYearIdAndParentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getParent().getId());
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                items = vacationBaen.findBySchoolyYearIdAndParentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getStudent().getId());
            }
        }
        System.out.println("end");
        System.out.println("end");
        System.out.println("end");
        System.out.println("end");

    }

    public void cancleApproval(Long id) {
        item = vacationBaen.find(id);
        item.setState_(false);
        vacationBaen.update(item);
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = vacationBaen.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                items = vacationBaen.findBySchoolyYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = vacationBaen.findBySchoolyYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {

            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
                items = vacationBaen.findBySchoolyYearIdAndParentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getParent().getId());
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                items = vacationBaen.findBySchoolyYearIdAndParentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getStudent().getId());
            }
        }

    }

    public void lateApproval(Long id) {
        int u = 0;
        System.out.println("s");
        System.out.println("s");
        System.out.println("s");
        item = vacationBaen.find(id);
        attendances = attendanceBean.findByStudentSIdAnd2Date(item.getStudentSchool().getStudent().getId(),
                item.getStart_date(), item.getEnd_date());
        item.setState_(true);
        if (attendances.size() >= 1) {
            for (int i = 0; i < attendances.size(); i++) {
//                if (item.getActive_day() < item.getNumber()) {
                    attendance = new Attendance();
                    attendance = attendanceBean.find(attendances.get(i).getId());
                    if (attendance.getStatus() == '0') {
                        attendance.setStatus('2');
                        attendanceBean.update(attendance);
//                        int p = item.getActive_day() + 1;
//                        String f = String.valueOf(p);
//                        item.setActive_day(Short.valueOf(f));
//                        u++;
                    }
//                }
            }
            
            vacationBaen.update(item);
            System.out.println("ppppppp");
            System.out.println("ppppppp");
            System.out.println("ppppppp");
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "attendnce Time is " + attendances.size()));
        }
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "attendnce Time is " + u));
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = vacationBaen.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
                items = vacationBaen.findBySchoolyYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = vacationBaen.findBySchoolyYearId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {

            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
                items = vacationBaen.findBySchoolyYearIdAndParentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getParent().getId());
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
                items = vacationBaen.findBySchoolyYearIdAndParentId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getStudent().getId());
            }
        }
        System.out.println("[[---");
        System.out.println("[[---");
        System.out.println("99");
    }

    public Vacation getItem() {
        return item;
    }

    public void setItem(Vacation item) {
        this.item = item;
    }

    public List<Vacation> getItems() {
        return items;
    }

    public void setItems(List<Vacation> items) {
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

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
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

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    
}
