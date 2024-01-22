package lk.ijse.rentCar.dao.customDaoImpl;


import lk.ijse.rentCar.dao.customDao.CategoryDao;
import lk.ijse.rentCar.entity.CategoryEntity;
import lk.ijse.rentCar.util.HibernetUtill;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CatergoryDaoImpl implements CategoryDao {

    @Override
    public void save(CategoryEntity categoryEntity) {
        try (Session session = HibernetUtill.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(categoryEntity);
            transaction.commit();
        }
    }

    @Override
    public void update(CategoryEntity categoryEntity) {
        try (Session session = HibernetUtill.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(categoryEntity);
            transaction.commit();
        }
    }

    @Override
    public void delete(CategoryEntity categoryEntity) {
        try (Session session = HibernetUtill.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(categoryEntity);
            transaction.commit();
        }
    }

    @Override
    public CategoryEntity search(String id) {
        try (Session session = HibernetUtill.getSessionFactory().openSession()) {
            CategoryEntity categoryEntity = session.get(CategoryEntity.class, id);
            return categoryEntity;
        }
    }

    @Override
    public List<CategoryEntity> getll() {
        try (Session session = HibernetUtill.getSessionFactory().openSession()) {
            Query query = session.createQuery("from CategoryEntity");
            List<CategoryEntity> list = query.list();
            return list;
        }
    }
}
