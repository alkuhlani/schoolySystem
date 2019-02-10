/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.WeekTopMarkBean;
import com.megasourceye.schooly.entities.School;
import com.megasourceye.schooly.entities.WeekTopMark;
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
@Named(value = "weekTopMarkMB")
@ViewScoped
public class WeekTopMarkMB implements Serializable{

    /**
     * Creates a new instance of WeekTopMarkMB
     */
    public WeekTopMarkMB() {
    }
    private WeekTopMark item;
    private List<WeekTopMark> items;
    
    @Inject
    private WeekTopMarkBean weekTopMarkBean;
    @Inject
    private LoginMB loginMB;
    
    public void prepareItems() {
//        items = schoolBean.findAll();
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                items = weekTopMarkBean.findAll();
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                items = weekTopMarkBean.findBySchoolId(loginMB.getUser().getSchool().getId());
            }
        }
        
    }
    
    public void prepareCreate() {
        item = new WeekTopMark();
        item.setSchool(new School());
    }
    
    public void prepareUpdate(Long id2) {
        item = weekTopMarkBean.find(id2);
    }
    
    public String create() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
                
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
                item.setSchool(loginMB.getUser().getSchool());
                
                weekTopMarkBean.create(item);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
            }
        }
          return "items?faces-redirect=true";
    }
    
    public String update() {
        weekTopMarkBean.update(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        return "items?faces-redirect=true";
        
    }
    
    public String delete() {
        weekTopMarkBean.delete(item);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Deleted"));
        return "items?faces-redirect=true";
    }
    
    public WeekTopMark getItem() {
        return item;
    }
    
    public void setItem(WeekTopMark item) {
        this.item = item;
    }
    
    public List<WeekTopMark> getItems() {
        return items;
    }
    
    public void setItems(List<WeekTopMark> items) {
        this.items = items;
    }
    
}
