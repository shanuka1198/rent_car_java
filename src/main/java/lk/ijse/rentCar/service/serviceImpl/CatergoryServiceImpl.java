package lk.ijse.rentCar.service.serviceImpl;

import lk.ijse.rentCar.dao.customDao.CategoryDao;
import lk.ijse.rentCar.dao.daoFactory.DaoType;
import lk.ijse.rentCar.dao.daoFactory.FactoryDao;
import lk.ijse.rentCar.dto.CategoryDto;
import lk.ijse.rentCar.entity.CategoryEntity;
import lk.ijse.rentCar.service.custom.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

public class CatergoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao = FactoryDao.getDao(DaoType.CATEGORY);

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryDto.getId());
        categoryEntity.setName(categoryDto.getName());
        categoryDao.save(categoryEntity);
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryDto.getId());
        categoryEntity.setName(categoryDto.getName());
        categoryDao.update(categoryEntity);
    }

    @Override
    public CategoryDto searchCategory(String catagerId) {
        CategoryEntity categoryEntity = categoryDao.search(catagerId);
        if (categoryEntity != null) {
            return new CategoryDto(
                    categoryEntity.getId(),
                    categoryEntity.getName()
            );
        }
        return null;
    }


    @Override
    public void delete(CategoryDto categoryDto) {
        CategoryEntity search = categoryDao.search(categoryDto.getId());
        categoryDao.delete(search);
    }

    @Override
    public List<CategoryDto> getAll() {
        return categoryDao.getll()
                .stream().map(
                        element -> new CategoryDto(
                                element.getId(),
                                element.getName())
                ).collect(Collectors.toList());
    }

}
