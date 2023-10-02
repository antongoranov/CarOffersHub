package bg.caroffershub.model.dtos;

import bg.caroffershub.model.enums.EngineEnum;
import bg.caroffershub.model.enums.TransmissionEnum;

import java.math.BigDecimal;
import java.util.UUID;

public class OfferDetailsDTO {

    private UUID id;
    private String imageUrl;
    private Integer year;
    private String brand;
    private String model;
    private Integer mileage;
    private BigDecimal price;
    private EngineEnum engine;
    private TransmissionEnum transmission;
    private String sellerFirstName;
    private String sellerLastName;


    public OfferDetailsDTO() {
    }

    public UUID getId() {
        return id;
    }

    public OfferDetailsDTO setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public OfferDetailsDTO setSellerFirstName(String sellerFirstName) {
        this.sellerFirstName = sellerFirstName;
        return this;
    }

    public String getSellerFullName() {
        return sellerFirstName + " " + sellerLastName;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public OfferDetailsDTO setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineEnum engine) {
        this.engine = engine;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
    }

    public String getOfferHighlight() {
        return this.year + " " + this.brand + " " + this.model;
    }


}