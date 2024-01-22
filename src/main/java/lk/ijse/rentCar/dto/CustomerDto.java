package lk.ijse.rentCar.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lk.ijse.rentCar.entity.RentEntity;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerDto {
    private String id;
    private String username;
    private String fName;
    private String lName;
    private String street;
    private String city;
    private String district;
    private String zip;
    private String email;
    private Long contact;
    @ToString.Exclude
    List<RentDto> rentDtosList;
}
