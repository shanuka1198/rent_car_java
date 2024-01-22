package lk.ijse.rentCar.dao.customDaoImpl;

import lk.ijse.rentCar.dao.customDao.CustomDao;
import lk.ijse.rentCar.entity.CustomerEntity;
import lk.ijse.rentCar.util.HibernetUtill;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class CustomDaoImpl implements CustomDao {

    @Override
    public void save(CustomerEntity customerEntity) {
        Session session = HibernetUtill.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(customerEntity);
        transaction.commit();
    }

    @Override
    public void update(CustomerEntity customerEntity) {
        try (Session session = HibernetUtill.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(customerEntity);
            transaction.commit();
        }
    }

    @Override
    public void delete(CustomerEntity customerEntity) {
        try (Session session = HibernetUtill.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(search(customerEntity.getId()));
            transaction.commit();
        }
    }


    @Override
    public CustomerEntity search(String id) {
        try (Session session = HibernetUtill.getSessionFactory().openSession()) {
            return session.get(CustomerEntity.class, id);
        }
    }

    @Override
    public List<CustomerEntity> getll() {
       try(Session session=HibernetUtill.getSessionFactory().openSession()){
           return session.createQuery("FROM CustomerEntity").list();
       }
    }

}
