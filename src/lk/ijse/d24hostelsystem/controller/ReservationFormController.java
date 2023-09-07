package lk.ijse.d24hostelsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.d24hostelsystem.bo.BOFactory;
import lk.ijse.d24hostelsystem.bo.custom.ReservationBO;
import lk.ijse.d24hostelsystem.dto.ReservationDTO;
import lk.ijse.d24hostelsystem.dto.RoomDTO;
import lk.ijse.d24hostelsystem.dto.StudentDTO;
import lk.ijse.d24hostelsystem.projection.StudentDetailsDTO;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReservationFormController {
    public JFXTextField txtReservationId;
    public JFXComboBox <String>cmbStudentId;
    public JFXComboBox <String>cmbRoomID;
    public JFXButton dltReserveOnAction;
    public JFXTextField txtStudentName;
    public JFXTextField txtRoomQty;
    public JFXButton updateReserveOnAction;
    public JFXButton reserveOnAction;
    public TableView <StudentDetailsDTO>tblNotPayed;
    public TableColumn <StudentDetailsDTO, String>colReserveID;
    public TableColumn <StudentDetailsDTO, String>colRoomID;
    public TableColumn <StudentDetailsDTO, String>colStudentID;
    public TableColumn <StudentDetailsDTO, String>colStudentName;
    public TableColumn <StudentDetailsDTO, String>colContact;
    public TableColumn <StudentDetailsDTO, Date>colPDate;
    public TableView <ReservationDTO>tblReservation;
    public TableColumn <ReservationDTO, String>colReserveId;
    public TableColumn <ReservationDTO, String>colDate;
    public TableColumn <ReservationDTO, String>colStudentId;
    public TableColumn <ReservationDTO, String>colRoomTypeID;
    public TableColumn <ReservationDTO, String>colKeyMoney;
    public JFXCheckBox cbxStatus;

    private ReservationBO reservationBO= (ReservationBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Reservation);
    private String id;

    public void initialize() throws Exception {
        setCellFactory();

        loadAll();
        getUnpaidStudent();
        setUnpaidDetails();


    }

    public void makeReservationVisibleOnAction(ActionEvent actionEvent) throws Exception {
        setReserveID();
        setIds();


    }

    public void updateReservationVisibleOnAction(ActionEvent actionEvent) {
    }

    public void deleteReservationVisibleOnAction(ActionEvent actionEvent) {
    }

    public void dltOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void reserveOnAction(ActionEvent actionEvent) throws Exception {

        boolean isSaved = reserveARoom(getData());
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION, "Room Reserved").show();
            tblReservation.getItems().clear();
            tblNotPayed.getItems().clear();

            loadAll();
            setUnpaidDetails();



            //clearFields();
        }else{
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }
//        }else {
//            if (cbxStatus.isSelected()){
//                //btn.setDisable(false);
//
//                String status="paid";
//
//                boolean isUpdated = false;
//                try {
//                    isUpdated = reservationBO.changePaidStatus(id, status);
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//                if (isUpdated){
//                    tblReservation.getItems().clear();
//                    tblNotPayed.getItems().clear();
//
//                    new Alert(Alert.AlertType.CONFIRMATION, "Status updated").show();
//
//                    try {
//                        loadAll();
//                    } catch (Exception e) {
//                        System.out.println(e.getMessage());
//                        throw new RuntimeException(e);
//                    }
//                    setUnpaidDetails();
//
//                    cbxStatus.setDisable(true);
//                    //btnReserve.setDisable(true);
//                }else {
//                    new Alert(Alert.AlertType.ERROR, "Error").show();
//                }
//
//            }else if (!cbxStatus.isSelected());
    }

    private void setCellFactory(){
        colReserveId.setCellValueFactory(new PropertyValueFactory<>("resID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colRoomTypeID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("status"));

        colReserveID.setCellValueFactory(new PropertyValueFactory<>("resID"));
        colPDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colRoomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void loadAll() throws Exception {
        List<ReservationDTO> reservationDTOS = reservationBO.loadAll();
        ObservableList<ReservationDTO> dtoObservableList= FXCollections.observableList(reservationDTOS);

        tblReservation.setItems(dtoObservableList);
    }

    private void setReserveID() throws Exception {
        txtReservationId.setText(reservationBO.generateNextReservationID());
    }

    private boolean reserveARoom(ReservationDTO reservationDTO) throws Exception {
        boolean isSaved = reservationBO.saveReservation(reservationDTO);

        if (!isSaved){
            return false;
        }

        RoomDTO room = reservationDTO.getRoom();
        room.setQty(room.getQty()-1);

        boolean isUpdated = reservationBO.updateRoomQty(room);

        if (!isUpdated){
            return false;
        }


        return true;
    }

    private ReservationDTO getData() throws Exception {

        String status="unPaid";
        if (cbxStatus.isSelected()){
            status="paid";
        }

        java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        StudentDTO studentData= getStudentData();
        RoomDTO roomData=getRoomData();

        return new ReservationDTO(
                txtReservationId.getText(),
                sqlDate,
                studentData,
                roomData,
                status
        );


    }
    private StudentDTO getStudentData() throws Exception {
        String studentID = cmbStudentId.getSelectionModel().getSelectedItem();
        return reservationBO.getStudent(studentID);

    }

    private RoomDTO getRoomData() throws Exception {
        String roomID = cmbRoomID.getSelectionModel().getSelectedItem();
        return reservationBO.getRoom(roomID);
    }

    private void setIds() throws Exception{
        /*cmbGender.getItems().clear();
        cmbGender1.getItems().clear();*/

        List<String> studentIds = reservationBO.getStudentIds();
        ObservableList<String> studentList= FXCollections.observableList(studentIds);
        cmbStudentId.setItems(studentList);

        List<String> roomIds = reservationBO.getRoomIds();
        ObservableList<String> roomList=FXCollections.observableList(roomIds);
        cmbRoomID.setItems(roomList);

    }

    private void setUnpaidDetails(){
        List<StudentDetailsDTO> unpaidStudents = reservationBO.getUnpaidStudents();
        ObservableList<StudentDetailsDTO> studentDetailsDTOS=FXCollections.observableList(unpaidStudents);

        tblNotPayed.setItems(studentDetailsDTOS);
    }

    void getUnpaidStudent(){
        tblNotPayed.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            btnCancel.setDisable(true);
//
//            btnReserve.setText(newValue != null ? "Update" : "Reserve");
//            //btnReserve.setDisable(newValue == null);
//
//            if (newValue != null) {
//                cbxStatus.setDisable(false);
//                id= newValue.getResID();
            //}
        });
    }

    public void cmbStudentOnAction(ActionEvent actionEvent) throws Exception {
        if (cmbStudentId.getSelectionModel().getSelectedItem()!=null){
            StudentDTO studentData= getStudentData();
            txtStudentName.setText(studentData.getStudentName());
        }
    }

    public void cmbRoomOnAction(ActionEvent actionEvent) throws Exception {
        if (cmbRoomID.getSelectionModel().getSelectedItem()!=null){
            RoomDTO roomData = getRoomData();
            txtRoomQty.setText(String.valueOf(roomData.getQty()));
        }
    }

}
