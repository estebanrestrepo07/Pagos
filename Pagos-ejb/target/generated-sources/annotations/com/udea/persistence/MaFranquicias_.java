package com.udea.persistence;

import com.udea.persistence.TsTransaccion;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-23T08:55:05")
@StaticMetamodel(MaFranquicias.class)
public class MaFranquicias_ { 

    public static volatile SingularAttribute<MaFranquicias, Long> fqRangoSu;
    public static volatile SingularAttribute<MaFranquicias, String> fqNombre;
    public static volatile SingularAttribute<MaFranquicias, Integer> fqId;
    public static volatile SingularAttribute<MaFranquicias, Long> fqRangoInf;
    public static volatile CollectionAttribute<MaFranquicias, TsTransaccion> tsTransaccionCollection;

}