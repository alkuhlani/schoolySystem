package com.megasourceye.schooly.entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HomeWork.class)
public abstract class HomeWork_ {

	public static volatile SingularAttribute<HomeWork, SubjectTable> subjectTable;
	public static volatile SingularAttribute<HomeWork, String> name;
	public static volatile SingularAttribute<HomeWork, Long> id;
	public static volatile SingularAttribute<HomeWork, Date> deliveryDate;
	public static volatile SingularAttribute<HomeWork, Date> dateEntry;

}

