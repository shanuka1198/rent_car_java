package lk.ijse.rentCar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CarsEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    private CategoryEntity category;
    @Id
    private String carId;
    @Column(nullable = false)
    private String vehicleNumber;
    @Column(nullable = false)
    private String carBrand;
    @Column(nullable = false)
    private String carModel;
    @Column(nullable = false)
    private Long carYear;
    @Column(nullable = false)
    private Long carRate;

    private Boolean isCarAvailable = true;

    @OneToMany(mappedBy = "carsEntity")
    @ToString.Exclude
    private List<RentEntity> rentList;
}
