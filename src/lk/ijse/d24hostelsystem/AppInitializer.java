package lk.ijse.d24hostelsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.d24hostelsystem.util.SessionFactoryConfig;
import org.hibernate.Session;

import java.io.IOException;

import static javafx.application.Application.launch;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Session session= SessionFactoryConfig.getInstance().getSession();
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/d24hostelsystem/view/LoginForm.fxml"))));
//        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.centerOnScreen();
//        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
