package lk.ijse.rentCar.service.custom;

import lk.ijse.rentCar.dto.CarDto;
import lk.ijse.rentCar.service.SupperService;

import java.util.List;

public interface CarService extends SupperService {


    void updateCars(CarDto carDto);

    CarDto searchCar(String carId);

    void delete(CarDto carDto);

    void saveCars(CarDto carDto);

    List<CarDto> getAll();
}
