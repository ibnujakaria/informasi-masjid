package scenes.admin.schedules;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import scenes.MyGroup;

/**
 * Created by ibnujakaria on 19/03/17.
 */
public class ListSchedulesScene extends MyGroup {

    private JFXButton backButton, createScheduleButton;

    @Override
    protected void prepareLayout() {
        VBox vBox = new VBox();
        Label label = new Label("Jadwal Kajian");
        backButton = new JFXButton("Back");
        createScheduleButton = new JFXButton("Tambah Jadwal Kajian");

        vBox.getChildren().addAll(backButton, label);
        getChildren().addAll(vBox);
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });

        createScheduleButton.setOnAction(event -> {

        });
    }
}
