package lk.ijse.rentCar.dao.customDaoImpl;

import lk.ijse.rentCar.dao.customDao.RentDao;
import lk.ijse.rentCar.entity.CarsEntity;
import lk.ijse.rentCar.entity.RentEntity;
import lk.ijse.rentCar.util.HibernetUtill;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RentDaoImpl implements RentDao {
    @Override
    public void save(RentEntity rentEntity) {
        try(Session session=HibernetUtill.getSessionFactory().openSession()){
            Transaction transaction=session.beginTransaction();
            session.save(rentEntity);
            session.get(CarsEntity.class,rentEntity.getCarsEntity().getCarId()).setIsCarAvailable(false);
            transaction.commit();
        }
    }


    @Override
    public void update(RentEntity rentEntity) {

    }

    @Override
    public void delete(RentEntity rentEntity) {

    }

    @Override
    public RentEntity search(String id) {
        Session session=HibernetUtill.getSessionFactory().openSession();
        Transaction transaction= session.beginTransaction();
        RentEntity rentEntity=session.get(RentEntity.class, id);
        transaction.commit();
        return rentEntity;
    }

    @Override
    public List<RentEntity> getll() {
        Session session=HibernetUtill.getSessionFactory().openSession();
        Query query=session.createQuery("FROM RentEntity ");
        List rentEntities=query.list();
        return rentEntities;
    }

    @Override
    public void updateRentStatusByRentID(String rentID) {
        try(Session session=HibernetUtill.getSessionFactory().openSession()){
            Transaction transaction=session.beginTransaction();
            Query query=session.createQuery("UPDATE RentEntity SET status='complete' WHERE rentId=:rentId");
            query.setParameter("rentId",rentID);
            query.executeUpdate();
            transaction.commit();
        }
    }
}
