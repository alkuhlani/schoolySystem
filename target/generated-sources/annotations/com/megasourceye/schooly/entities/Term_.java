package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Term.class)
public abstract class Term_ {

	public static volatile ListAttribute<Term, TermMonth> termMonths;
	public static volatile ListAttribute<Term, TeacherAssign> teacherAssigns;
	public static volatile SingularAttribute<Term, String> name_ar;
	public static volatile SingularAttribute<Term, TermType> termType;
	public static volatile ListAttribute<Term, TermWeek> termWeeks;
	public static volatile SingularAttribute<Term, Long> id;
	public static volatile SingularAttribute<Term, String> name_en;

}

