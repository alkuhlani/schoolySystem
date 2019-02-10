package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Activity.class)
public abstract class Activity_ {

	public static volatile SingularAttribute<Activity, Float> priorityNo;
	public static volatile SingularAttribute<Activity, String> imagePath;
	public static volatile SingularAttribute<Activity, String> name;
	public static volatile SingularAttribute<Activity, SchoolYear> schoolYear;
	public static volatile SingularAttribute<Activity, Character> seenType;
	public static volatile SingularAttribute<Activity, Long> id;
	public static volatile SingularAttribute<Activity, Date> creation_date;
	public static volatile SingularAttribute<Activity, String> title;
	public static volatile SingularAttribute<Activity, Character> type;
	public static volatile SingularAttribute<Activity, Boolean> isActive;

}

