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
import lk.ijse.d24hostelsystem.bo.custom.UsersBO;
import lk.ijse.d24hostelsystem.dto.UsersDTO;

import java.util.List;

public class UsersFormController {

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private TableView<UsersDTO> tblUsers;

    @FXML
    private TableColumn<UsersDTO, String> colUserID;

    @FXML
    private TableColumn<UsersDTO, String > colName;

    @FXML
    private TableColumn<UsersDTO, String> colUserName;

    @FXML
    private TableColumn<UsersDTO, String> colEmail;

    @FXML
    private JFXTextField txtUserID;

    UsersBO usersBO= (UsersBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.Users);
    @FXML
    void initialize() throws Exception {
        setProperties();
        loadAll();
        getData();
        //setID();
    }

    @FXML
    void saveOnAction(ActionEvent event) throws Exception {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setId(txtUserID.getText());
        usersDTO.setName(txtName.getText());
        usersDTO.setUserName(txtUserName.getText());
        usersDTO.setPassword(txtPassword.getText());
        usersDTO.setEmail(txtEmail.getText());

        boolean isSaved = usersBO.saveUsers(
                new UsersDTO(
                        txtUserID.getText(),
                        txtName.getText(),
                        txtUserName.getText(),
                        txtPassword.getText(),
                        txtEmail.getText()
                )
        );
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"User added").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }

    @FXML
    void updateOnAction(ActionEvent event) throws Exception {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setId(txtUserID.getText());
        usersDTO.setName(txtName.getText());
        usersDTO.setUserName(txtUserName.getText());
        usersDTO.setPassword(txtPassword.getText());
        usersDTO.setEmail(txtEmail.getText());

        boolean isSaved = usersBO.updateUsers(
                new UsersDTO(
                        txtUserID.getText(),
                        txtName.getText(),
                        txtUserName.getText(),
                        txtPassword.getText(),
                        txtEmail.getText()
                )
        );
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION,"User updated").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Error").show();
        }
    }


    public void setProperties(){
        colUserID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadAll() throws Exception {
        List<UsersDTO> usersDTOS = usersBO.loadAll();
        ObservableList<UsersDTO> list= FXCollections.observableArrayList(usersDTOS);
        tblUsers.setItems(list);
    }

    private void getData(){
        tblUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null){
                txtUserID.setText(newValue.getId());
                txtName.setText(newValue.getName());
                txtUserName.setText(newValue.getUserName());
                txtPassword.setText(newValue.getPassword());
                txtEmail.setText(newValue.getEmail());
            }
        });
    }

    private void setID() throws Exception {
        txtUserID.setText(usersBO.generateNextUserID());

        //System.out.println(studentBO.generateNextStudentID());
    }

}

