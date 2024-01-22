package lk.ijse.rentCar.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CustomerEntity  {
    @Id
    private String id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String fName;
    @Column(nullable = false)
    private String lName;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String district;
    @Column(nullable = false)
    private String zip;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Long contact;
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customerEntity", fetch = FetchType.LAZY)
    @ToString.Exclude
    List<RentEntity> rentEntityList = new ArrayList<>();
}
