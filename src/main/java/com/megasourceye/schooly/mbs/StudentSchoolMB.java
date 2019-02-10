/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.ClassBean;
import com.megasourceye.schooly.ejbs.ParentBean;
import com.megasourceye.schooly.ejbs.StudentBean;
import com.megasourceye.schooly.ejbs.StudentSchoolBean;
import com.megasourceye.schooly.ejbs.TeacherAssignBean;
import com.megasourceye.schooly.entities.Class_;
import com.megasourceye.schooly.entities.Parent;
import com.megasourceye.schooly.entities.Student;
import com.megasourceye.schooly.entities.StudentSchool;
import com.megasourceye.schooly.entities.TeacherAssign;
import com.megasourceye.schooly.mbs.utls.JasperManager;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author said
 */
@Named(value = "studentSchoolMB")
@ViewScoped
public class StudentSchoolMB implements Serializable {

    /**
     * Creates a new instance of StudentSchoolMB
     */
    public StudentSchoolMB() {
    }

    private StudentSchool item;
    private Student student;
    private Parent parent;
    private List<StudentSchool> items;
    private List<StudentSchool> selectedStudents;
    private List<Student> students;
    private List<Class_> class_s;
    private List<TeacherAssign> TeacherAssign;
    private TeacherAssign TeacherAssign_;
    private boolean newStudent = false;
    private boolean newParent = false;
    private boolean deaActive = false;
    private long termWeekId;
    private String month;
    private String year;
    private String className;
    private Long classId;
    private String teacher;

    @Inject
    private StudentSchoolBean studentSchoolBean;
    @Inject
    private StudentBean studentBean;
    @Inject
    private ClassBean classBean;
    @Inject
    private TeacherAssignBean teacherAssignBean;
    @Inject
    private ParentBean parentBean;
    @Inject
    private LoginMB loginMB;
    @Inject
    private JasperManager jasperManager;

