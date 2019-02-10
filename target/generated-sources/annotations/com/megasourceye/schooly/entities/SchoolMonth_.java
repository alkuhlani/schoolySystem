package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SchoolMonth.class)
public abstract class SchoolMonth_ {

	public static volatile ListAttribute<SchoolMonth, Exam> exams;
	public static volatile SingularAttribute<SchoolMonth, TermMonth> termMonth;
	public static volatile ListAttribute<SchoolMonth, SystemVariables> systemVariabless;
	public static volatile SingularAttribute<SchoolMonth, Date> endDate;
	public static volatile SingularAttribute<SchoolMonth, String> name_ar;
	public static volatile SingularAttribute<SchoolMonth, Date> firstDate;
	public static volatile SingularAttribute<SchoolMonth, SchoolYear> schoolYear;
	public static volatile SingularAttribute<SchoolMonth, Long> id;
	public static volatile ListAttribute<SchoolMonth, MonthMark> monthMarks;
	public static volatile SingularAttribute<SchoolMonth, SystemVariables> systemVariables;
	public static volatile SingularAttribute<SchoolMonth, String> name_en;

}

