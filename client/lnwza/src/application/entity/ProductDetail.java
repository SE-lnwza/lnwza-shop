package application.entity;

import java.awt.Color;
import javax.persistence.*;

/**
 *
 * @author EARTHPYY
 */
@Entity
public class ProductDetail {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String colorName;
    private String color;
    private Integer quantity;
    
    public ProductDetail() {
        
    }

    public ProductDetail(String colorName, String color, Integer quantity) {
        this.colorName = colorName;
        this.color = color;
        this.quantity = quantity;
    }
    
    public ProductDetail(String colorName, String color) {
        this(colorName, color, 0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public String getColor() {
        return color;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
        if (!(object instanceof ProductDetail)) {
            return false;
        }
        ProductDetail other = (ProductDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "application.entity.ProductColor[ id=" + id + " ]";
    }
    
}
