package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StudentSchool.class)
public abstract class StudentSchool_ {

	public static volatile ListAttribute<StudentSchool, Term2MArk> term2MArks;
	public static volatile SingularAttribute<StudentSchool, Boolean> deaActive;
	public static volatile SingularAttribute<StudentSchool, Student> student;
	public static volatile SingularAttribute<StudentSchool, Character> subClass;
	public static volatile ListAttribute<StudentSchool, NoteDaily> noteDailys;
	public static volatile SingularAttribute<StudentSchool, Class_> class_;
	public static volatile SingularAttribute<StudentSchool, Float> discount;
	public static volatile ListAttribute<StudentSchool, MonthMark> monthMarks;
	public static volatile ListAttribute<StudentSchool, Attendance> attendances;
	public static volatile ListAttribute<StudentSchool, WeekMark> weekMarks;
	public static volatile SingularAttribute<StudentSchool, School> school;
	public static volatile ListAttribute<StudentSchool, Vacation> vacations;
	public static volatile SingularAttribute<StudentSchool, Audit> audit;
	public static volatile ListAttribute<StudentSchool, Term3Mark> term3Marks;
	public static volatile SingularAttribute<StudentSchool, SchoolYear> schoolYear;
	public static volatile ListAttribute<StudentSchool, NoteSpec> noteSpecs;
	public static volatile SingularAttribute<StudentSchool, Long> id;
	public static volatile SingularAttribute<StudentSchool, Department> department;

}

