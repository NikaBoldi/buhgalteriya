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
import javax.persistence.CascadeType;
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
@Table(name = "schet")
@NamedQueries({
    @NamedQuery(name = "Schet.findAll", query = "SELECT s FROM Schet s"),
    @NamedQuery(name = "Schet.findById", query = "SELECT s FROM Schet s WHERE s.id = :id"),
    @NamedQuery(name = "Schet.findByName", query = "SELECT s FROM Schet s WHERE s.name = :name"),
    @NamedQuery(name = "Schet.findBySuma", query = "SELECT s FROM Schet s WHERE s.suma = :suma")})
public class Schet implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "suma")
    private double suma;
    @JoinColumn(name = "tipvalut", referencedColumnName = "ID")
    @ManyToOne
    private TipValyuty tipvalut;
    @JoinColumn(name = "accountholder", referencedColumnName = "ID")
    @ManyToOne
    private Vladeltsy accountholder;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schet")
    private Collection<Dokhody> dokhodyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "schet")
    private Collection<Raskhody> raskhodyCollection;

    public Schet() {
    }

    public Schet(Integer id) {
        this.id = id;
    }

    public Schet(Integer id, double suma) {
        this.id = id;
        this.suma = suma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        changeSupport.firePropertyChange("name", oldName, name);
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        double oldSuma = this.suma;
        this.suma = suma;
        changeSupport.firePropertyChange("suma", oldSuma, suma);
    }

    public TipValyuty getTipvalut() {
        return tipvalut;
    }

    public void setTipvalut(TipValyuty tipvalut) {
        TipValyuty oldTipvalut = this.tipvalut;
        this.tipvalut = tipvalut;
        changeSupport.firePropertyChange("tipvalut", oldTipvalut, tipvalut);
    }

    public Vladeltsy getAccountholder() {
        return accountholder;
    }

    public void setAccountholder(Vladeltsy accountholder) {
        Vladeltsy oldAccountholder = this.accountholder;
        this.accountholder = accountholder;
        changeSupport.firePropertyChange("accountholder", oldAccountholder, accountholder);
    }

    public Collection<Dokhody> getDokhodyCollection() {
        return dokhodyCollection;
    }

    public void setDokhodyCollection(Collection<Dokhody> dokhodyCollection) {
        this.dokhodyCollection = dokhodyCollection;
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
        if (!(object instanceof Schet)) {
            return false;
        }
        Schet other = (Schet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
