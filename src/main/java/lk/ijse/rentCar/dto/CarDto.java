package lk.ijse.rentCar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CarDto {
    private CategoryDto categoryDto;
    private String carId;
    private String vehicleNumber;
    private String carBrand;
    private String carModel;
    private Long carYear;
    private Long carRate;
    private Boolean isCarAvailable;
}
