package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Exam.class)
public abstract class Exam_ {

	public static volatile SingularAttribute<Exam, TermMonth> termMonth;
	public static volatile ListAttribute<Exam, ExamTable> examTables;
	public static volatile SingularAttribute<Exam, String> name_ar;
	public static volatile SingularAttribute<Exam, SchoolYear> schoolYear;
	public static volatile SingularAttribute<Exam, Long> id;
	public static volatile SingularAttribute<Exam, Character> type;
	public static volatile SingularAttribute<Exam, Boolean> isActive;
	public static volatile SingularAttribute<Exam, SchoolMonth> schoolMonth;
	public static volatile SingularAttribute<Exam, String> name_en;
	public static volatile SingularAttribute<Exam, TermWeek> termWeek;
	public static volatile SingularAttribute<Exam, SchoolWeek> schoolWeek;

}

