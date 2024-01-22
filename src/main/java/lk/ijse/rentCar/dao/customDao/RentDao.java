package lk.ijse.rentCar.dao.customDao;

import lk.ijse.rentCar.dao.CrudDao;
import lk.ijse.rentCar.entity.RentEntity;

public interface RentDao extends CrudDao<RentEntity,String> {
    void updateRentStatusByRentID(String rentID);
}
