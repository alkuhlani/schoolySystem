package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ExamTable.class)
public abstract class ExamTable_ {

	public static volatile SingularAttribute<ExamTable, Exam> exam;
	public static volatile SingularAttribute<ExamTable, Date> examDate;
	public static volatile SingularAttribute<ExamTable, Date> examTimeDuring;
	public static volatile SingularAttribute<ExamTable, String> name;
	public static volatile SingularAttribute<ExamTable, Class_> class_;
	public static volatile SingularAttribute<ExamTable, Subject_t> subject_t;
	public static volatile SingularAttribute<ExamTable, Long> id;
	public static volatile SingularAttribute<ExamTable, Character> termHalfType;
	public static volatile SingularAttribute<ExamTable, Date> beginTime;

}

