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
@Table(name = "edenitsy_izmereniya")
@NamedQueries({
    @NamedQuery(name = "EdenitsyIzmereniya.findAll", query = "SELECT e FROM EdenitsyIzmereniya e"),
    @NamedQuery(name = "EdenitsyIzmereniya.findById", query = "SELECT e FROM EdenitsyIzmereniya e WHERE e.id = :id"),
    @NamedQuery(name = "EdenitsyIzmereniya.findByNameUnits", query = "SELECT e FROM EdenitsyIzmereniya e WHERE e.nameUnits = :nameUnits")})
public class EdenitsyIzmereniya implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NameUnits")
    private String nameUnits;
    @OneToMany(mappedBy = "units")
    private Collection<Raskhody> raskhodyCollection;

    public EdenitsyIzmereniya() {
    }

    public EdenitsyIzmereniya(Integer id) {
        this.id = id;
    }

    public EdenitsyIzmereniya(Integer id, String nameUnits) {
        this.id = id;
        this.nameUnits = nameUnits;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getNameUnits() {
        return nameUnits;
    }

    public void setNameUnits(String nameUnits) {
        String oldNameUnits = this.nameUnits;
        this.nameUnits = nameUnits;
        changeSupport.firePropertyChange("nameUnits", oldNameUnits, nameUnits);
    }

    public Collection<Raskhody> getRaskhodyCollection() {
        return raskhodyCollection;
    }

    public void setRaskhodyCollection(Collection<Raskhody> raskhodyCollection) {
        this.raskhodyCollection = raskhodyCollection;
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
        if (!(object instanceof EdenitsyIzmereniya)) {
            return false;
        }
        EdenitsyIzmereniya other = (EdenitsyIzmereniya) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nameUnits;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
