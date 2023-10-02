package bg.caroffershub.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private LocalDateTime created;

    private LocalDateTime modified;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<ModelEntity> models;


    public BrandEntity() {
        super();
        this.models = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public BrandEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public BrandEntity setModified(LocalDateTime modified) {
        this.modified = modified;
        return this;
    }

    public List<ModelEntity> getModels() {
        return models;
    }

    public BrandEntity setModels(List<ModelEntity> models) {
        this.models = models;
        return this;
    }

    @Override
    public String toString() {
        return "BrandEntity{" +
                "name='" + name + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                ", models=" + models +
                '}';
    }
}
