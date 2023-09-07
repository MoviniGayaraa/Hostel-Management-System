package lk.ijse.d24hostelsystem.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.d24hostelsystem.bo.BOFactory;
import lk.ijse.d24hostelsystem.bo.custom.StudentBO;
import lk.ijse.d24hostelsystem.dto.StudentDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentFormController {
    public AnchorPane studentPane;
    public  JFXTextField txtStudentName;
    public  JFXTextField txtStudentId;
    public  JFXTextField txtStudentNic;
    public  JFXTextField txtStudentHomeTown;
    public  JFXDatePicker txtStudentDob;
    public  JFXComboBox cmbStudentGender;
    public  JFXTextField txtStudentPhoneNumber;
    public  JFXButton deleteStudentVisibleOnAction;
    public  JFXTextField txtStudentUniversity;
    public JFXButton btnSaveStudent;
    public JFXButton btnSearchStudent;
    public JFXButton btnUpdateStudent;
    public JFXButton btnClear;
    public JFXButton btnDeleteStudent;
    public TableView <StudentDTO>tblStudents;
    public TableColumn <StudentDTO ,String>colID;
    public TableColumn <StudentDTO ,String>colName;
    public TableColumn <StudentDTO ,String>colNIC;
    public TableColumn <StudentDTO ,String>colAddress;
    public TableColumn <StudentDTO ,String>colGender;
    public TableColumn <StudentDTO ,String>colPhoneNum;
    public TableColumn <StudentDTO ,String>colUniversity;

    private StudentBO studentBO= (StudentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Student);

    public void initialize() throws Exception {
        setProperties();
        txtStudentName.setDisable(true);
        txtStudentId.setDisable(true);
        txtStudentNic.setDisable(true);
        txtStudentHomeTown.setDisable(true);
        txtStudentDob.setDisable(true);
        cmbStudentGender.setDisable(true);
        txtStudentPhoneNumber.setDisable(true);
        txtStudentUniversity.setDisable(true);
        btnSaveStudent.setDisable(true);
        btnSearchStudent.setDisable(true);
        btnUpdateStudent.setDisable(true);
        btnDeleteStudent.setDisable(true);
        btnClear.setDisable(true);
        loadAllStudents();
        getData();

    }
    private void setProperties(){
        colID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("homeTown"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colPhoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colUniversity.setCellValueFactory(new PropertyValueFactory<>("university"));
    }

    public void goBackOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void addStudentVisibleOnAction(ActionEvent actionEvent) throws Exception {
        txtStudentName.setDisable(false);
        txtStudentId.setDisable(false);
        txtStudentNic.setDisable(false);
        txtStudentHomeTown.setDisable(false);
        txtStudentDob.setDisable(true);
        cmbStudentGender.setDisable(false);
        txtStudentPhoneNumber.setDisable(false);
        txtStudentUniversity.setDisable(false);
        btnSaveStudent.setDisable(false);
        btnClear.setDisable(false);
        btnSearchStudent.setDisable(true);
        btnUpdateStudent.setDisable(true);
        btnDeleteStudent.setDisable(true);
        setCmbGender();
        setID();
    }

    public void updateStudentVisibleOnAction(ActionEvent actionEvent) {
        txtStudentName.setDisable(true);
        txtStudentId.setDisable(false);
        txtStudentNic.setDisable(true);
        txtStudentHomeTown.setDisable(true);
        txtStudentDob.setDisable(true);
        cmbStudentGender.setDisable(true);
        txtStudentPhoneNumber.setDisable(true);
        txtStudentUniversity.setDisable(true);
        btnSaveStudent.setDisable(true);
        btnSearchStudent.setDisable(false);
        btnUpdateStudent.setDisable(true);
        btnDeleteStudent.setDisable(true);
        btnClear.setDisable(true);
    }

    public void saveStudentOnAction(ActionEvent actionEvent) throws Exception {
        // getStudentDto();
        StudentDTO studentDto=new StudentDTO();
        studentDto.setStudentId(txtStudentId.getText());
        studentDto.setStudentName(txtStudentName.getText());
        studentDto.setHomeTown(txtStudentHomeTown.getText());

        boolean isValidate = checkValidation();
        if (isValidate){
            boolean isSaved = studentBO.saveStudent(
                    new StudentDTO(
                            txtStudentId.getText(),
                            txtStudentName.getText(),
                            txtStudentNic.getText(),
                            txtStudentHomeTown.getText(),
                            (String) cmbStudentGender.getSelectionModel().getSelectedItem(),
                            txtStudentPhoneNumber.getText(),
                            txtStudentUniversity.getText()
                    )
            );

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Student saved").show();
                clearFields();
//                tblStudent.getItems().clear();
                loadAllStudents();

//                btnCancel.setDisable(true);
//                btnAddStudent.setDisable(false);
            }else{
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }

        }

    }

    public void searchStudentOnAction(ActionEvent actionEvent) {
        txtStudentName.setDisable(false);
        txtStudentId.setDisable(false);
        txtStudentNic.setDisable(false);
        txtStudentHomeTown.setDisable(false);
        txtStudentDob.setDisable(false);
        cmbStudentGender.setDisable(false);
        txtStudentPhoneNumber.setDisable(false);
        txtStudentUniversity.setDisable(false);
        btnSaveStudent.setDisable(true);
        btnSearchStudent.setDisable(true);
        btnUpdateStudent.setDisable(false);
        btnDeleteStudent.setDisable(true);
        btnClear.setDisable(true);
    }

    public void updateStudentOnAction(ActionEvent actionEvent) throws Exception {
        boolean isValidate = checkValidation();
        if (isValidate){
            boolean isUpdated = studentBO.updateStudent(
                    new StudentDTO(
                            txtStudentId.getText(),
                            txtStudentName.getText(),
                            txtStudentNic.getText(),
                            txtStudentHomeTown.getText(),
                            (String) cmbStudentGender.getSelectionModel().getSelectedItem(),
                            txtStudentPhoneNumber.getText(),
                            txtStudentUniversity.getText()
                    )
            );

            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Student updated").show();

                clearFields();
                loadAllStudents();

            }else{
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }

        }
    }

    public void clearOnAction(ActionEvent actionEvent) {
        txtStudentName.clear();
        //txtStudentId.clear();
        txtStudentNic.clear();
        txtStudentHomeTown.clear();
        txtStudentPhoneNumber.clear();
        txtStudentUniversity.clear();
    }

    public void deleteStudentOnAction(ActionEvent actionEvent) throws Exception {
        boolean isValidate = checkValidation();
        if (isValidate){
            boolean isDeleted = studentBO.deleteStudent(
                    new StudentDTO(
                            txtStudentId.getText(),
                            txtStudentName.getText(),
                            txtStudentNic.getText(),
                            txtStudentHomeTown.getText(),
                            (String) cmbStudentGender.getSelectionModel().getSelectedItem(),
                            txtStudentPhoneNumber.getText(),
                            txtStudentUniversity.getText()
                    )
            );

            if (isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Room Deleted").show();

                clearFields();
                loadAllStudents();
            }else{
                new Alert(Alert.AlertType.ERROR, "Error").show();
            }

        }
    }

    public void deleteStudentVisibleOnAction(ActionEvent actionEvent) {
        txtStudentName.setDisable(true);
        txtStudentId.setDisable(false);
        txtStudentNic.setDisable(true);
        txtStudentHomeTown.setDisable(true);
        txtStudentDob.setDisable(true);
        cmbStudentGender.setDisable(true);
        txtStudentPhoneNumber.setDisable(true);
        txtStudentUniversity.setDisable(true);
        btnSaveStudent.setDisable(true);
        btnSearchStudent.setDisable(true);
        btnUpdateStudent.setDisable(true);
        btnDeleteStudent.setDisable(false);
        btnClear.setDisable(true);
    }
    //String name= txtStudentName.getText();
    private StudentDTO getStudentDto(){
        StudentDTO studentDto=new StudentDTO();
        studentDto.setStudentId(txtStudentId.getText());
        studentDto.setStudentName(txtStudentName.getText());
        studentDto.setHomeTown(txtStudentHomeTown.getText());

        return studentDto;
    }






    private void setID() throws Exception {
        txtStudentId.setText(studentBO.generateNextStudentID());

        System.out.println(studentBO.generateNextStudentID());
    }

    private void setCmbGender(){
        ArrayList<String> genders = new ArrayList<>();
        genders.add("Male");
        genders.add("Female");


        ObservableList<String> observableList = FXCollections.observableList(genders);
        cmbStudentGender.setItems(observableList);
    }

    private boolean checkValidation(){
        String nameText = txtStudentName.getText();
        String addressText = txtStudentHomeTown.getText();
        String contactText = txtStudentPhoneNumber.getText();

        if (!nameText.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtStudentName.requestFocus();
            return false;
        } else if (!addressText.matches(".{2,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtStudentHomeTown.requestFocus();
            return false;
        }else if (!contactText.matches(".*(?:7|0|(?:\\\\+94))[0-9]{9,10}")) {
            new Alert(Alert.AlertType.ERROR, "Invalid Contact").show();
            txtStudentPhoneNumber.requestFocus();
            return false;
        }else {
            return true;
        }

    }

    private void clearFields(){
        txtStudentName.clear();
        //txtStudentId.clear();
        txtStudentNic.clear();
        txtStudentHomeTown.clear();
        txtStudentPhoneNumber.clear();
        txtStudentUniversity.clear();
        cmbStudentGender.getSelectionModel().clearSelection();
    }
    private void loadAllStudents() throws Exception {
        List<StudentDTO> studentDTOS = studentBO.loadAll();
        ObservableList<StudentDTO> observableList= FXCollections.observableList(studentDTOS);
        tblStudents.setItems(observableList);
    }

    void getData(){
        tblStudents.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            btnCancel.setDisable(true);
//
//            btnAdd.setText(newValue != null ? "Update" : "Save");
//            btnAdd.setDisable(newValue == null);

            if (newValue != null) {
                txtStudentId.setText(newValue.getStudentId());
                txtStudentName.setText(newValue.getStudentName());
                txtStudentNic.setText(newValue.getNic());
                txtStudentHomeTown.setText(newValue.getHomeTown());
                txtStudentPhoneNumber.setText(newValue.getPhoneNumber());
                txtStudentUniversity.setText(newValue.getUniversity());

                if (newValue.getGender().equals("Male")){
                    setCmbGender();
                    cmbStudentGender.getSelectionModel().select(0);
                }else cmbStudentGender.getSelectionModel().select(1);
            }
        });
    }
}
