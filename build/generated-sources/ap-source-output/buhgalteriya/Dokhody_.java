package buhgalteriya;

import buhgalteriya.PodkategoriiDokhodov;
import buhgalteriya.Schet;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-14T17:52:26")
@StaticMetamodel(Dokhody.class)
public class Dokhody_ { 

    public static volatile SingularAttribute<Dokhody, Integer> id;
    public static volatile SingularAttribute<Dokhody, Schet> schet;
    public static volatile SingularAttribute<Dokhody, PodkategoriiDokhodov> podkategoriyDokhoda;
    public static volatile SingularAttribute<Dokhody, Double> summa;
    public static volatile SingularAttribute<Dokhody, Date> date;
    public static volatile SingularAttribute<Dokhody, String> primechanie;

}