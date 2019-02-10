package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Class_.class)
public abstract class Class__ {

	public static volatile SingularAttribute<Class_, StaticLevel> staticLevel;
	public static volatile SingularAttribute<Class_, Character> subClass;
	public static volatile ListAttribute<Class_, ExamTable> examTables;
	public static volatile ListAttribute<Class_, TeacherAssign> teacherAssigns;
	public static volatile SingularAttribute<Class_, String> name_ar;
	public static volatile SingularAttribute<Class_, Double> fee;
	public static volatile SingularAttribute<Class_, Short> noClass;
	public static volatile SingularAttribute<Class_, Branch> branch;
	public static volatile SingularAttribute<Class_, StaticClass> staticClass;
	public static volatile SingularAttribute<Class_, School> school;
	public static volatile ListAttribute<Class_, StudentSchool> studentSchools;
	public static volatile SingularAttribute<Class_, StaticSubClass> staticSubClass;
	public static volatile SingularAttribute<Class_, Long> id;
	public static volatile SingularAttribute<Class_, Department> department;
	public static volatile SingularAttribute<Class_, String> name_en;

}

