package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WeekMark.class)
public abstract class WeekMark_ {

	public static volatile SingularAttribute<WeekMark, Boolean> isApproval;
	public static volatile SingularAttribute<WeekMark, Float> total;
	public static volatile SingularAttribute<WeekMark, TeacherAssign> teacherAssign;
	public static volatile SingularAttribute<WeekMark, StudentSchool> studentSchool;
	public static volatile SingularAttribute<WeekMark, Long> id;
	public static volatile SingularAttribute<WeekMark, Float> behavior;
	public static volatile SingularAttribute<WeekMark, Date> dataEntryDate;
	public static volatile SingularAttribute<WeekMark, Float> attendance;
	public static volatile SingularAttribute<WeekMark, Float> quize;
	public static volatile SingularAttribute<WeekMark, Float> hw;
	public static volatile SingularAttribute<WeekMark, SchoolWeek> schoolWeek;

}

