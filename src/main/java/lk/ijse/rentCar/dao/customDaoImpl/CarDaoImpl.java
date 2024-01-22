package lk.ijse.rentCar.dao.customDaoImpl;


import lk.ijse.rentCar.dao.customDao.CarDao;
import lk.ijse.rentCar.entity.CarsEntity;
import lk.ijse.rentCar.util.HibernetUtill;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarDaoImpl implements CarDao {

    @Override
    public void save(CarsEntity carsEntity) {
        try (Session session = HibernetUtill.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(carsEntity);
            transaction.commit();
        }
    }

    @Override
    public void update(CarsEntity carsEntity) {
        try (Session session = HibernetUtill.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(carsEntity);
            transaction.commit();
        }
    }

    @Override
    public void delete(CarsEntity carsEntity) {
        try (Session session = HibernetUtill.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(carsEntity);
            transaction.commit();
        }
    }

    @Override
    public CarsEntity search(String id) {
        try (Session session = HibernetUtill.getSessionFactory().openSession()) {
            CarsEntity carsEntity = session.get(CarsEntity.class, id);
            return carsEntity;
        }
    }

    @Override
    public List<CarsEntity> getll() {
        try (Session session = HibernetUtill.getSessionFactory().openSession()) {
            return  session.createQuery("from CarsEntity").list();
        }
    }
}
