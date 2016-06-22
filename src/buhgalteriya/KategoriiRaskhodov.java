/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buhgalteriya;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Максим
 */
@Entity
@Table(name = "kategorii_raskhodov")
@NamedQueries({
    @NamedQuery(name = "KategoriiRaskhodov.findAll", query = "SELECT k FROM KategoriiRaskhodov k"),
    @NamedQuery(name = "KategoriiRaskhodov.findById", query = "SELECT k FROM KategoriiRaskhodov k WHERE k.id = :id"),
    @NamedQuery(name = "KategoriiRaskhodov.findByNameCategory", query = "SELECT k FROM KategoriiRaskhodov k WHERE k.nameCategory = :nameCategory")})
public class KategoriiRaskhodov implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NameCategory")
    private String nameCategory;
    @OneToMany(mappedBy = "iDcategory")
    private Collection<PodkategoriiRaskhodov> podkategoriiRaskhodovCollection;

    public KategoriiRaskhodov() {
    }

    public KategoriiRaskhodov(Integer id) {
        this.id = id;
    }

    public KategoriiRaskhodov(Integer id, String nameCategory) {
        this.id = id;
        this.nameCategory = nameCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        String oldNameCategory = this.nameCategory;
        this.nameCategory = nameCategory;
        changeSupport.firePropertyChange("nameCategory", oldNameCategory, nameCategory);
    }

    public Collection<PodkategoriiRaskhodov> getPodkategoriiRaskhodovCollection() {
        return podkategoriiRaskhodovCollection;
    }

    public void setPodkategoriiRaskhodovCollection(Collection<PodkategoriiRaskhodov> podkategoriiRaskhodovCollection) {
        this.podkategoriiRaskhodovCollection = podkategoriiRaskhodovCollection;
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
        if (!(object instanceof KategoriiRaskhodov)) {
            return false;
        }
        KategoriiRaskhodov other = (KategoriiRaskhodov) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nameCategory;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
