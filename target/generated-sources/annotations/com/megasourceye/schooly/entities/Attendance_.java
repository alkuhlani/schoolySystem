package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Attendance.class)
public abstract class Attendance_ {

	public static volatile SingularAttribute<Attendance, StudentSchool> studentSchool;
	public static volatile SingularAttribute<Attendance, Long> id;
	public static volatile SingularAttribute<Attendance, Date> attendanceDate;
	public static volatile SingularAttribute<Attendance, Character> status;

}

