package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TermWeek.class)
public abstract class TermWeek_ {

	public static volatile ListAttribute<TermWeek, SchoolWeek> schoolWeeks;
	public static volatile SingularAttribute<TermWeek, Date> first1Date;
	public static volatile SingularAttribute<TermWeek, String> name_ar;
	public static volatile SingularAttribute<TermWeek, Date> first2Date;
	public static volatile SingularAttribute<TermWeek, Short> noFirst;
	public static volatile SingularAttribute<TermWeek, Date> end2Date;
	public static volatile SingularAttribute<TermWeek, SystemVariables> systemVariables;
	public static volatile ListAttribute<TermWeek, Exam> exams;
	public static volatile SingularAttribute<TermWeek, School> school;
	public static volatile ListAttribute<TermWeek, Term3Mark> term3Marks;
	public static volatile SingularAttribute<TermWeek, SchoolYear> schoolYear;
	public static volatile SingularAttribute<TermWeek, Term> term;
	public static volatile SingularAttribute<TermWeek, Long> id;
	public static volatile SingularAttribute<TermWeek, Short> noEnd;
	public static volatile SingularAttribute<TermWeek, String> name_en;
	public static volatile SingularAttribute<TermWeek, Date> end1Date;

}

