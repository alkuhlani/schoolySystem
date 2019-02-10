/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs.utls;

import com.megasourceye.schooly.mbs.LoginMB;
import javax.inject.Inject;

/**
 *
 * @author said
 */
public class Methods {

    @Inject
    private LoginMB loginMB;

    public void prepare() {
        if (loginMB.getUser() != null) {
            if (loginMB.getUser().getRole_().equals("ADMINISTRATOR")) {
            } else if (loginMB.getUser().getRole_().equals("ADMIN")) {
            } else if (loginMB.getUser().getRole_().equals("TEACHER")) {
            } else if (loginMB.getUser().getRole_().equals("EMPLOYEE")) {
            } else if (loginMB.getUser().getRole_().equals("STUDENT")) {
            } else if (loginMB.getUser().getRole_().equals("PARENT")) {
            }
        }
    }
}