    public void prepareItems() {

        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = studentSchoolBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = studentSchoolBean.findBySchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = studentSchoolBean.findBySchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
//                items = studentSchoolBean.findBySchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
                TeacherAssign = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
                TeacherAssign_ = new TeacherAssign();
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {

            }
        }
    }

    public void prepareItemsTermWeek(long Id) {
        System.out.println("5");
        System.out.println("5");
        System.out.println("5");
        System.out.println("5");

        System.out.println(Id);
        System.out.println(Id);
        System.out.println(Id);
        System.out.println("5");
        termWeekId = Id;
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = studentSchoolBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = studentSchoolBean.findBySchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = studentSchoolBean.findBySchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
//                items = studentSchoolBean.findBySchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
                TeacherAssign = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
                TeacherAssign_ = new TeacherAssign();
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {

            }
        }
    }

    public void prepareItems(Long id) {

        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = studentSchoolBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = studentSchoolBean.findBySchoolYearAndClassId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(), id);
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = studentSchoolBean.findBySchoolYearAndClassId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(), id);
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
                TeacherAssign_ = new TeacherAssign();
                items = studentSchoolBean.findBySchoolYearAndClassId(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(), id);
            }
        }
    }

    public void prepareCreate(Long studentId) {
        item = new StudentSchool();
        item.setStudent(new Student(studentId));
        item.setClass_(new Class_());
        class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        newParent = false;
        parent = null;
    }

    public void prepareCreateNewStudent() {
        item = new StudentSchool();
        item.setClass_(new Class_());
        student = new Student();
        class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        newParent = false;

    }

    public void prepareCreateNewStudent(long classId) {
        item = new StudentSchool();
        item.setClass_(new Class_(classId));
        student = new Student();
        class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        newParent = false;

    }

    public void prepareCreateNewParent() {
        newParent = true;

        student.setParent(new Parent());
        parent = new Parent();
//        newParent = false;
        parent.setSchool(loginMB.getUser().getSchool());

    }

    public void prepareCreateParent() {
        parent = new Parent();
    }

    public void createParent() {
        System.out.println("before save parent");
        int ss = parentBean.findBySchoolId(loginMB.getUser().getSchool().getId()).size();

        if (ss >= 1) {
            Long v = parentBean.findBySchoolId(loginMB.getUser().getSchool().getId())
                    .get(ss - 1).getParentID();
            parent.setParentID(v + 1);
        } else {
            String ParentID = loginMB.getUser().getSchool().getId().toString();
            ParentID = ParentID.concat("3").concat("10000");
            parent.setParentID(new Long(ParentID));
        }
        parent.setSchool(loginMB.getUser().getSchool());
        parentBean.create(parent);
        newParent = true;
        student = new Student();
        item = new StudentSchool();
        item.setClass_(new Class_());
        student.setParent(parent);
        student.setSchool(loginMB.getUser().getSchool());
        class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        items = studentSchoolBean.findBySchoolYearAndParentId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                parent.getId());
        System.out.println("after save parent");
    }

    public void prepareCreateStudentForParent() {
        student = new Student();
        item = new StudentSchool();
    }

    public void createStudentForParent() {
        students = studentBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        int s = students.size();
        if (s >= 1) {
            Long i = students.get(s - 1).getStudentID();
            student.setStudentID(i + 1);
        } else {
            String StudentID = loginMB.getUser().getSchool().getId().toString();
            StudentID = StudentID.concat("6").concat("10000");
            student.setStudentID(new Long(StudentID));
//            student.setStudentID(loginMB.getUser().getSchool().getSystemVariables().getFirstID());
        }
        student.setRegisterDate(new java.util.Date());
        System.out.println("before save student");
        student.setSchool(loginMB.getUser().getSchool());
        item.setSchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear());
        student.setParent(parent);

        studentBean.create(student);
        item.setStudent(student);
        studentSchoolBean.create(item);
        items = studentSchoolBean.findBySchoolYearAndParentId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                parent.getId());
        newStudent = true;
        student = new Student();

        item = new StudentSchool();
        item.setStudent(student);
        item.setClass_(new Class_());
        class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        System.out.println("after save student");
    }

    public void prepareUpdate(Long id2) {
        item = studentSchoolBean.find(id2);
        if (item.getClass_() == null) {
            item.setClass_(new Class_());
        }

        class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
    }

    public String create() {
        item.setSchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear());
        if (studentSchoolBean.findBySchoolYearAndStudentId(
                item.getSchoolYear().getId(), item.getStudent().getId()).size() >= 1) {
            return "items?faces-redirect=true";
        } else {

            studentSchoolBean.create(item);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
            return "items?faces-redirect=true";
        }

    }

    public void classListner() {

        items = studentSchoolBean.findBySchoolYearAndClassId(
                loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                teacherAssignBean.find(TeacherAssign_.getId()).getClass_().getId());
    }

    public void createNewStudent() {
        students = studentBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        int s = students.size();
        if (s >= 1) {
            Long i = students.get(s - 1).getStudentID();
            student.setStudentID(i + 1);
        } else {
            String StudentID = loginMB.getUser().getSchool().getId().toString();
            StudentID = StudentID.concat("6").concat("10000");
            student.setStudentID(new Long(StudentID));
//            student.setStudentID(loginMB.getUser().getSchool().getSystemVariables().getFirstID());
        }
        student.setRegisterDate(new java.util.Date());
        student.setSchool(loginMB.getUser().getSchool());
        if (newParent) {
            int ss = parentBean.findBySchoolId(loginMB.getUser().getSchool().getId()).size();

            if (ss >= 1) {
                Long v = parentBean.findBySchoolId(loginMB.getUser().getSchool().getId())
                        .get(ss - 1).getParentID();
                parent.setParentID(v + 1);
            } else {
                /*int generatedInt = new Integer("11111") + (int) (Math.random() * (new Integer("10000") - new Integer("11111")));
                Date datei = new Date();
                String p = String.valueOf(datei.getYear())
                        + String.valueOf(datei.getMonth())
                        + String.valueOf(datei.getDay()
                                + String.valueOf(generatedInt));

                parent.setParentID(new Long(p));*/
                String ParentID = loginMB.getUser().getSchool().getId().toString();
                ParentID = ParentID.concat("3").concat("10000");
                parent.setParentID(new Long(ParentID));
            }
            parent.setSchool(loginMB.getUser().getSchool());
            parentBean.create(parent);
            student.setParent(parent);
        }
        studentBean.create(student);
        item.setStudent(student);
        item.setSchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear());
        studentSchoolBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        prepareCreateNewStudent(item.getClass_().getId());
    }

    public String update() {
        studentSchoolBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";
    }

    public String delete() {
        studentSchoolBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public void newStudent2() {
//        if(newStudent==true)
        newStudent = true;
//        else
//            newStudent=false;
        String summary = newStudent ? "Checked" : "Unchecked";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public void print(
            long studentId,
            String studentName,
            String className,
            String schoolYear) throws JRException, IOException {

        Map<String, Object> m = new HashMap();
        m.put("studentId", studentId);
        m.put("termWeekId", this.termWeekId);
        m.put("studentName", studentName);
        m.put("className", className);
        m.put("schoolYear", schoolYear);
        jasperManager.exportToPDF("mteB.jrxml", null, null, m, false);
    }

    public void print2(
            long studentId,
            String studentName,
            String className,
            String schoolYear) throws JRException, IOException {

        Map<String, Object> m = new HashMap();
        m.put("studentId", studentId);
        m.put("termWeekId", loginMB.getUser().getSchool().getSystemVariables().getTermWeek().getId());
        m.put("studentName", studentName);
        m.put("className", className);
        m.put("schoolYear", schoolYear);
        jasperManager.exportToPDF("mteB.jrxml", null, null, m, false);
    }

    public void printAttendanceSheet(
            long classId,
            String className,
            String month,
            String year,
            String teacher) throws JRException, IOException {
        System.out.println("kjshdfn");
        System.out.println("kjshdfn");
        System.out.println("kjshdfn");
        System.out.println("kjshdfn");
        System.out.println("kjshdfn");
        Map<String, Object> m = new HashMap();
        m.put("classId", classId);
        m.put("grade", className);
        m.put("year", loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId().toString());
        m.put("month", month);
        m.put("teacher", teacher);
        jasperManager.exportToPDF("R2.jrxml", null, null, m, false);
        System.out.println("pppppppppppp");
    }

    public void printAttendanceSheet() throws JRException, IOException {
        System.out.println("kjshdfn");
        System.out.println("kjshdfn");
        System.out.println("kjshdfn");
        System.out.println("kjshdfn");
        System.out.println("kjshdfn");
        className = classBean.find(classId).getName_en();
        Map<String, Object> m = new HashMap();
        m.put("classId", classId);
        m.put("grade", className);
        m.put("year", loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getName_en());
        m.put("month", month);
        m.put("teacher", teacher);
        jasperManager.exportToPDF("R2.jrxml", null, null, m, false);
        System.out.println("pppppppppppp");
    }

    public void prepareAttendanceSheetReport() {
        System.out.println(";;;;;;;;;;");
        class_s = classBean.findBySchoolId(loginMB.getUser().getSchool().getId());
    }

    public void deaActiveStudents() {
        if (selectedStudents.size() >= 1) {
            for (int i = 0; i < selectedStudents.size(); i++) {
                item = studentSchoolBean.find(selectedStudents.get(i).getId());
                item.setDeaActive(true);
                studentSchoolBean.update(item);
            }
        }
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = studentSchoolBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = studentSchoolBean.findBySchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = studentSchoolBean.findBySchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
//                items = studentSchoolBean.findBySchoolYear(loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId());
                TeacherAssign = teacherAssignBean.findBySchoolYearIdAndTeacherId(
                        loginMB.getUser().getSchool().getSystemVariables().getSchoolYear().getId(),
                        loginMB.getUser().getTeacher().getId());
                TeacherAssign_ = new TeacherAssign();
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {

            }
        }
    }

    public void deaActiveTurn() {
        deaActive = true;
    }

    public StudentSchool getItem() {
        return item;
    }

    public void setItem(StudentSchool item) {
        this.item = item;
    }

    public List<StudentSchool> getItems() {

        return items;
    }

    public void setItems(List<StudentSchool> items) {
        this.items = items;
    }

    public boolean isNewStudent() {
        return newStudent;
    }

    public void setNewStudent(boolean newStudent) {
        this.newStudent = newStudent;
    }

    public List<Class_> getClass_s() {
        return class_s;
    }

    public void setClass_s(List<Class_> class_s) {
        this.class_s = class_s;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<TeacherAssign> getTeacherAssign() {
        return TeacherAssign;
    }

    public void setTeacherAssign(List<TeacherAssign> TeacherAssign) {
        this.TeacherAssign = TeacherAssign;
    }

    public TeacherAssign getTeacherAssign_() {
        return TeacherAssign_;
    }

    public void setTeacherAssign_(TeacherAssign TeacherAssign_) {
        this.TeacherAssign_ = TeacherAssign_;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public boolean isNewParent() {
        return newParent;
    }

    public void setNewParent(boolean newParent) {
        this.newParent = newParent;
    }

    public long getTermWeekId() {
        return termWeekId;
    }

    public void setTermWeekId(long termWeekId) {
        this.termWeekId = termWeekId;
    }

    public List<StudentSchool> getSelectedStudents() {
        return selectedStudents;
    }

    public void setSelectedStudents(List<StudentSchool> selectedStudents) {
        this.selectedStudents = selectedStudents;
    }

    public boolean isDeaActive() {
        return deaActive;
    }

    public void setDeaActive(boolean deaActive) {
        this.deaActive = deaActive;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

}
