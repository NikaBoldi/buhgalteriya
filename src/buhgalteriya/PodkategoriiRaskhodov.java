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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "podkategorii_raskhodov")
@NamedQueries({
    @NamedQuery(name = "PodkategoriiRaskhodov.findAll", query = "SELECT p FROM PodkategoriiRaskhodov p"),
    @NamedQuery(name = "PodkategoriiRaskhodov.findByIDsubcategory", query = "SELECT p FROM PodkategoriiRaskhodov p WHERE p.iDsubcategory = :iDsubcategory"),
    @NamedQuery(name = "PodkategoriiRaskhodov.findByNameSubcategory", query = "SELECT p FROM PodkategoriiRaskhodov p WHERE p.nameSubcategory = :nameSubcategory")})
public class PodkategoriiRaskhodov implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_subcategory")
    private Integer iDsubcategory;
    @Basic(optional = false)
    @Column(name = "NameSubcategory")
    private String nameSubcategory;
    @OneToMany(mappedBy = "podkategoriyRaskhodov")
    private Collection<Raskhody> raskhodyCollection;
    @JoinColumn(name = "ID_category", referencedColumnName = "ID")
    @ManyToOne
    private KategoriiRaskhodov iDcategory;

    public PodkategoriiRaskhodov() {
    }

    public PodkategoriiRaskhodov(Integer iDsubcategory) {
        this.iDsubcategory = iDsubcategory;
    }

    public PodkategoriiRaskhodov(Integer iDsubcategory, String nameSubcategory) {
        this.iDsubcategory = iDsubcategory;
        this.nameSubcategory = nameSubcategory;
    }

    public Integer getIDsubcategory() {
        return iDsubcategory;
    }

    public void setIDsubcategory(Integer iDsubcategory) {
        Integer oldIDsubcategory = this.iDsubcategory;
        this.iDsubcategory = iDsubcategory;
        changeSupport.firePropertyChange("IDsubcategory", oldIDsubcategory, iDsubcategory);
    }

    public String getNameSubcategory() {
        return nameSubcategory;
    }

    public void setNameSubcategory(String nameSubcategory) {
        String oldNameSubcategory = this.nameSubcategory;
        this.nameSubcategory = nameSubcategory;
        changeSupport.firePropertyChange("nameSubcategory", oldNameSubcategory, nameSubcategory);
    }

    public Collection<Raskhody> getRaskhodyCollection() {
        return raskhodyCollection;
    }

    public void setRaskhodyCollection(Collection<Raskhody> raskhodyCollection) {
        this.raskhodyCollection = raskhodyCollection;
    }

    public KategoriiRaskhodov getIDcategory() {
        return iDcategory;
    }

    public void setIDcategory(KategoriiRaskhodov iDcategory) {
        KategoriiRaskhodov oldIDcategory = this.iDcategory;
        this.iDcategory = iDcategory;
        changeSupport.firePropertyChange("IDcategory", oldIDcategory, iDcategory);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDsubcategory != null ? iDsubcategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PodkategoriiRaskhodov)) {
            return false;
        }
        PodkategoriiRaskhodov other = (PodkategoriiRaskhodov) object;
        if ((this.iDsubcategory == null && other.iDsubcategory != null) || (this.iDsubcategory != null && !this.iDsubcategory.equals(other.iDsubcategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NameSubcategory";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
