/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megasourceye.schooly.mbs;

import com.megasourceye.schooly.ejbs.BranchBean;
import com.megasourceye.schooly.entities.Branch;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author said
 */
@Named(value = "branchesMB2")
@RequestScoped
public class BranchesMB2 {

    /**
     * Creates a new instance of BranchesMB2
     */
    @Inject
    private BranchBean branchBean;
    public BranchesMB2() {
    }
    private List<Branch> selectedBranch;
//    private List<Employee> selectedEmployees;

//    public List<Employee> getEmployeeList() {
//        return EmployeeService.Instance.getEmployees();
//    }
    public List<Branch> getBranchList() {
        return branchBean.findAll();
    }

//    public List<Employee> getSelectedEmployees() {
//        return selectedEmployees;
//    }

    public List<Branch> getSelectedBranch() {
        return selectedBranch;
    }

//    public void setSelectedEmployees(List<Employee> selectedEmployees) {
//        this.selectedEmployees = selectedEmployees;
//    }

    public void setSelectedBranch(List<Branch> selectedBranch) {
        this.selectedBranch = selectedBranch;
    }

//    public void submitSelectedEmployees() {
//        RequestContext.getCurrentInstance().closeDialog(selectedEmployees);
//    }
    public void submitSelectedBranches() {
        RequestContext.getCurrentInstance().closeDialog(selectedBranch);
    }
}
