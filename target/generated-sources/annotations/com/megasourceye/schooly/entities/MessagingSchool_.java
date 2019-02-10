package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MessagingSchool.class)
public abstract class MessagingSchool_ {

	public static volatile SingularAttribute<MessagingSchool, Date> send_date;
	public static volatile SingularAttribute<MessagingSchool, SchoolYear> schoolYear;
	public static volatile SingularAttribute<MessagingSchool, Long> id;
	public static volatile SingularAttribute<MessagingSchool, String> text;
	public static volatile SingularAttribute<MessagingSchool, Character> type;
	public static volatile SingularAttribute<MessagingSchool, Users> users;
	public static volatile SingularAttribute<MessagingSchool, Boolean> seen;

}

