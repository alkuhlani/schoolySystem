package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Employee.class)
public abstract class Employee_ {

	public static volatile SingularAttribute<Employee, Boolean> gender;
	public static volatile SingularAttribute<Employee, School> school;
	public static volatile SingularAttribute<Employee, String> name_ar;
	public static volatile SingularAttribute<Employee, String> phone2;
	public static volatile SingularAttribute<Employee, Long> employeeID;
	public static volatile SingularAttribute<Employee, Long> id;
	public static volatile SingularAttribute<Employee, Users> user;
	public static volatile SingularAttribute<Employee, String> name_en;
	public static volatile SingularAttribute<Employee, String> phone1;
	public static volatile SingularAttribute<Employee, Date> registerDate;

}

