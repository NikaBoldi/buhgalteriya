/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buhgalteriya;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Максим
 */
@Entity
@Table(name = "tip_valyuty")
@NamedQueries({
    @NamedQuery(name = "TipValyuty.findAll", query = "SELECT t FROM TipValyuty t"),
    @NamedQuery(name = "TipValyuty.findById", query = "SELECT t FROM TipValyuty t WHERE t.id = :id"),
    @NamedQuery(name = "TipValyuty.findByName", query = "SELECT t FROM TipValyuty t WHERE t.name = :name"),
    @NamedQuery(name = "TipValyuty.findByKurs", query = "SELECT t FROM TipValyuty t WHERE t.kurs = :kurs")})
public class TipValyuty implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "kurs")
    private Double kurs;
    @OneToMany(mappedBy = "tipvalut")
    private Collection<Schet> schetCollection;

    public TipValyuty() {
    }

    public TipValyuty(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getKurs() {
        return kurs;
    }

    public void setKurs(Double kurs) {
        this.kurs = kurs;
    }

    public Collection<Schet> getSchetCollection() {
        return schetCollection;
    }

    public void setSchetCollection(Collection<Schet> schetCollection) {
        this.schetCollection = schetCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipValyuty)) {
            return false;
        }
        TipValyuty other = (TipValyuty) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "buhgalteriya.TipValyuty[ id=" + id + " ]";
    }
    
}
