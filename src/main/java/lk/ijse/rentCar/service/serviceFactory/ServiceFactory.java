package lk.ijse.rentCar.service.serviceFactory;

import lk.ijse.rentCar.service.serviceImpl.CarServiceImpl;
import lk.ijse.rentCar.service.serviceImpl.CatergoryServiceImpl;
import lk.ijse.rentCar.service.serviceImpl.CustomServiceImpl;
import lk.ijse.rentCar.service.serviceImpl.RentServiceImpl;

public class ServiceFactory {
    public static <T> T getFact(FactoryType type) {
        switch (type) {
            case CUSTOMER:
                return (T) new CustomServiceImpl();
            case CARS:
                return (T) new CarServiceImpl();
            case CATERGORY:
                return (T) new CatergoryServiceImpl();
            case RENT:
                return (T) new RentServiceImpl();
            default:
                return null;
        }
    }
}
