package lk.ijse.rentCar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class RentEntity {
    @Id
    private String rentId;
    private Date startDate;
    private Date endDate;
    private Double payment;
    private Double total;
    private String status;//pending,complete

    @ManyToOne(fetch = FetchType.EAGER)
    private CustomerEntity customerEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    private CarsEntity carsEntity;
}
