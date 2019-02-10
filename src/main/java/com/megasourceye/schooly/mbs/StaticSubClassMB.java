/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.StaticLevelBean;
import com.megasourceye.schooly.ejbs.StaticSubClassBean;
import com.megasourceye.schooly.entities.StaticLevel;
import com.megasourceye.schooly.entities.StaticSubClass;
import com.megasourceye.schooly.mbs.utls.PrimeFaces;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author said
 */
@Named(value = "staticSubClassMB")
@ViewScoped
public class StaticSubClassMB implements Serializable{

    /**
     * Creates a new instance of StaticSubClassMB
     */
    public StaticSubClassMB() {
    }
    private StaticSubClass item;
    private List<StaticSubClass> items;
    private boolean isUpdate;
    private boolean isTrue;

    @Inject
    private StaticSubClassBean bean;
    @Inject
    private LoginMB loginMB;

    @PostConstruct
    public void init() {
        if (loginMB.getUser() != null) {
            if ("ADMINISTRATOR".equals(loginMB.getUser().getRole_())) {
                items = bean.findAll();
            }
        }
    }
    public void prepareItems() {
//        items = schoolBean.findAll();
        if (loginMB.getUser() != null) {
            if ("ADMINISTRATOR".equals(loginMB.getUser().getRole_())) {
                items = bean.findAll();
            }
        }
    }

    public void prepareItem() {
//        items = schoolBean.findAll();
        item = new StaticSubClass();
    }

    public void prepareCreate() {
        RequestContext rctx = RequestContext.getCurrentInstance();
        isUpdate = false;
        rctx.update("form2");
        item = new StaticSubClass();
        rctx.execute("PF('dlg').show();");
    }

    public void prepareUpdate(Long id2) {
        RequestContext rctx = RequestContext.getCurrentInstance();
        isUpdate = true;
        item = bean.find(id2);
        rctx.update("form2");
        rctx.execute("PF('dlg').show();");
    }

    public void create() {
        bean.create(item);
        isTrue = true;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Saved"));
        PrimeFaces.current().ajax().addCallbackParam("isTrue", isTrue);
    }

    public void update() {
        bean.update(item);
        isTrue = true;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        PrimeFaces.current().ajax().addCallbackParam("isTrue", isTrue);

    }

    public void delete() {
        bean.delete(item);
        isTrue = true;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Successfuly Updated"));
        PrimeFaces.current().ajax().addCallbackParam("isTrue", isTrue);
    }

    public StaticSubClass getItem() {
        return item;
    }

    public void setItem(StaticSubClass item) {
        this.item = item;
    }

    public List<StaticSubClass> getItems() {
        return items;
    }

    public void setItems(List<StaticSubClass> items) {
        this.items = items;
    }

    

    public boolean isIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public boolean isIsTrue() {
        return isTrue;
    }

    public void setIsTrue(boolean isTrue) {
        this.isTrue = isTrue;
    }

}
