package lk.ijse.rentCar.util;


import lk.ijse.rentCar.entity.CarsEntity;
import lk.ijse.rentCar.entity.CategoryEntity;
import lk.ijse.rentCar.entity.CustomerEntity;
import lk.ijse.rentCar.entity.RentEntity;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernetUtill {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().loadProperties("/cfg/application.properties").build();

        Metadata metadata = new MetadataSources(standardRegistry).addAnnotatedClass(CustomerEntity.class).addAnnotatedClass(CarsEntity.class).addAnnotatedClass(CategoryEntity.class).addAnnotatedClass(RentEntity.class).getMetadataBuilder().applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE).build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
