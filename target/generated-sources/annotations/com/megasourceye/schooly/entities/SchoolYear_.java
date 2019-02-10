package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SchoolYear.class)
public abstract class SchoolYear_ {

	public static volatile ListAttribute<SchoolYear, SchoolWeek> schoolWeeks;
	public static volatile ListAttribute<SchoolYear, TeacherAssign> teacherAssigns;
	public static volatile SingularAttribute<SchoolYear, String> name_ar;
	public static volatile ListAttribute<SchoolYear, TermWeek> termWeeks;
	public static volatile SingularAttribute<SchoolYear, SystemVariables> systemVariables;
	public static volatile ListAttribute<SchoolYear, Exam> exams;
	public static volatile ListAttribute<SchoolYear, Messaging> messagings;
	public static volatile SingularAttribute<SchoolYear, School> school;
	public static volatile ListAttribute<SchoolYear, TermMonth> termMonths;
	public static volatile ListAttribute<SchoolYear, Activity> activitys;
	public static volatile ListAttribute<SchoolYear, StudentSchool> studentSchools;
	public static volatile ListAttribute<SchoolYear, MessagingSchool> messagingSchools;
	public static volatile ListAttribute<SchoolYear, OH> oHs;
	public static volatile SingularAttribute<SchoolYear, Long> id;
	public static volatile ListAttribute<SchoolYear, SchoolMonth> schoolMonths;
	public static volatile SingularAttribute<SchoolYear, String> name_en;

}

