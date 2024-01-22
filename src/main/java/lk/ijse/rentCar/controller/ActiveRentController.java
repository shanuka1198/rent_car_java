package lk.ijse.rentCar.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.rentCar.dto.RentDto;
import lk.ijse.rentCar.dto.tm.RentTM;
import lk.ijse.rentCar.service.custom.RentService;
import lk.ijse.rentCar.service.serviceFactory.FactoryType;
import lk.ijse.rentCar.service.serviceFactory.ServiceFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ActiveRentController implements Initializable {
    private List<RentTM> rentTMList;
    private final RentService rentService = ServiceFactory.getFact(FactoryType.RENT);

    @FXML
    private TableColumn<?, ?> colCarId;

    @FXML
    private TableColumn<?, ?> colCustomerID;

    @FXML
    private TableColumn<?, ?> colEndingDate;

    @FXML
    private TableColumn<?, ?> colRentID;

    @FXML
    private TableColumn<?, ?> colStartingDate;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableView<RentTM> tblRent;

    @FXML
    private TextField txtCustomerID;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colRentID.setCellValueFactory(new PropertyValueFactory<>("rentId"));
        colStartingDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEndingDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCarId.setCellValueFactory(new PropertyValueFactory<>("carId"));

        loadRentTable();
    }

    private void loadRentTable() {
        tblRent.setItems(
                FXCollections.observableArrayList(
                        rentService.getAllRent().stream().map(this::toRentTM).toList())
        );
        rentTMList = tblRent.getItems();
    }

private RentTM toRentTM(RentDto rentDto){
        return new RentTM(
                rentDto.getRentId(),
                rentDto.getCarDto().getCarId(),
                rentDto.getCustomerDto().getId(),
                rentDto.getStartDate(),
                rentDto.getEndDate(),
                rentDto.getTotal(),
                rentDto.getPayment(),
                rentDto.getStatus()
        );
    }

    public void tblRentOnMouseClicked(MouseEvent mouseEvent) {
        RentTM selectedItem = tblRent.getSelectionModel().getSelectedItem();
        if(selectedItem!=null && selectedItem.getStatus().equals("pending")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to update this rent status ?");
            alert.showAndWait();
            ButtonType result = alert.getResult();
            if(result==ButtonType.OK){
                rentService.updateRentStatusByRentID(selectedItem.getRentId());
                loadRentTable();
            }
        }
    }

    public void txtCustomerIdOnKeyReleased(KeyEvent keyEvent) {
        String text = txtCustomerID.getText();
        if(text.equals("")){
            loadRentTable();
            return;
        }
        tblRent.setItems(
                FXCollections.observableArrayList(
                        rentTMList.stream().filter(
                                rentTM -> rentTM.getCustomerId().equals(text)).collect(Collectors.toList())));
    }
}
