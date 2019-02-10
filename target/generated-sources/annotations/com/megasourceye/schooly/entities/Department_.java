package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Department.class)
public abstract class Department_ {

	public static volatile ListAttribute<Department, StudentSchool> studentSchools;
	public static volatile SingularAttribute<Department, String> name_ar;
	public static volatile ListAttribute<Department, Student> students;
	public static volatile SingularAttribute<Department, Long> id;
	public static volatile SingularAttribute<Department, DepartmentType> departmentType;
	public static volatile ListAttribute<Department, Class_> class_s;
	public static volatile SingularAttribute<Department, String> name_en;

}

