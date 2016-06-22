package buhgalteriya;

import buhgalteriya.EdenitsyIzmereniya;
import buhgalteriya.PodkategoriiRaskhodov;
import buhgalteriya.Schet;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-05-14T17:52:26")
@StaticMetamodel(Raskhody.class)
public class Raskhody_ { 

    public static volatile SingularAttribute<Raskhody, Integer> id;
    public static volatile SingularAttribute<Raskhody, Schet> schet;
    public static volatile SingularAttribute<Raskhody, Integer> cnt;
    public static volatile SingularAttribute<Raskhody, Double> summa;
    public static volatile SingularAttribute<Raskhody, PodkategoriiRaskhodov> podkategoriyRaskhodov;
    public static volatile SingularAttribute<Raskhody, Date> date;
    public static volatile SingularAttribute<Raskhody, String> primechanie;
    public static volatile SingularAttribute<Raskhody, EdenitsyIzmereniya> units;

}