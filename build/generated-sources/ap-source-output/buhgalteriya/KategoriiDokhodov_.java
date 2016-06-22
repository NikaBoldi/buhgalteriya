package buhgalteriya;

import buhgalteriya.PodkategoriiDokhodov;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-14T17:52:26")
@StaticMetamodel(KategoriiDokhodov.class)
public class KategoriiDokhodov_ { 

    public static volatile SingularAttribute<KategoriiDokhodov, Integer> id;
    public static volatile CollectionAttribute<KategoriiDokhodov, PodkategoriiDokhodov> podkategoriiDokhodovCollection;
    public static volatile SingularAttribute<KategoriiDokhodov, String> nameCategory;

}