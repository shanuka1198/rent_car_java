package lk.ijse.rentCar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentDto {
    private String rentId;
    private Date startDate;
    private Date endDate;
    private Double payment;
    private Double total;
    private String status;//pending,complete
    private CustomerDto customerDto;
    private CarDto carDto;

    public RentDto(String rentId, Date startDate, Date endDate, Double payment, Double total) {
        this.rentId = rentId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.payment = payment;
        this.total = total;
    }
}
