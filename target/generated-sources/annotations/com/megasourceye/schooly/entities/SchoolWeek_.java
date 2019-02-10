package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SchoolWeek.class)
public abstract class SchoolWeek_ {

	public static volatile ListAttribute<SchoolWeek, Exam> exams;
	public static volatile SingularAttribute<SchoolWeek, Date> endDate;
	public static volatile SingularAttribute<SchoolWeek, String> name_ar;
	public static volatile SingularAttribute<SchoolWeek, Date> firstDate;
	public static volatile SingularAttribute<SchoolWeek, Character> termType;
	public static volatile SingularAttribute<SchoolWeek, SchoolYear> schoolYear;
	public static volatile SingularAttribute<SchoolWeek, Long> id;
	public static volatile SingularAttribute<SchoolWeek, SystemVariables> systemVariables;
	public static volatile ListAttribute<SchoolWeek, WeekMark> weekMarks;
	public static volatile SingularAttribute<SchoolWeek, String> name_en;
	public static volatile SingularAttribute<SchoolWeek, TermWeek> termWeek;

}

