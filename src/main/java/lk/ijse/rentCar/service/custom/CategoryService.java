package lk.ijse.rentCar.service.custom;

import lk.ijse.rentCar.dto.CategoryDto;
import lk.ijse.rentCar.service.SupperService;

import java.util.List;

public interface CategoryService extends SupperService {
    void saveCategory(CategoryDto categoryDto);

    void updateCategory(CategoryDto categoryDto);

    CategoryDto searchCategory(String catagerId);

    void delete(CategoryDto categoryDto);

    List<CategoryDto> getAll();
}
