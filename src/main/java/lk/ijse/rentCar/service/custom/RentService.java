package lk.ijse.rentCar.service.custom;

import lk.ijse.rentCar.dto.CustomerDto;
import lk.ijse.rentCar.dto.RentDto;

import java.util.List;

public interface RentService {

    void save(RentDto rentDto);
    RentDto search(String rentId);
    List<RentDto>getAllRent();
    void updateRentStatusByRentID(String rentID);
}
