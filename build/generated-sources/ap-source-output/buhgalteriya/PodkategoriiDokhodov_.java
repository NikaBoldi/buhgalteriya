package buhgalteriya;

import buhgalteriya.Dokhody;
import buhgalteriya.KategoriiDokhodov;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-14T17:52:26")
@StaticMetamodel(PodkategoriiDokhodov.class)
public class PodkategoriiDokhodov_ { 

    public static volatile SingularAttribute<PodkategoriiDokhodov, Integer> iDsubcategory;
    public static volatile SingularAttribute<PodkategoriiDokhodov, KategoriiDokhodov> iDcategory;
    public static volatile SingularAttribute<PodkategoriiDokhodov, String> nameSubcategory;
    public static volatile CollectionAttribute<PodkategoriiDokhodov, Dokhody> dokhodyCollection;

}