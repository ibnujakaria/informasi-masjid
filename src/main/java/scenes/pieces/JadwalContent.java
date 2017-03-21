package scenes.pieces;

import com.jfoenix.controls.JFXListView;
import core.components.ScheduleComponent;
import database.models.Schedule;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jooq.Record;

/**
 * Created by abdullah on 17/03/17.
 */
public class JadwalContent extends JFXListView<VBox> {
    public JadwalContent() {
        for (Record schedule : Schedule.get()) {
            ScheduleComponent kajian = new ScheduleComponent(schedule);
            kajian.setPadding(new Insets(10, 10, 10, 10));
            getItems().add(kajian);
        }

    }
}
