/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.EncodeClass;
import com.megasourceye.schooly.ejbs.ParentBean;

import com.megasourceye.schooly.ejbs.StudentBean;
import com.megasourceye.schooly.ejbs.StudentParentBean;
import com.megasourceye.schooly.ejbs.UsersBean;
import com.megasourceye.schooly.entities.Parent;

import com.megasourceye.schooly.entities.Student;
import com.megasourceye.schooly.entities.StudentParent;
import com.megasourceye.schooly.entities.Users;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
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
@Named(value = "studentParentMB")
@ViewScoped
public class StudentParentMB implements Serializable {

    /**
     * Creates a new instance of StudentParentMB
     */
    public StudentParentMB() {
    }
    private StudentParent item;
    private List<StudentParent> items;
    private Student student;
    private List<Student> students;
    private Parent parent;
    private List<Parent> parents;
    
    private Users user;

    @Inject
    private StudentParentBean studentParentBean;
    @Inject
    private StudentBean studentBean;
    @Inject
    private ParentBean parentBean;
    
    @Inject
    private UsersBean usersBean;
    @Inject
    private LoginMB loginMB;

    public void prepareItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().endsWith("ADMINISTRATOR")) {
                items = studentParentBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = studentParentBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = studentParentBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {

            } else if (loginMB.getUser().getRole_().equals("PARENT")) {

            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {

            }
        }

    }

    public void prepareAllItems() {
//        items = schoolBean.findAll();
//        items = studentBean.findBySchoolId(loginMB.getUser().getSchool().getId());
    }

    public void prepareCreate() {
        item = new StudentParent();
        students = studentBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        parents = parentBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        
        item.setStudent(new Student());
        item.setParent(new Parent());
        
    }

    public void prepareUpdate(Long id2) {
        item = studentParentBean.find(id2);
    }

    public void create() {
        System.out.println("11");
        System.out.println("11");
        System.out.println("11");
        System.out.println("11");
        if (parent != null && student != null) {
            item.setParent(parent);
            item.setStudent(student);
            studentParentBean.create(item);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Record is Empty"));
        }

        //  return "items?faces-redirect=true";
    }

    public String update() {
        studentParentBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";

    }

    public String delete() {
        studentParentBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }

    public StudentParent getItem() {
        return item;
    }

    public void setItem(StudentParent item) {
        this.item = item;
    }

    public List<StudentParent> getItems() {
        return items;
    }

    public void setItems(List<StudentParent> items) {
        this.items = items;
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

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public StudentParentBean getStudentParentBean() {
        return studentParentBean;
    }

    public void setStudentParentBean(StudentParentBean studentParentBean) {
        this.studentParentBean = studentParentBean;
    }

   

}
