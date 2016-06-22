package buhgalteriya;

import buhgalteriya.Schet;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-14T17:52:26")
@StaticMetamodel(Vladeltsy.class)
public class Vladeltsy_ { 

    public static volatile SingularAttribute<Vladeltsy, Integer> id;
    public static volatile CollectionAttribute<Vladeltsy, Schet> schetCollection;
    public static volatile SingularAttribute<Vladeltsy, String> firstName;
    public static volatile SingularAttribute<Vladeltsy, Integer> password;

}