package lk.ijse.rentCar.dao.daoFactory;

import lk.ijse.rentCar.dao.customDaoImpl.CarDaoImpl;
import lk.ijse.rentCar.dao.customDaoImpl.CatergoryDaoImpl;
import lk.ijse.rentCar.dao.customDaoImpl.CustomDaoImpl;
import lk.ijse.rentCar.dao.customDaoImpl.RentDaoImpl;

public class FactoryDao {
    public static <T>T getDao(DaoType type){
        switch (type){
            case CUSTOMER:
                return(T) new CustomDaoImpl();
            case CARS:
                return (T)new CarDaoImpl();
            case CATEGORY:
                return (T)new CatergoryDaoImpl();
            case RENT:
                return (T)new RentDaoImpl();
            default:
                return null;

        }
    }
}
