package scenes.admin.schedules;

import com.jfoenix.controls.JFXButton;
import core.components.ScheduleComponent;
import database.models.jooq.Schedule;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jooq.Record;
import scenes.MyGroup;

/**
 * Created by ibnujakaria on 19/03/17.
 */
public class ListSchedulesScene extends MyGroup {

    private JFXButton backButton, createScheduleButton;
    private VBox listSchedules;

    @Override
    protected void prepareLayout() {
        VBox vBox = new VBox();
        Label label = new Label("Jadwal Kajian");
        backButton = new JFXButton("Back");
        createScheduleButton = new JFXButton("Tambah Jadwal Kajian");
        listSchedules = new VBox();

        vBox.getChildren().addAll(backButton, label, createScheduleButton, listSchedules);
        getChildren().addAll(vBox);

        loadSchedules();
    }

    @Override
    protected void onAfterBack() {
        super.onAfterBack();
        loadSchedules();
    }

    private void loadSchedules() {
        listSchedules.getChildren().clear();
        for (Record schedule : Schedule.get()) {
            listSchedules.getChildren().addAll(new ScheduleComponent(schedule));
        }
    }

    @Override
    protected void addListeners() {
        backButton.setOnAction(event -> {
            movePreviousScene();
        });

        createScheduleButton.setOnAction(event -> {
            setNextScene(new CreateScheduleScene());
            moveNextScene();
        });
    }
}
