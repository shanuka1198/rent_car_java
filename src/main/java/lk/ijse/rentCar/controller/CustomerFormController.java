package lk.ijse.rentCar.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.rentCar.dto.CustomerDto;
import lk.ijse.rentCar.service.custom.CustomService;
import lk.ijse.rentCar.service.serviceFactory.FactoryType;
import lk.ijse.rentCar.service.serviceFactory.ServiceFactory;

import java.net.URL;
import java.util.ResourceBundle;


public class CustomerFormController implements Initializable {
    private final CustomService customService = ServiceFactory.getFact(FactoryType.CUSTOMER);
    public TextField txtCustId;
    public TextField txtUserName;
    public TextField txtFName;
    public TextField txtLName;
    public TextField txtStreet;
    public TextField txtCity;
    public TextField txtDistrict;
    public TextField txtPostCode;
    public TextField txtEmail;
    public TextField txtContact;
    public TableView custTable;
    @FXML
    private TableColumn<?, ?> colCity;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDistrict;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colFname;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colLname;

    @FXML
    private TableColumn<?, ?> colStreet;

    @FXML
    private TableColumn<?, ?> colUsername;

    @FXML
    private TableColumn<?, ?> colZip;

    public void btnCustIdSearchOnAction(ActionEvent actionEvent) {
        btnTxtCustIdOnAction(actionEvent);
    }

    public void btnCustSaveOnAction(ActionEvent actionEvent) {
        try {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(txtCustId.getText());
            customerDto.setUsername(txtUserName.getText());
            customerDto.setFName(txtFName.getText());
            customerDto.setLName(txtLName.getText());
            customerDto.setStreet(txtStreet.getText());
            customerDto.setCity(txtCity.getText());
            customerDto.setDistrict(txtDistrict.getText());
            customerDto.setZip(txtPostCode.getText());
            customerDto.setEmail(txtEmail.getText());
            customerDto.setContact(Long.parseLong(txtContact.getText()));
            customService.save(customerDto);
            clear();
            CustomerTableLoad();
            new Alert(Alert.AlertType.CONFIRMATION, "Customer saved successfully").show();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }

    }

    public void clear() {
        txtCustId.setText("");
        txtUserName.setText("");
        txtFName.setText("");
        txtLName.setText("");
        txtStreet.setText("");
        txtCity.setText("");
        txtDistrict.setText("");
        txtPostCode.setText("");
        txtEmail.setText("");
        txtContact.setText("");
    }

    public void btnCustUpdateOnAction(ActionEvent actionEvent) {
        try {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(txtCustId.getText());
            customerDto.setUsername(txtUserName.getText());
            customerDto.setFName(txtFName.getText());
            customerDto.setLName(txtLName.getText());
            customerDto.setStreet(txtStreet.getText());
            customerDto.setCity(txtCity.getText());
            customerDto.setDistrict(txtDistrict.getText());
            customerDto.setZip(txtPostCode.getText());
            customerDto.setEmail(txtEmail.getText());
            customerDto.setContact(Long.parseLong(txtContact.getText()));
            customService.update(customerDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Customer updated successfully").show();
            clear();
            CustomerTableLoad();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
    }

    public void btnCustDelOnAction(ActionEvent actionEvent) {
        try {
            String text = txtCustId.getText();
            CustomerDto customerDto = new CustomerDto();
            customerDto.setId(text);
            customService.delete(customerDto);
            new Alert(Alert.AlertType.CONFIRMATION, "Customer deleted successfully").show();
            clear();
            CustomerTableLoad();
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }
    }


    public void btnTxtCustIdOnAction(ActionEvent actionEvent) {
        try {
            String text = txtCustId.getText();
            CustomerDto customerDto = customService.search(text);
            if (customerDto != null) {
                txtCustId.setText(customerDto.getId());
                txtUserName.setText(customerDto.getUsername());
                txtFName.setText(customerDto.getFName());
                txtLName.setText(customerDto.getLName());
                txtStreet.setText(customerDto.getStreet());
                txtCity.setText(customerDto.getCity());
                txtDistrict.setText(customerDto.getDistrict());
                txtPostCode.setText(customerDto.getZip());
                txtEmail.setText(customerDto.getEmail());
                txtContact.setText(String.valueOf(customerDto.getContact()));
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not found").show();
            }
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, "Error : " + e.getMessage()).show();
        }

    }

    public void btnCustIClearOnAction(ActionEvent actionEvent) {
        clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colFname.setCellValueFactory(new PropertyValueFactory<>("fName"));
        colLname.setCellValueFactory(new PropertyValueFactory<>("lName"));
        colStreet.setCellValueFactory(new PropertyValueFactory<>("street"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colDistrict.setCellValueFactory(new PropertyValueFactory<>("district"));
        colZip.setCellValueFactory(new PropertyValueFactory<>("zip"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

        CustomerTableLoad();
    }

    private void CustomerTableLoad() {
        custTable.setItems(FXCollections.observableArrayList(customService.getAllCustomer()));
    }
}
