/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buhgalteriya;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Максим
 */
@Entity
@Table(name = "dokhody")
@NamedQueries({
    @NamedQuery(name = "Dokhody.findAll", query = "SELECT d FROM Dokhody d"),
    @NamedQuery(name = "Dokhody.findById", query = "SELECT d FROM Dokhody d WHERE d.id = :id"),
    @NamedQuery(name = "Dokhody.findByDate", query = "SELECT d FROM Dokhody d WHERE d.date = :date"),
    @NamedQuery(name = "Dokhody.findBySumma", query = "SELECT d FROM Dokhody d WHERE d.summa = :summa"),
    @NamedQuery(name = "Dokhody.findByPrimechanie", query = "SELECT d FROM Dokhody d WHERE d.primechanie = :primechanie")})
public class Dokhody implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "summa")
    private Double summa;
    @Column(name = "primechanie")
    private String primechanie;
    @JoinColumn(name = "schet", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Schet schet;
    @JoinColumn(name = "podkategoriy_dokhoda", referencedColumnName = "ID_subcategory")
    @ManyToOne
    private PodkategoriiDokhodov podkategoriyDokhoda;

    public Dokhody() {
    }

    public Dokhody(Integer id) {
        this.id = id;
    }

    public Dokhody(Integer id, Date date) {
        this.id = id;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        Date oldDate = this.date;
        this.date = date;
        changeSupport.firePropertyChange("date", oldDate, date);
    }

    public Double getSumma() {
        return summa;
    }

    public void setSumma(Double summa) {
        Double oldSumma = this.summa;
        this.summa = summa;
        changeSupport.firePropertyChange("summa", oldSumma, summa);
    }

    public String getPrimechanie() {
        return primechanie;
    }

    public void setPrimechanie(String primechanie) {
        String oldPrimechanie = this.primechanie;
        this.primechanie = primechanie;
        changeSupport.firePropertyChange("primechanie", oldPrimechanie, primechanie);
    }

    public Schet getSchet() {
        return schet;
    }

    public void setSchet(Schet schet) {
        Schet oldSchet = this.schet;
        this.schet = schet;
        changeSupport.firePropertyChange("schet", oldSchet, schet);
    }

    public PodkategoriiDokhodov getPodkategoriyDokhoda() {
        return podkategoriyDokhoda;
    }

    public void setPodkategoriyDokhoda(PodkategoriiDokhodov podkategoriyDokhoda) {
        PodkategoriiDokhodov oldPodkategoriyDokhoda = this.podkategoriyDokhoda;
        this.podkategoriyDokhoda = podkategoriyDokhoda;
        changeSupport.firePropertyChange("podkategoriyDokhoda", oldPodkategoriyDokhoda, podkategoriyDokhoda);
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
        if (!(object instanceof Dokhody)) {
            return false;
        }
        Dokhody other = (Dokhody) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "buhgalteriya.Dokhody[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    void getPrimechanie(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
