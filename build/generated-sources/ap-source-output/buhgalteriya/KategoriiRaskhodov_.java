package buhgalteriya;

import buhgalteriya.PodkategoriiRaskhodov;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-14T17:52:26")
@StaticMetamodel(KategoriiRaskhodov.class)
public class KategoriiRaskhodov_ { 

    public static volatile SingularAttribute<KategoriiRaskhodov, Integer> id;
    public static volatile CollectionAttribute<KategoriiRaskhodov, PodkategoriiRaskhodov> podkategoriiRaskhodovCollection;
    public static volatile SingularAttribute<KategoriiRaskhodov, String> nameCategory;

}