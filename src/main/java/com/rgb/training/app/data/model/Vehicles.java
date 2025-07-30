package com.rgb.training.app.data.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 *
 * @author marccunillera
 */
@Entity
@Table(name = "vehicles", schema = "public")
@XmlType(name = "Vehicles")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Vehicles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    
    @JoinColumn(name = "brand_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private MarcaVehicle brandId;
    
    @JoinColumn(name = "model_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ModelVehicles modelId;

    public Vehicles() {
    }

    public Vehicles(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MarcaVehicle getBrandId() {
        return brandId;
    }

    public void setBrandId(MarcaVehicle brandId) {
        this.brandId = brandId;
    }

    public ModelVehicles getModelId() {
        return modelId;
    }

    public void setModelId(ModelVehicles modelId) {
        this.modelId = modelId;
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
        if (!(object instanceof Vehicles)) {
            return false;
        }
        Vehicles other = (Vehicles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "id=" + id + "BrandId=" + brandId + "ModelId=" + modelId +'}';
    }
    
}
