package lk.ijse.rentCar.service.serviceImpl;

import lk.ijse.rentCar.dao.customDao.CustomDao;
import lk.ijse.rentCar.dao.daoFactory.DaoType;
import lk.ijse.rentCar.dao.daoFactory.FactoryDao;
import lk.ijse.rentCar.dto.CustomerDto;
import lk.ijse.rentCar.entity.CustomerEntity;
import lk.ijse.rentCar.service.custom.CustomService;

import java.util.List;
import java.util.stream.Collectors;


public class CustomServiceImpl implements CustomService {
    private final CustomDao customDao = FactoryDao.getDao(DaoType.CUSTOMER);

    @Override
    public void save(CustomerDto customerDto) {

        customDao.save(toCustomerEntity(customerDto));
    }

    @Override
    public CustomerDto search(String custId) {
        CustomerEntity customerEntity = customDao.search(custId);
        return (customerEntity != null) ? toCustomerDto(customerEntity) : null;
    }

    @Override
    public void update(CustomerDto customerDto) {

        customDao.update(toCustomerEntity(customerDto));
    }

    private CustomerEntity toCustomerEntity(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerDto.getId());
        customerEntity.setUsername(customerDto.getUsername());
        customerEntity.setFName(customerDto.getFName());
        customerEntity.setLName(customerDto.getLName());
        customerEntity.setStreet(customerDto.getStreet());
        customerEntity.setCity(customerDto.getCity());
        customerEntity.setDistrict(customerDto.getDistrict());
        customerEntity.setZip(customerDto.getZip());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setContact(customerDto.getContact());
        return customerEntity;
    }

    @Override
    public void delete(CustomerDto customerDto) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerDto.getId());
        customDao.delete(customerEntity);
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<CustomerEntity> getll = customDao.getll();
        return getll.stream().map(this::toCustomerDto).collect(Collectors.toList());
    }

    private CustomerDto toCustomerDto(CustomerEntity e) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(e.getId());
        customerDto.setUsername(e.getUsername());
        customerDto.setFName(e.getFName());
        customerDto.setLName(e.getLName());
        customerDto.setStreet(e.getStreet());
        customerDto.setCity(e.getCity());
        customerDto.setDistrict(e.getDistrict());
        customerDto.setZip(e.getZip());
        customerDto.setEmail(e.getEmail());
        customerDto.setContact(e.getContact());
        return customerDto;
    }

}
