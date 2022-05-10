package me.dio.rest.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.geo.Point;

@TypeDefs(value = {
    @TypeDef(name = "point", typeClass = PointType.class)
})
@Entity
public class Cidade implements Serializable {

    @Id
    private Long id;
    private String nome;
    private Integer uf;
    private Integer ibge;
    // 1st
    @Column(name = "lat_lon")
    private String geolocation;

    // 2nd
    @Type(type = "point")
    @Column(name = "lat_lon", updatable = false, insertable = false)
    private Point location;

    public Cidade() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getUf() {
        return uf;
    }

    public Integer getIbge() {
        return ibge;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public Point getLocation() {
        return location;
    }

}
