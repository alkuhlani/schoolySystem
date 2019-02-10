package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Student.class)
public abstract class Student_ {

	public static volatile SingularAttribute<Student, Parent> parent;
	public static volatile SingularAttribute<Student, String> Address;
	public static volatile SingularAttribute<Student, Boolean> gender;
	public static volatile SingularAttribute<Student, String> imagePath;
	public static volatile SingularAttribute<Student, String> name_ar;
	public static volatile SingularAttribute<Student, Date> birthDate;
	public static volatile SingularAttribute<Student, String> phone1;
	public static volatile SingularAttribute<Student, Long> studentID;
	public static volatile ListAttribute<Student, StudentParent> studentParents;
	public static volatile SingularAttribute<Student, School> school;
	public static volatile ListAttribute<Student, StudentSchool> studentSchools;
	public static volatile SingularAttribute<Student, Long> id;
	public static volatile SingularAttribute<Student, Department> department;
	public static volatile SingularAttribute<Student, Users> user;
	public static volatile SingularAttribute<Student, String> email;
	public static volatile SingularAttribute<Student, String> name_en;
	public static volatile SingularAttribute<Student, Date> registerDate;

}

