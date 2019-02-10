package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(NoteSpec.class)
public abstract class NoteSpec_ {

	public static volatile SingularAttribute<NoteSpec, StudentSchool> studentSchool;
	public static volatile SingularAttribute<NoteSpec, SubjectTable> subjectTable;
	public static volatile SingularAttribute<NoteSpec, String> name;
	public static volatile SingularAttribute<NoteSpec, Date> writeDate;
	public static volatile SingularAttribute<NoteSpec, Long> id;
	public static volatile SingularAttribute<NoteSpec, Boolean> read_;

}

