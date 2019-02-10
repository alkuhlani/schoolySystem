package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TermType.class)
public abstract class TermType_ {

	public static volatile ListAttribute<TermType, Term> terms;
	public static volatile ListAttribute<TermType, SystemVariables> systemVariabless;
	public static volatile SingularAttribute<TermType, String> name_ar;
	public static volatile SingularAttribute<TermType, Long> id;
	public static volatile SingularAttribute<TermType, String> name_en;

}

