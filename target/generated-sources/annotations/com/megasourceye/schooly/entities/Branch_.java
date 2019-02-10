package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Branch.class)
public abstract class Branch_ {

	public static volatile SingularAttribute<Branch, School> school;
	public static volatile SingularAttribute<Branch, String> name_ar;
	public static volatile SingularAttribute<Branch, Long> id;
	public static volatile ListAttribute<Branch, Class_> class_s;
	public static volatile SingularAttribute<Branch, String> name_en;

}

