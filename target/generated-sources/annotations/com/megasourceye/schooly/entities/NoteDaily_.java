package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NoteDaily.class)
public abstract class NoteDaily_ {

	public static volatile SingularAttribute<NoteDaily, Boolean> parent_seen;
	public static volatile SingularAttribute<NoteDaily, StudentSchool> studentSchool;
	public static volatile SingularAttribute<NoteDaily, SubjectTable> subjectTable;
	public static volatile SingularAttribute<NoteDaily, String> name;
	public static volatile SingularAttribute<NoteDaily, Date> writeDate;
	public static volatile SingularAttribute<NoteDaily, Long> id;
	public static volatile SingularAttribute<NoteDaily, Boolean> read_;
	public static volatile SingularAttribute<NoteDaily, Boolean> isSpec;
	public static volatile SingularAttribute<NoteDaily, Boolean> forStudent;
	public static volatile SingularAttribute<NoteDaily, Boolean> forParent;
	public static volatile SingularAttribute<NoteDaily, Boolean> student_seen;

}

