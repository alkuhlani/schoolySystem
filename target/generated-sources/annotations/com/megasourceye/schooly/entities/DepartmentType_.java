package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DepartmentType.class)
public abstract class DepartmentType_ {

	public static volatile SingularAttribute<DepartmentType, String> name_ar;
	public static volatile ListAttribute<DepartmentType, Department> departments;
	public static volatile SingularAttribute<DepartmentType, Long> id;
	public static volatile SingularAttribute<DepartmentType, String> name_en;

}

