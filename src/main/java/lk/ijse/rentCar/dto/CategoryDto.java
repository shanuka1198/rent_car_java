package lk.ijse.rentCar.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {
    @ToString.Exclude
    List<CarDto> carsList;
    private String id;
    private String name;
    public CategoryDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
