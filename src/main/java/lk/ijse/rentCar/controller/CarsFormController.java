package lk.ijse.rentCar.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.rentCar.dto.CarDto;
import lk.ijse.rentCar.dto.CategoryDto;
import lk.ijse.rentCar.service.custom.CarService;
import lk.ijse.rentCar.service.custom.CategoryService;
import lk.ijse.rentCar.service.serviceFactory.FactoryType;
import lk.ijse.rentCar.service.serviceFactory.ServiceFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CarsFormController implements Initializable {
    private final CarService carService = ServiceFactory.getFact(FactoryType.CARS);
    private final CategoryService categoryService = ServiceFactory.getFact(FactoryType.CATERGORY);
    public TextField txtCarId;
    public TextField txtVehicleNum;
    public TextField txtBrand;
    public TextField txtModel;
    public TextField txtYear;
    public TextField txtRate;
    public TableView tblCars;
    public TableColumn colId;
    public TableColumn colNumber;
    public TableColumn colBrand;
    public TableColumn colModel;
    public TableColumn colYear;
    public TableColumn colRate;
    public ComboBox cmbCategory;
    public ToggleGroup availability;
    @FXML
    private RadioButton rBtnAvailable;
    @FXML
    private RadioButton rBtnNotAvailable;

    public void btnCarsSaveOnAction(ActionEvent actionEvent) {
        try{
            CarDto carDto = new CarDto();
            carDto.setCarId(txtCarId.getText());
            carDto.setVehicleNumber(txtVehicleNum.getText());
            carDto.setCarBrand(txtBrand.getText());
            carDto.setCarModel(txtModel.getText());
            carDto.setCarYear(Long.parseLong(txtYear.getText()));
            carDto.setCarRate(Long.parseLong(txtRate.getText()));
            carDto.setIsCarAvailable(rBtnAvailable.isSelected());
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(cmbCategory.getSelectionModel().getSelectedItem().toString());
            carDto.setCategoryDto(categoryDto);
            carService.saveCars(carDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Car saved successfully!").show();
            loadCarTable();
            clear();
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }
    }

    public void btnCarClearOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void clear() {
        txtCarId.setText("");
        txtVehicleNum.setText("");
        txtBrand.setText("");
        txtModel.setText("");
        txtYear.setText("");
        txtRate.setText("");
    }

    public void btnCarsUpdateOnAction(ActionEvent actionEvent) {
        try{
            CarDto carDto=new CarDto();
            carDto.setCarId(txtCarId.getText());
            carDto.setVehicleNumber(txtVehicleNum.getText());
            carDto.setCarBrand(txtBrand.getText());
            carDto.setCarModel(txtModel.getText());
            carDto.setCarYear(Long.parseLong(txtYear.getText()));
            carDto.setCarRate(Long.parseLong(txtRate.getText()));
            carDto.setIsCarAvailable(rBtnAvailable.isSelected());
            CategoryDto categoryDto=new CategoryDto();
           categoryDto.setId(cmbCategory.getSelectionModel().getSelectedItem().toString());
           carDto.setCategoryDto(categoryDto);

            carService.updateCars(carDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Update Car").show();
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
}
    public void btnTxtCatIdOnAction(ActionEvent actionEvent) {
        CarDto carDto=carService.searchCar(txtCarId.getText());
        if(carDto!=null){
            new Alert(Alert.AlertType.CONFIRMATION,"Car Found").show();
            txtCarId.setText(carDto.getCarId());
            txtVehicleNum.setText(carDto.getVehicleNumber());
            txtBrand.setText(carDto.getCarBrand());
            txtModel.setText(carDto.getCarModel());
            txtYear.setText(String.valueOf(carDto.getCarYear()));
            txtRate.setText(String.valueOf(carDto.getCarRate()));

        }else {
            new Alert(Alert.AlertType.ERROR,"No Car Found").show();
        }
    }

    public void btnCarDeletOnAction(ActionEvent actionEvent) {
        try{
            String idText = txtCarId.getText();
            CarDto carDto = new CarDto();
            carDto.setCarId(idText);
            carService.delete(carDto);
            new Alert(Alert.AlertType.CONFIRMATION,"Car deleted successfully!").show();
            loadCarTable();
            clear();
        }catch (RuntimeException e){
            new Alert(Alert.AlertType.ERROR,"Error : "+e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("carId"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("vehicleNumber"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("carBrand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("carModel"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("carYear"));
        colRate.setCellValueFactory(new PropertyValueFactory<>("carRate"));
        loadCarTable();
        loadCarCategories();
    }

    private void loadCarCategories() {
        List<CategoryDto> all = categoryService.getAll();
        ObservableList<String> observableList = FXCollections.observableArrayList();
        all.forEach(categoryDto -> {
            observableList.add(categoryDto.getId());
        });
        cmbCategory.setItems(observableList);
    }

    private void loadCarTable() {
        tblCars.setItems(FXCollections.observableArrayList(carService.getAll()));
    }
}





