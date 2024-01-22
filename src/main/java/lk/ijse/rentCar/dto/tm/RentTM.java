package lk.ijse.rentCar.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * Author: shan
 * Created: 12/21/23 4:17 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentTM {
    private String rentId;
    private String carId;
    private String customerId;
    private Date startDate;
    private Date endDate;
    private double total;
    private double advance;
    private String status;

}
