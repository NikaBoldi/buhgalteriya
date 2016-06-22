package buhgalteriya;

import buhgalteriya.KategoriiRaskhodov;
import buhgalteriya.Raskhody;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-14T17:52:26")
@StaticMetamodel(PodkategoriiRaskhodov.class)
public class PodkategoriiRaskhodov_ { 

    public static volatile SingularAttribute<PodkategoriiRaskhodov, Integer> iDsubcategory;
    public static volatile CollectionAttribute<PodkategoriiRaskhodov, Raskhody> raskhodyCollection;
    public static volatile SingularAttribute<PodkategoriiRaskhodov, KategoriiRaskhodov> iDcategory;
    public static volatile SingularAttribute<PodkategoriiRaskhodov, String> nameSubcategory;

}