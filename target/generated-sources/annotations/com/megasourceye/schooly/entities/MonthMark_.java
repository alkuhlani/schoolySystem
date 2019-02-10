package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MonthMark.class)
public abstract class MonthMark_ {

	public static volatile SingularAttribute<MonthMark, Boolean> isApproval;
	public static volatile SingularAttribute<MonthMark, Float> total;
	public static volatile SingularAttribute<MonthMark, TeacherAssign> teacherAssign;
	public static volatile SingularAttribute<MonthMark, StudentSchool> studentSchool;
	public static volatile SingularAttribute<MonthMark, Float> writing;
	public static volatile SingularAttribute<MonthMark, String> name;
	public static volatile SingularAttribute<MonthMark, Subject_t> subject_t;
	public static volatile SingularAttribute<MonthMark, Float> reading;
	public static volatile SingularAttribute<MonthMark, Long> id;
	public static volatile SingularAttribute<MonthMark, Float> behavior;
	public static volatile SingularAttribute<MonthMark, SchoolMonth> schoolMonth;
	public static volatile SingularAttribute<MonthMark, Float> hw;

}

