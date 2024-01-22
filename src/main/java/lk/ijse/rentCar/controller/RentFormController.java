package lk.ijse.rentCar.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rentCar.dto.CarDto;
import lk.ijse.rentCar.dto.CustomerDto;
import lk.ijse.rentCar.dto.RentDto;
import lk.ijse.rentCar.service.custom.CarService;
import lk.ijse.rentCar.service.custom.CustomService;
import lk.ijse.rentCar.service.custom.RentService;
import lk.ijse.rentCar.service.serviceFactory.FactoryType;
import lk.ijse.rentCar.service.serviceFactory.ServiceFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
public class RentFormController implements Initializable {
    private final RentService rentService = ServiceFactory.getFact(FactoryType.RENT);
    private final CustomService customService = ServiceFactory.getFact(FactoryType.CUSTOMER);
    private final CarService carService = ServiceFactory.getFact(FactoryType.CARS);
    public TextField txtAdavancePay;
    public TextField txtRentId;
    public AnchorPane rootRent;
    public TextField txtRentRatePerDay;
    public Label lblDueAmount;
    public ComboBox comboCarId;
    public ComboBox comboCustId;
    public TableView rentTable;
    public TableColumn colRentId;
    public TableColumn colEDate;
    public TableColumn colAddPay;
    public DatePicker datePickerEndDate;
    public DatePicker datePickerStartDate;
    public TableColumn colSDate;
    public TableColumn colTotal;
    public Label lblTotal;

    public void btnAddRentOnAction(ActionEvent actionEvent) {
       try{
           String rentId = txtRentId.getText();
           String carId = comboCarId.getSelectionModel().getSelectedItem().toString();
           String custId = comboCustId.getSelectionModel().getSelectedItem().toString();
           LocalDate startDate = datePickerStartDate.getValue();
           LocalDate endDate = datePickerEndDate.getValue();
           double total = Double.parseDouble(lblTotal.getText());
           double advance = Double.parseDouble(txtAdavancePay.getText());

           CustomerDto customerDto = new CustomerDto();
           customerDto.setId(custId);

           CarDto carDto = new CarDto();
           carDto.setCarId(carId);

           RentDto rentDto = new RentDto(
                   rentId,
                   Date.valueOf(startDate),
                   Date.valueOf(endDate),
                   advance,
                   total,
                   advance < total ? "pending" : "complete",
                   customerDto,
                   carDto
           );
           rentService.save(rentDto);
           new Alert(Alert.AlertType.CONFIRMATION, "Rent Added Successfully").show();
       }catch (RuntimeException e){
           e.printStackTrace();
           new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
       }
        rentTableLoad();
    }


    public void btnPlaceOrder(ActionEvent actionEvent) {

    }

    public void txtRentIdOnActoin(ActionEvent actionEvent) {

    }

    public void btnActiveRentOnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/Active_Rent.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Active Rent");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
    }

    public void btnComboCustId(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRentId.setCellValueFactory(new PropertyValueFactory<>("rentId"));
        colSDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colAddPay.setCellValueFactory(new PropertyValueFactory<>("payment"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        rentTableLoad();
        loadCarIdsList();
        loadCustIdsList();
    }

    private void rentTableLoad() {
        List<RentDto> rentDtoList = rentService.getAllRent();
        ObservableList observableList = FXCollections.observableArrayList();
        if (rentDtoList != null) {
            rentDtoList.forEach(rentDto -> observableList.add(
                    new RentDto(
                            rentDto.getRentId(),
                            rentDto.getStartDate(),
                            rentDto.getEndDate(),
                            rentDto.getPayment(),
                            rentDto.getTotal()
                    )
            ));
            rentTable.setItems(observableList);
        }
    }

    private void loadCustIdsList() {
        List<CustomerDto> customerDtoList = customService.getAllCustomer();
        if (customerDtoList != null) {
            ObservableList<String> observableArrayList = FXCollections.observableArrayList();
            customerDtoList.forEach(customerDto -> observableArrayList.add(customerDto.getId()));
            comboCustId.setItems(observableArrayList);
        }
    }

    private void loadCarIdsList() {
        List<CarDto> carDtoList = carService.getAll();
        if (carDtoList != null) {
            ObservableList<String> observableArrayList = FXCollections.observableArrayList();
            carDtoList.stream().filter(
                    (carDto -> carDto.getIsCarAvailable())
            ).toList().forEach(
                    (carDto -> observableArrayList.add(carDto.getCarId()))
            );
            comboCarId.setItems(observableArrayList);
        }
    }

    public void cmbCarIdOnAction(ActionEvent actionEvent) {
        String carId = comboCarId.getSelectionModel().getSelectedItem().toString();
        CarDto carDto = carService.searchCar(carId);
        txtRentRatePerDay.setText(String.valueOf(carDto.getCarRate()));
    }

    public void datePickerEndDateOnAction(ActionEvent actionEvent) {
        LocalDate startDate = datePickerStartDate.getValue();
        LocalDate endDate = datePickerEndDate.getValue();
        if (startDate != null && endDate != null) {
            if (startDate.isBefore(endDate)) {
                lblTotal.setText(
                        String.valueOf(
                                Double.parseDouble(
                                        txtRentRatePerDay.getText()) * (endDate.toEpochDay() - startDate.toEpochDay())
                        )
                );
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Date").show();
            }
        }
    }

    public void advancedPaymentOnKeyReleased(KeyEvent keyEvent) {
        if (!txtAdavancePay.getText().isEmpty()) {
            double total = Double.parseDouble(lblTotal.getText());
            double advance = Double.parseDouble(txtAdavancePay.getText());
            lblDueAmount.setText(String.valueOf(total - advance));
        } else {
            lblDueAmount.setText(lblTotal.getText());
        }
    }
}
