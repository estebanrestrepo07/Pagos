package com.udea.persistence;

import com.udea.persistence.TsTransaccion;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-04-23T15:38:47")
@StaticMetamodel(TsCliente.class)
public class TsCliente_ { 

    public static volatile SingularAttribute<TsCliente, String> ctEmail;
    public static volatile SingularAttribute<TsCliente, String> ctId;
    public static volatile SingularAttribute<TsCliente, String> ctNombre;
    public static volatile CollectionAttribute<TsCliente, TsTransaccion> tsTransaccionCollection;

}