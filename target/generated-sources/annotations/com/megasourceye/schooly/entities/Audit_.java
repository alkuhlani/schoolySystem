package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Audit.class)
public abstract class Audit_ {

	public static volatile SingularAttribute<Audit, StudentSchool> studentSchool;
	public static volatile SingularAttribute<Audit, Date> lastUpdateDate;
	public static volatile SingularAttribute<Audit, Long> id;
	public static volatile SingularAttribute<Audit, String> lastData;
	public static volatile SingularAttribute<Audit, String> firstData;
	public static volatile SingularAttribute<Audit, Character> typeState;
	public static volatile SingularAttribute<Audit, Users> user;
	public static volatile SingularAttribute<Audit, Date> createDate;

}

