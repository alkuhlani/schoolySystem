package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TermMonth.class)
public abstract class TermMonth_ {

	public static volatile ListAttribute<TermMonth, Term2MArk> term2MArks;
	public static volatile ListAttribute<TermMonth, Exam> exams;
	public static volatile ListAttribute<TermMonth, SystemVariables> systemVariabless;
	public static volatile SingularAttribute<TermMonth, Date> endDate;
	public static volatile SingularAttribute<TermMonth, String> name_ar;
	public static volatile SingularAttribute<TermMonth, Date> firstDate;
	public static volatile SingularAttribute<TermMonth, SchoolYear> schoolYear;
	public static volatile SingularAttribute<TermMonth, Term> term;
	public static volatile SingularAttribute<TermMonth, Long> id;
	public static volatile ListAttribute<TermMonth, SchoolMonth> schoolMonths;
	public static volatile SingularAttribute<TermMonth, Short> noMonths;
	public static volatile SingularAttribute<TermMonth, String> name_en;

}

