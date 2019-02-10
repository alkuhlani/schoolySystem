package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Users.class)
public abstract class Users_ {

	public static volatile SingularAttribute<Users, Parent> parent;
	public static volatile SingularAttribute<Users, Student> student;
	public static volatile SingularAttribute<Users, String> imagePath;
	public static volatile ListAttribute<Users, AccessTracking> accessTrackings;
	public static volatile SingularAttribute<Users, String> name_ar;
	public static volatile ListAttribute<Users, Roles> roles;
	public static volatile SingularAttribute<Users, Employee> employee;
	public static volatile SingularAttribute<Users, String> password;
	public static volatile SingularAttribute<Users, Teacher> teacher;
	public static volatile SingularAttribute<Users, String> role_;
	public static volatile ListAttribute<Users, Messaging> messagings;
	public static volatile SingularAttribute<Users, String> phone;
	public static volatile SingularAttribute<Users, School> school;
	public static volatile ListAttribute<Users, MessagingSchool> messagingSchools;
	public static volatile ListAttribute<Users, Messaging> messagings1;
	public static volatile SingularAttribute<Users, Long> id;
	public static volatile ListAttribute<Users, Audit> audits;
	public static volatile SingularAttribute<Users, String> name_en;
	public static volatile SingularAttribute<Users, String> username;

}

