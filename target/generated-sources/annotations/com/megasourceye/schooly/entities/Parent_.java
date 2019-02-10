package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Parent.class)
public abstract class Parent_ {

	public static volatile SingularAttribute<Parent, Boolean> gender;
	public static volatile SingularAttribute<Parent, String> name_ar;
	public static volatile SingularAttribute<Parent, String> phone2;
	public static volatile ListAttribute<Parent, Student> students;
	public static volatile SingularAttribute<Parent, Long> parentID;
	public static volatile SingularAttribute<Parent, String> phone1;
	public static volatile ListAttribute<Parent, StudentParent> studentParents;
	public static volatile SingularAttribute<Parent, School> school;
	public static volatile ListAttribute<Parent, Vacation> vacations;
	public static volatile SingularAttribute<Parent, Long> id;
	public static volatile SingularAttribute<Parent, Users> user;
	public static volatile SingularAttribute<Parent, String> email;
	public static volatile SingularAttribute<Parent, String> name_en;

}

