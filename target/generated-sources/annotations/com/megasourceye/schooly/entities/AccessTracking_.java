package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccessTracking.class)
public abstract class AccessTracking_ {

	public static volatile SingularAttribute<AccessTracking, String> password;
	public static volatile SingularAttribute<AccessTracking, String> macAddress;
	public static volatile SingularAttribute<AccessTracking, Date> accessDate;
	public static volatile SingularAttribute<AccessTracking, String> ipAddress;
	public static volatile SingularAttribute<AccessTracking, Long> id;
	public static volatile SingularAttribute<AccessTracking, Users> users;
	public static volatile SingularAttribute<AccessTracking, Boolean> status;
	public static volatile SingularAttribute<AccessTracking, String> username;

}

