/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Locale;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.barcelona.view.GuestPreferences;

/**
 *
 * @author said
 */
@Named(value = "lnsMB")
@SessionScoped
public class LnsMB implements Serializable {

    /**
     * Creates a new instance of LnsMB
     */
    public LnsMB() {
    }
    private String language = "en";

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String actionArabic() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("ar"));
//        setLanguage("ar");
        FacesContext facesContext = FacesContext.getCurrentInstance();
//        System.out.println(facesContext.getViewRoot().getViewId());
//        GuestPreferences o = new GuestPreferences();
//        o.setOrientationRTL(true);
                return facesContext.getViewRoot().getViewId()+"?faces-redirect=true";

    }

    public String actionEnglish() {
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
//        setLanguage("en");
        FacesContext facesContext = FacesContext.getCurrentInstance();
//        System.out.println(facesContext.getViewRoot().getViewId());
//        GuestPreferences o = new GuestPreferences();
//        o.setOrientationRTL(false);
                return facesContext.getViewRoot().getViewId()+"?faces-redirect=true";
    }

}
