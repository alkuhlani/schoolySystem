package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(School.class)
public abstract class School_ {

	public static volatile ListAttribute<School, Users> userss;
	public static volatile ListAttribute<School, SchoolYear> schoolYears;
	public static volatile SingularAttribute<School, String> name_ar;
	public static volatile ListAttribute<School, Student> students;
	public static volatile SingularAttribute<School, String> managerName;
	public static volatile ListAttribute<School, Class_> class_s;
	public static volatile SingularAttribute<School, String> webSiteUrl;
	public static volatile SingularAttribute<School, SystemVariables> systemVariables;
	public static volatile ListAttribute<School, Teacher> teachers;
	public static volatile ListAttribute<School, OH> oHs;
	public static volatile SingularAttribute<School, Long> id;
	public static volatile SingularAttribute<School, String> email;
	public static volatile ListAttribute<School, Branch> branchs;
	public static volatile ListAttribute<School, WeekTopMark> weekTopMarks;
	public static volatile SingularAttribute<School, String> iconFile;
	public static volatile ListAttribute<School, TermWeek> termWeeks;
	public static volatile ListAttribute<School, TermTopMark> termTopMarks;
	public static volatile SingularAttribute<School, String> phoneContact;
	public static volatile ListAttribute<School, StudentSchool> studentSchools;
	public static volatile ListAttribute<School, Term2TopMark> term2TopMarks;
	public static volatile ListAttribute<School, MonthTopMark> monthTopMarks;
	public static volatile SingularAttribute<School, String> location;
	public static volatile ListAttribute<School, Subject_t> subject_ts;
	public static volatile ListAttribute<School, Employee> employees;
	public static volatile SingularAttribute<School, String> fb;
	public static volatile ListAttribute<School, Parent> parents;
	public static volatile SingularAttribute<School, String> name_en;

}

