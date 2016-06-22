package buhgalteriya;

import buhgalteriya.Dokhody;
import buhgalteriya.Raskhody;
import buhgalteriya.TipValyuty;
import buhgalteriya.Vladeltsy;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-14T17:52:26")
@StaticMetamodel(Schet.class)
public class Schet_ { 

    public static volatile SingularAttribute<Schet, Integer> id;
    public static volatile SingularAttribute<Schet, TipValyuty> tipvalut;
    public static volatile CollectionAttribute<Schet, Raskhody> raskhodyCollection;
    public static volatile SingularAttribute<Schet, Double> suma;
    public static volatile SingularAttribute<Schet, String> name;
    public static volatile SingularAttribute<Schet, Vladeltsy> accountholder;
    public static volatile CollectionAttribute<Schet, Dokhody> dokhodyCollection;

}