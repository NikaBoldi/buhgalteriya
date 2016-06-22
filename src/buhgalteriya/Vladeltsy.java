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
@Table(name = "vladeltsy")
@NamedQueries({
    @NamedQuery(name = "Vladeltsy.findAll", query = "SELECT v FROM Vladeltsy v"),
    @NamedQuery(name = "Vladeltsy.findById", query = "SELECT v FROM Vladeltsy v WHERE v.id = :id"),
    @NamedQuery(name = "Vladeltsy.findByFirstName", query = "SELECT v FROM Vladeltsy v WHERE v.firstName = :firstName"),
    //@NamedQuery(name = "Vladeltsy.findByLastName", query = "SELECT v FROM Vladeltsy v WHERE v.lastName = :lastName"),
    //@NamedQuery(name = "Vladeltsy.findByFatherName", query = "SELECT v FROM Vladeltsy v WHERE v.fatherName = :fatherName"),
    @NamedQuery(name = "Vladeltsy.findByPassword", query = "SELECT v FROM Vladeltsy v WHERE v.password = :password")})
public class Vladeltsy implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "FirstName")
    private String firstName;
    //@Column(name = "LastName")
    //private String lastName;
    //@Column(name = "FatherName")
   // private String fatherName;
    @Basic(optional = false)
    @Column(name = "password")
    private int password;
    @OneToMany(mappedBy = "accountholder")
    private Collection<Schet> schetCollection;

    public Vladeltsy() {
    }

    public Vladeltsy(Integer id) {
        this.id = id;
    }

    public Vladeltsy(Integer id, int password) {
        this.id = id;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        String oldFirstName = this.firstName;
        this.firstName = firstName;
        changeSupport.firePropertyChange("firstName", oldFirstName, firstName);
    }

    /*public String getLastName() {
        return lastName;
    }*/

    /*public void setLastName(String lastName) {
        String oldLastName = this.lastName;
        this.lastName = lastName;
        changeSupport.firePropertyChange("lastName", oldLastName, lastName);
    }*/

    /*public String getFatherName() {
        return fatherName;
    }*/

    /*public void setFatherName(String fatherName) {
        String oldFatherName = this.fatherName;
        this.fatherName = fatherName;
        changeSupport.firePropertyChange("fatherName", oldFatherName, fatherName);
    }*/

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        int oldPassword = this.password;
        this.password = password;
        changeSupport.firePropertyChange("password", oldPassword, password);
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
        if (!(object instanceof Vladeltsy)) {
            return false;
        }
        Vladeltsy other = (Vladeltsy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return firstName;
        //return firstName+" "+lastName+" "+fatherName;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
