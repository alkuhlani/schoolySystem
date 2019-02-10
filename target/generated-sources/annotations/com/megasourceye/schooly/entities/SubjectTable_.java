package com.megasourceye.schooly.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SubjectTable.class)
public abstract class SubjectTable_ {

	public static volatile SingularAttribute<SubjectTable, TeacherAssign> teacherAssign;
	public static volatile ListAttribute<SubjectTable, NoteDaily> noteDailys;
	public static volatile SingularAttribute<SubjectTable, String> name_ar;
	public static volatile SingularAttribute<SubjectTable, Character> lectureNo;
	public static volatile ListAttribute<SubjectTable, NoteSpec> noteSpecs;
	public static volatile SingularAttribute<SubjectTable, Long> id;
	public static volatile SingularAttribute<SubjectTable, Boolean> stateDeActive;
	public static volatile ListAttribute<SubjectTable, HomeWork> homeWorks;
	public static volatile SingularAttribute<SubjectTable, DT> day;
	public static volatile SingularAttribute<SubjectTable, String> name_en;

}

