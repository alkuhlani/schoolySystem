package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Subject_t.class)
public abstract class Subject_t_ {

	public static volatile ListAttribute<Subject_t, Term2MArk> term2MArks;
	public static volatile SingularAttribute<Subject_t, String> shortcuts_en;
	public static volatile SingularAttribute<Subject_t, School> school;
	public static volatile ListAttribute<Subject_t, ExamTable> examTables;
	public static volatile ListAttribute<Subject_t, TeacherAssign> teacherAssigns;
	public static volatile SingularAttribute<Subject_t, String> name_ar;
	public static volatile ListAttribute<Subject_t, Term3Mark> term3Marks;
	public static volatile SingularAttribute<Subject_t, Long> id;
	public static volatile SingularAttribute<Subject_t, String> shortcuts_ar;
	public static volatile ListAttribute<Subject_t, MonthMark> monthMarks;
	public static volatile SingularAttribute<Subject_t, String> name_en;

}

