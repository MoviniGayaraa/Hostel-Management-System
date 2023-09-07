package lk.ijse.d24hostelsystem.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.d24hostelsystem.util.Navigation;
import lk.ijse.d24hostelsystem.util.Routes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardFormController {
    public Label lblTime;
    public AnchorPane loadPane;
    public Label roomsCount;
    public Label studentCount;
    public Label ReservationCount;

    public void initialize(){
        setDateAndTime();
    }

    private void setDateAndTime() {
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e->{
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd   HH:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void reservationOnAction(ActionEvent actionEvent) throws IOException {
        //new Alert(Alert.AlertType.WARNING, "This function has not Developed yet").show();
        Navigation.navigate(Routes.RESERVATION,loadPane);
    }

    public void studentsOnAction(ActionEvent actionEvent) throws IOException {
        //new Alert(Alert.AlertType.WARNING, "This function has not Developed yet").show();
        Navigation.navigate(Routes.STUDENT,loadPane);
    }

    public void roomsOnAction(ActionEvent actionEvent) throws IOException {
        //new Alert(Alert.AlertType.WARNING, "This function has not Developed yet").show();
        Navigation.navigate(Routes.ROOMS,loadPane);
    }

    public void signOutOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void setttingsOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.SEETINGS,loadPane);
    }
}
