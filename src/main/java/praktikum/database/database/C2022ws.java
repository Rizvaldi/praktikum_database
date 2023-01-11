/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikum.database.database;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MSI 65 SERIES
 */
@Entity
@Table(name = "c2022ws")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "C2022ws.findAll", query = "SELECT c FROM C2022ws c"),
    @NamedQuery(name = "C2022ws.findById", query = "SELECT c FROM C2022ws c WHERE c.id = :id"),
    @NamedQuery(name = "C2022ws.findByName", query = "SELECT c FROM C2022ws c WHERE c.name = :name"),
    @NamedQuery(name = "C2022ws.findByBirthdate", query = "SELECT c FROM C2022ws c WHERE c.birthdate = :birthdate")})
public class C2022ws implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Lob
    @Column(name = "picture")
    private byte[] picture;

    public C2022ws() {
    }

    public C2022ws(Integer id) {
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
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
        if (!(object instanceof C2022ws)) {
            return false;
        }
        C2022ws other = (C2022ws) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "praktikum.database.database.C2022ws[ id=" + id + " ]";
    }
    
}
