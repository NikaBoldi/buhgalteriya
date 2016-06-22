package buhgalteriya;

import buhgalteriya.Schet;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-14T17:52:26")
@StaticMetamodel(TipValyuty.class)
public class TipValyuty_ { 

    public static volatile SingularAttribute<TipValyuty, Integer> id;
    public static volatile CollectionAttribute<TipValyuty, Schet> schetCollection;
    public static volatile SingularAttribute<TipValyuty, String> name;
    public static volatile SingularAttribute<TipValyuty, Double> kurs;

}