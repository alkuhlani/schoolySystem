package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(OH.class)
public abstract class OH_ {

	public static volatile SingularAttribute<OH, DT> dt;
	public static volatile SingularAttribute<OH, School> school;
	public static volatile SingularAttribute<OH, String> name_ar;
	public static volatile SingularAttribute<OH, SchoolYear> schoolYear;
	public static volatile SingularAttribute<OH, Long> id;
	public static volatile SingularAttribute<OH, Character> type;
	public static volatile SingularAttribute<OH, SystemVariables> systemVariables;
	public static volatile SingularAttribute<OH, String> name_en;
	public static volatile SingularAttribute<OH, Date> holidayDate;

}

