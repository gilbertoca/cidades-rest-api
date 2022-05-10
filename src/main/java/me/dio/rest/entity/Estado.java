package me.dio.rest.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@TypeDefs({
    @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Entity
public class Estado implements Serializable {

    @Id
    private Long id;
    private String nome;
    private String uf;
    private Integer ibge;
    private Integer pais;
    @Type(type = "jsonb")
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ddd", columnDefinition = "jsonb")    
    private List<Integer> ddd;

    public Estado() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return nome;
    }

    public String getUf() {
        return uf;
    }

    public Integer getIbge() {
        return ibge;
    }

    public List<Integer> getDdd() {
        return ddd;
    }

    public Integer getPais() {
        return pais;
    }
}
