package lk.ijse.rentCar.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.rentCar.dto.CategoryDto;
import lk.ijse.rentCar.service.custom.CategoryService;
import lk.ijse.rentCar.service.serviceFactory.FactoryType;
import lk.ijse.rentCar.service.serviceFactory.ServiceFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CarCatogeryFormController implements Initializable {
    private final CategoryService categoryService = ServiceFactory.getFact(FactoryType.CATERGORY);
    public TextField txtCatId;
    public TextField txtCatName;

    public TableView tblCategory;
    public TableColumn colId;
    public TableColumn colName;

    public void btnCarCatSearchOnAction(ActionEvent actionEvent) {

        txtCatIdSearchOnAction(actionEvent);
    }

    public void btnCarCatSaveOnAction(ActionEvent actionEvent) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(txtCatId.getText());
        categoryDto.setName(txtCatName.getText());
        try {
            categoryService.saveCategory(categoryDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Category successfully saved!").show();
            loadCategoryTable();
            clear();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
    }

    public void btnCarCatUpdateOnAction(ActionEvent actionEvent) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(txtCatId.getText());
        categoryDto.setName(txtCatName.getText());
        try {
            categoryService.updateCategory(categoryDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Category successfully updated!").show();
            loadCategoryTable();
            clear();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
    }

    public void btnCarCatDeleteOnAction(ActionEvent actionEvent) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(txtCatId.getText());
        try {
            categoryService.delete(categoryDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Category successfully deleted!").show();
            loadCategoryTable();
            clear();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
    }

    public void txtCatIdSearchOnAction(ActionEvent actionEvent) {
        CategoryDto categoryDto = categoryService.searchCategory(txtCatId.getText());
        if (categoryDto != null) {
            new Alert(Alert.AlertType.CONFIRMATION, "Category found!").show();
            txtCatId.setText(categoryDto.getId());
            txtCatName.setText(categoryDto.getName());
        } else {
            new Alert(Alert.AlertType.ERROR, "No Category found!").show();
        }
    }

    public void clear() {
        txtCatId.setText("");
        txtCatName.setText("");
    }

    public void btnCarCatClearOnAction(ActionEvent actionEvent) {
        clear();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        loadCategoryTable();
    }

    private void loadCategoryTable() {
        tblCategory.setItems(FXCollections.observableArrayList(categoryService.getAll()));
    }
}
