/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author said
 */
@Named(value = "checkRoleMB")
@Dependent
public class checkRoleMB implements Serializable{

    /**
     * Creates a new instance of checkRoleMB
     */
    public checkRoleMB() {
    }
    public boolean hasRole(){
        
        return true;
    }
    public String doCheck(){
        
        return "";
    }
    
}
