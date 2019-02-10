package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Teacher.class)
public abstract class Teacher_ {

	public static volatile SingularAttribute<Teacher, Boolean> isEmployee;
	public static volatile SingularAttribute<Teacher, Boolean> gender;
	public static volatile SingularAttribute<Teacher, String> imagePath;
	public static volatile ListAttribute<Teacher, TeacherAssign> teacherAssigns;
	public static volatile SingularAttribute<Teacher, String> name_ar;
	public static volatile SingularAttribute<Teacher, String> phone2;
	public static volatile SingularAttribute<Teacher, String> forParent;
	public static volatile SingularAttribute<Teacher, String> phone1;
	public static volatile SingularAttribute<Teacher, String> vision;
	public static volatile SingularAttribute<Teacher, Long> teacherID;
	public static volatile SingularAttribute<Teacher, School> school;
	public static volatile SingularAttribute<Teacher, Long> id;
	public static volatile SingularAttribute<Teacher, Boolean> isTeacher;
	public static volatile SingularAttribute<Teacher, Users> user;
	public static volatile SingularAttribute<Teacher, String> forStudent;
	public static volatile SingularAttribute<Teacher, String> email;
	public static volatile SingularAttribute<Teacher, String> name_en;

}

