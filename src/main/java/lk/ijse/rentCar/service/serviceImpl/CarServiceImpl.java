package lk.ijse.rentCar.service.serviceImpl;

import lk.ijse.rentCar.dao.customDao.CarDao;
import lk.ijse.rentCar.dao.customDao.CategoryDao;
import lk.ijse.rentCar.dao.daoFactory.DaoType;
import lk.ijse.rentCar.dao.daoFactory.FactoryDao;
import lk.ijse.rentCar.dto.CarDto;
import lk.ijse.rentCar.dto.CategoryDto;
import lk.ijse.rentCar.entity.CarsEntity;
import lk.ijse.rentCar.entity.CategoryEntity;
import lk.ijse.rentCar.service.custom.CarService;

import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {
    private final CarDao carDao = FactoryDao.getDao(DaoType.CARS);
private final CategoryDao categoryDao = FactoryDao.getDao(DaoType.CATEGORY);
    @Override
    public void saveCars(CarDto carDto) {
        CarsEntity carsEntity = new CarsEntity();
        carsEntity.setCarId(carDto.getCarId());
        carsEntity.setVehicleNumber(carDto.getVehicleNumber());
        carsEntity.setCarBrand(carDto.getCarBrand());
        carsEntity.setCarModel(carDto.getCarModel());
        carsEntity.setCarYear(carDto.getCarYear());
        carsEntity.setCarRate(carDto.getCarRate());
        carsEntity.setIsCarAvailable(carDto.getIsCarAvailable());
        carsEntity.setCategory(
                categoryDao.search(carDto.getCategoryDto().getId())
        );
        carDao.save(carsEntity);
    }

    @Override
    public List<CarDto> getAll() {
        List<CarsEntity> all = carDao.getll();
        return all != null ? all.stream().map(this::toCarDto).collect(Collectors.toList()) : null;
    }

    private CarDto toCarDto(CarsEntity carsEntity) {
        return new CarDto(
                new CategoryDto(
                        carsEntity.getCategory().getId(),
                        carsEntity.getCategory().getName()),
                carsEntity.getCarId(),
                carsEntity.getVehicleNumber(),
                carsEntity.getCarBrand(),
                carsEntity.getCarModel(),
                carsEntity.getCarYear(),
                carsEntity.getCarRate(),
                carsEntity.getIsCarAvailable()
        );
    }

    @Override
    public void updateCars(CarDto carDto) {
        CarsEntity carsEntity=new CarsEntity();

        carsEntity.setCarId(carDto.getCarId());
        carsEntity.setVehicleNumber(carDto.getVehicleNumber());
        carsEntity.setCarBrand(carDto.getCarBrand());
        carsEntity.setCarModel(carDto.getCarModel());
        carsEntity.setCarYear(carDto.getCarYear());
        carsEntity.setCarRate(carDto.getCarRate());
        carsEntity.setIsCarAvailable(carDto.getIsCarAvailable());
        carsEntity.setCategory(
                categoryDao.search(carDto.getCategoryDto().getId())
        );
        carDao.update(carsEntity);
    }

    @Override
    public CarDto searchCar(String carId) {
        CarsEntity carsEntity = carDao.search(carId);
        return carsEntity != null ? toCarDto(carsEntity) : null;
    }

    @Override
    public void delete(CarDto carDto) {
        CarsEntity carsEntity=new CarsEntity();
        carsEntity.setCarId(carDto.getCarId());

        carDao.delete(carsEntity);
    }

    private CarsEntity toCarEntity(CarDto carDto) {
        return new CarsEntity(
                new CategoryEntity(
                        carDto.getCategoryDto().getId(),
                        carDto.getCategoryDto().getName()),
                carDto.getCarId(),
                carDto.getVehicleNumber(),
                carDto.getCarBrand(),
                carDto.getCarModel(),
                carDto.getCarYear(),
                carDto.getCarRate(),
                carDto.getIsCarAvailable(),
                null
        );

    }
}
