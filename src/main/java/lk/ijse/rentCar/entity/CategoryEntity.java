package lk.ijse.rentCar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CategoryEntity {
    @OneToMany(mappedBy = "category",targetEntity = CarsEntity.class,cascade = CascadeType.REMOVE)
    List<CarsEntity> carsList;
    @Id
    private String id;
    @Column(nullable = false)
    private String name;

    public CategoryEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
