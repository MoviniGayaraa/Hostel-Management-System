package lk.ijse.d24hostelsystem.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {
    private static AnchorPane pane;

    public static void navigate(Routes route , AnchorPane pane) throws IOException {
        Navigation.pane=pane;
        Navigation.pane.getChildren().clear();
        Stage window = (Stage) Navigation.pane.getScene().getWindow();

        switch (route){
            case DASHBOARD:
                initUI("DashboardForm.fxml");
                break;
            case STUDENT:
                initUI("StudentForm.fxml");
                break;
            case ROOMS:
                initUI("RoomsForm.fxml");
                break;
            case RESERVATION:
                initUI("ReservationForm.fxml");
                break;
            case SEETINGS:
                initUI("UsersForm.fxml");
                break;
            default:
                new Alert(Alert.AlertType.ERROR,"No UI Found");
        }
    }

    private static void initUI(String location) throws IOException {
        Navigation.pane.getChildren().add(FXMLLoader.load(Navigation.class
                .getResource("/lk/ijse/d24hostelsystem/view/" + location)));
    }
}
