package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TeacherAssign.class)
public abstract class TeacherAssign_ {

	public static volatile SingularAttribute<TeacherAssign, Teacher> teacher;
	public static volatile ListAttribute<TeacherAssign, SubjectTable> subjectTables;
	public static volatile SingularAttribute<TeacherAssign, String> name_ar;
	public static volatile SingularAttribute<TeacherAssign, Subject_t> subject_t;
	public static volatile SingularAttribute<TeacherAssign, Class_> class_;
	public static volatile SingularAttribute<TeacherAssign, SchoolYear> schoolYear;
	public static volatile SingularAttribute<TeacherAssign, Term> term;
	public static volatile SingularAttribute<TeacherAssign, Long> id;
	public static volatile ListAttribute<TeacherAssign, MonthMark> monthMarks;
	public static volatile ListAttribute<TeacherAssign, WeekMark> weekMarks;
	public static volatile SingularAttribute<TeacherAssign, String> name_en;

}

