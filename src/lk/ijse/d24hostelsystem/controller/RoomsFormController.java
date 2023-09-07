package lk.ijse.d24hostelsystem.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.d24hostelsystem.bo.BOFactory;
import lk.ijse.d24hostelsystem.bo.custom.RoomsBO;
import lk.ijse.d24hostelsystem.dto.RoomDTO;

import java.util.List;

public class RoomsFormController {
    public JFXTextField txtRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtRoomKeyMoney;
    public JFXTextField txtRoomQty;
    public TableColumn colTypeID;
    public TableView <RoomDTO>tblRooms;
    public TableColumn<RoomDTO, String> colQty;
    public TableColumn<RoomDTO, String> colType;
    public TableColumn<RoomDTO, String> colKeyMoney;

    RoomsBO roomsBO= (RoomsBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Rooms);
    @FXML
    void initialize() throws Exception {
        setProperties();
        loadAllRooms();
        getData();
    }

    public void addRoomVisibleOnAction(ActionEvent actionEvent) throws Exception {
        setID();
    }

    public void updateRoomVisibleOnAction(ActionEvent actionEvent) {
    }

    public void deleteRoomVisibleOnAction(ActionEvent actionEvent) {
    }

    public void saveRoomOnAction(ActionEvent actionEvent) throws Exception {
        boolean isValidate = checkValidation();
        if (isValidate){
            boolean isSaved = roomsBO.saveRoom(
                    new RoomDTO(
                            txtRoomId.getText(),
                            txtRoomType.getText(),
                            txtRoomKeyMoney.getText(),
                            Integer.parseInt(txtRoomQty.getText())
                    )
            );

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Room saved").show();
                clearFields();
                tblRooms.getItems().clear();
                loadAllRooms();

            }else{
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }

        }
    }

    public void updateRoomOnAction(ActionEvent actionEvent) throws Exception {
        boolean isValidate = checkValidation();
        if (isValidate){
            boolean isSaved = roomsBO.updateRoom(
                    new RoomDTO(
                            txtRoomId.getText(),
                            txtRoomType.getText(),
                            txtRoomKeyMoney.getText(),
                            Integer.parseInt(txtRoomQty.getText())
                    )
            );

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Customer saved").show();
                clearFields();
                tblRooms.getItems().clear();
                loadAllRooms();
            }else{
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }

        }
    }

    public void deleteRoomOnAction(ActionEvent actionEvent) throws Exception {
        boolean isValidate = checkValidation();
        if (isValidate){
            boolean isSaved = roomsBO.deleteRoom(
                    new RoomDTO(
                            txtRoomId.getText(),
                            txtRoomType.getText(),
                            txtRoomKeyMoney.getText(),
                            Integer.parseInt(txtRoomQty.getText())
                    )
            );

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Room Deleted").show();
                clearFields();
                tblRooms.getItems().clear();
                loadAllRooms();
            }else{
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }

        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
    }





    private void setProperties(){
        colTypeID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    void loadAllRooms() throws Exception{
        List<RoomDTO> roomDTOS = roomsBO.loadAll();
        ObservableList<RoomDTO> observableList= FXCollections.observableList(roomDTOS);
        tblRooms.setItems(observableList);
    }

    private boolean checkValidation(){
        String typeText = txtRoomType.getText();
        String moneyText = txtRoomKeyMoney.getText();
        String qtyText = txtRoomQty.getText();

        if (!typeText.matches("[A-Za-z0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtRoomType.requestFocus();
            return false;
        } else if (!moneyText.matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "invalid key money").show();
            txtRoomKeyMoney.requestFocus();
            return false;
        } else if (!qtyText.matches("^\\d+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty").show();
            txtRoomQty.requestFocus();
            return false;
        }else {
            return true;
        }

    }

    private void clearFields(){
        txtRoomId.setText(" ");
        txtRoomQty.clear();
        txtRoomType.clear();
        txtRoomKeyMoney.clear();
    }

    private void setID() throws Exception {
        txtRoomId.setText(roomsBO.generateNextRoomID());
        System.out.println(txtRoomId.getText());
    }

    void getData(){
        tblRooms.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //setFieldsActivation(false,true);
            //btnCancel.setDisable(true);

            //btnAdd.setText(newValue != null ? "Update" : "Save");
            //btnAdd.setDisable(newValue == null);

            if (newValue != null) {

                txtRoomId.setText(newValue.getId());
                txtRoomType.setText(newValue.getType());
                txtRoomKeyMoney.setText(newValue.getKeyMoney());
                txtRoomQty.setText(String.valueOf(newValue.getQty()));

            }
        });
    }
}
