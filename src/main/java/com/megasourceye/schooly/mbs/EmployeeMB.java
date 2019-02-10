/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.DepartmentTBean;
import com.megasourceye.schooly.ejbs.EmployeeBean;
import com.megasourceye.schooly.ejbs.EncodeClass;
import com.megasourceye.schooly.ejbs.UsersBean;
import com.megasourceye.schooly.entities.DepartmentType;
import com.megasourceye.schooly.entities.Employee;
import com.megasourceye.schooly.entities.Users;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author said
 */
@Named(value = "employeeMB")
@ViewScoped
public class EmployeeMB implements Serializable {

    /**
     * Creates a new instance of EmployeeMB
     */
    public EmployeeMB() {
    }
    
    private Employee item;
    private List<Employee> items;
    private Users user;
    
    @Inject
    private EmployeeBean employeeBean;
    @Inject
    private UsersBean usersBean;
    @Inject
    private LoginMB loginMB;
    
    public void prepareItems() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = employeeBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = employeeBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
                items = employeeBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            }
        }
        
    }
    
    public void prepareCreate() {
        item = new Employee();
    }
    
    public void prepareUpdate(Long id2) {
        item = employeeBean.find(id2);
    }
    
    public void create() {
        items = employeeBean.findBySchoolId(loginMB.getUser().getSchool().getId());
        int s = items.size();
        if (s >= 1) {
            Long i = items.get(s - 1).getEmployeeID();
            item.setEmployeeID(i + 1);
        } else {
//            long generatedLong = new Long("11111111111") + (long) (Math.random() * (new Long("100000000000") - new Long("11111111111")));
            int generatedInt = new Integer("11111") + (int) (Math.random() * (new Integer("10000") - new Integer("11111")));
            Date datei = new Date();
            String d=String.valueOf(datei.getYear())
                    +String.valueOf(datei.getMonth())
                    +String.valueOf(datei.getDay()
                    +String.valueOf(generatedInt));
            item.setEmployeeID(new Long(d));
        }
//        java.util.Date dt = new java.util.Date();
        item.setRegisterDate(new java.util.Date());
        item.setSchool(loginMB.getUser().getSchool());
        employeeBean.create(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        //  return "items?faces-redirect=true";
    }
    
    public String update() {
        employeeBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";
    }
    
    public String delete() {
        employeeBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }
    
    public String createUser(Long id) throws NoSuchAlgorithmException {
        user = new Users();
        item = employeeBean.find(id);
//        user.setStudent(item);
        user.setName_ar(item.getName_ar());
        user.setName_en(item.getName_en());
        if(usersBean.findByUsername(item.getEmployeeID().toString()).size()>=1){
            int generatedInt = new Integer("11111") + (int) (Math.random() * (new Integer("10000") - new Integer("11111")));
            Date datei = new Date();
            String d=String.valueOf(datei.getYear())
                    +String.valueOf(datei.getMonth())
                    +String.valueOf(datei.getDay()
                    +String.valueOf(generatedInt));
            user.setUsername(d);
        }else
        {
            user.setUsername(item.getEmployeeID().toString());
        }
        user.setPassword(EncodeClass.encode(item.getPhone1()));
        user.setRole_("EMPLOYEE");
        user.setSchool(item.getSchool());
        usersBean.create(user);
        item.setUser(new Users());
        item.setUser(user);
        employeeBean.update(item);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", id.toString()));
        return "items?faces-redirect=true";
    }
    
    public Employee getItem() {
        return item;
    }
    
    public void setItem(Employee item) {
        this.item = item;
    }
    
    public List<Employee> getItems() {
        return items;
    }
    
    public void setItems(List<Employee> items) {
        this.items = items;
    }
    
    public Users getUser() {
        return user;
    }
    
    public void setUser(Users user) {
        this.user = user;
    }
    
}
