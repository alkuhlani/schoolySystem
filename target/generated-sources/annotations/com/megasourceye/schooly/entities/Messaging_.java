package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Messaging.class)
public abstract class Messaging_ {

	public static volatile SingularAttribute<Messaging, Users> users1;
	public static volatile SingularAttribute<Messaging, Date> send_date;
	public static volatile SingularAttribute<Messaging, SchoolYear> schoolYear;
	public static volatile SingularAttribute<Messaging, Long> id;
	public static volatile SingularAttribute<Messaging, String> text;
	public static volatile SingularAttribute<Messaging, Users> users;
	public static volatile SingularAttribute<Messaging, Boolean> seen;

}

