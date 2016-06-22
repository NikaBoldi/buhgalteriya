/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buhgalteriya;

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

/**
 *
 * @author Максим
 */
@Entity
@Table(name = "raskhody")
@NamedQueries({
    @NamedQuery(name = "Raskhody.findAll", query = "SELECT r FROM Raskhody r"),
    @NamedQuery(name = "Raskhody.findById", query = "SELECT r FROM Raskhody r WHERE r.id = :id"),
    @NamedQuery(name = "Raskhody.findByDate", query = "SELECT r FROM Raskhody r WHERE r.date = :date"),
    @NamedQuery(name = "Raskhody.findByCnt", query = "SELECT r FROM Raskhody r WHERE r.cnt = :cnt"),
    @NamedQuery(name = "Raskhody.findBySumma", query = "SELECT r FROM Raskhody r WHERE r.summa = :summa"),
    @NamedQuery(name = "Raskhody.findByPrimechanie", query = "SELECT r FROM Raskhody r WHERE r.primechanie = :primechanie")})
public class Raskhody implements Serializable {
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
    @Basic(optional = false)
    @Column(name = "cnt")
    private int cnt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "summa")
    private Double summa;
    @Column(name = "primechanie")
    private String primechanie;
    @JoinColumn(name = "units", referencedColumnName = "ID")
    @ManyToOne
    private EdenitsyIzmereniya units;
    @JoinColumn(name = "schet", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Schet schet;
    @JoinColumn(name = "podkategoriy_raskhodov", referencedColumnName = "ID_subcategory")
    @ManyToOne
    private PodkategoriiRaskhodov podkategoriyRaskhodov;

    public Raskhody() {
    }

    public Raskhody(Integer id) {
        this.id = id;
    }

    public Raskhody(Integer id, Date date, int cnt) {
        this.id = id;
        this.date = date;
        this.cnt = cnt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public Double getSumma() {
        return summa;
    }

    public void setSumma(Double summa) {
        this.summa = summa;
    }

    public String getPrimechanie() {
        return primechanie;
    }

    public void setPrimechanie(String primechanie) {
        this.primechanie = primechanie;
    }

    public EdenitsyIzmereniya getUnits() {
        return units;
    }

    public void setUnits(EdenitsyIzmereniya units) {
        this.units = units;
    }

    public Schet getSchet() {
        return schet;
    }

    public void setSchet(Schet schet) {
        this.schet = schet;
    }

    public PodkategoriiRaskhodov getPodkategoriyRaskhodov() {
        return podkategoriyRaskhodov;
    }

    public void setPodkategoriyRaskhodov(PodkategoriiRaskhodov podkategoriyRaskhodov) {
        this.podkategoriyRaskhodov = podkategoriyRaskhodov;
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
        if (!(object instanceof Raskhody)) {
            return false;
        }
        Raskhody other = (Raskhody) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "buhgalteriya.Raskhody[ id=" + id + " ]";
    }
    
}
