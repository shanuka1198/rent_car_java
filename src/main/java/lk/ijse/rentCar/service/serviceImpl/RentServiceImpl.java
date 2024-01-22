package lk.ijse.rentCar.service.serviceImpl;

import lk.ijse.rentCar.dao.customDao.CarDao;
import lk.ijse.rentCar.dao.customDao.CustomDao;
import lk.ijse.rentCar.dao.customDao.RentDao;
import lk.ijse.rentCar.dao.daoFactory.DaoType;
import lk.ijse.rentCar.dao.daoFactory.FactoryDao;
import lk.ijse.rentCar.dto.CarDto;
import lk.ijse.rentCar.dto.CustomerDto;
import lk.ijse.rentCar.dto.RentDto;
import lk.ijse.rentCar.entity.CarsEntity;
import lk.ijse.rentCar.entity.RentEntity;
import lk.ijse.rentCar.service.custom.RentService;

import java.util.ArrayList;
import java.util.List;

public class RentServiceImpl implements RentService {
    private final RentDao rentDao = FactoryDao.getDao(DaoType.RENT);
    private final CustomDao customDao = FactoryDao.getDao(DaoType.CUSTOMER);
    private final CarDao carDao = FactoryDao.getDao(DaoType.CARS);

    @Override
    public void save(RentDto rentDto) {
        CarsEntity searchedCar = carDao.search(rentDto.getCarDto().getCarId());
        RentEntity rentEntity = new RentEntity(
                rentDto.getRentId(),
                rentDto.getStartDate(),
                rentDto.getEndDate(),
                rentDto.getPayment(),
                rentDto.getTotal(),
                rentDto.getStatus(),
                customDao.search(rentDto.getCustomerDto().getId()),
                searchedCar
        );
        rentDao.save(rentEntity);
    }

    @Override
    public RentDto search(String rentId) {
        return null;
    }

    @Override
    public List<RentDto> getAllRent() {
        List<RentEntity> getll = rentDao.getll();
        if (getll != null) {
            return getll.stream().map(
                    rentEntity -> new RentDto(
                                    rentEntity.getRentId(),
                                    rentEntity.getStartDate(),
                                    rentEntity.getEndDate(),
                                    rentEntity.getPayment(),
                                    rentEntity.getTotal(),
                                    rentEntity.getStatus(),
                                    new CustomerDto(
                                            rentEntity.getCustomerEntity().getId(),
                                            rentEntity.getCustomerEntity().getUsername(),
                                            rentEntity.getCustomerEntity().getFName(),
                                            rentEntity.getCustomerEntity().getLName(),
                                            rentEntity.getCustomerEntity().getStreet(),
                                            rentEntity.getCustomerEntity().getCity(),
                                            rentEntity.getCustomerEntity().getDistrict(),
                                            rentEntity.getCustomerEntity().getZip(),
                                            rentEntity.getCustomerEntity().getEmail(),
                                            rentEntity.getCustomerEntity().getContact(),
                                            null
                                    ),
                                    new CarDto(
                                           null,
                                            rentEntity.getCarsEntity().getCarId(),
                                            rentEntity.getCarsEntity().getVehicleNumber(),
                                            rentEntity.getCarsEntity().getCarBrand(),
                                            rentEntity.getCarsEntity().getCarModel(),
                                            rentEntity.getCarsEntity().getCarYear(),
                                            rentEntity.getCarsEntity().getCarRate(),
                                            rentEntity.getCarsEntity().getIsCarAvailable()
                                    )
                            )
            ).toList();
        }
        return new ArrayList<>();
    }

    @Override
    public void updateRentStatusByRentID(String rentID) {
        rentDao.updateRentStatusByRentID(rentID);
    }

}





