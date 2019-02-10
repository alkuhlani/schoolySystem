package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Vacation.class)
public abstract class Vacation_ {

	public static volatile SingularAttribute<Vacation, Date> end_date;
	public static volatile SingularAttribute<Vacation, Short> number;
	public static volatile SingularAttribute<Vacation, Parent> parent;
	public static volatile SingularAttribute<Vacation, String> reason;
	public static volatile SingularAttribute<Vacation, Boolean> state_;
	public static volatile SingularAttribute<Vacation, StudentSchool> studentSchool;
	public static volatile SingularAttribute<Vacation, Date> request_date;
	public static volatile SingularAttribute<Vacation, Short> active_day;
	public static volatile SingularAttribute<Vacation, Long> id;
	public static volatile SingularAttribute<Vacation, Date> start_date;

}

