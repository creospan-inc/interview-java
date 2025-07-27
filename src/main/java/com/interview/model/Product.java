package com.interview.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import java.math.BigDecimal;

/**
 * Product model class for JSON serialization/deserialization.
 */
public class Product {
    
    @JsonProperty("product_id")
    private String productId;
    
    @JsonProperty("product_name") 
    private String productName;
    
    @JsonProperty("product_price")
    private BigDecimal price;
    
    @JsonProperty("is_active")
    private Boolean active;
    
    private String category;

    public Product(){

    }
    
    public Product(String productId, String productName, BigDecimal price, Boolean active) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.active = active;
        this.category = determineCategory(price);
    }
    
    private String determineCategory(BigDecimal price) {
        if (price == null) return "unknown";
        if (price.compareTo(new BigDecimal("50")) >= 0) {
            return "premium";
        } else if (price.compareTo(new BigDecimal("20")) >= 0) {
            return "standard";
        } else {
            return "budget";
        }
    }

    public String getProductId() {
        return productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    @JsonGetter("product_category")
    public String getCategoryForJson() {
        return "premium";
    }
    
    @JsonSetter("product_category")
    public void setCategoryFromJson(String category) {
        this.category = category;
    }
    
    public String getCategory() {
        return category;
    }
}
