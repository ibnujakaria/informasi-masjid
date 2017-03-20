package scenes.admin.schedules.pieces;

import com.jfoenix.controls.JFXComboBox;
import javafx.scene.control.Label;

import java.util.HashMap;

/**
 * Created by basithdj on 3/20/17.
 */
public class PeriodicScheduleComboBox extends JFXComboBox {
    public static HashMap<String, String> periodic;

    public PeriodicScheduleComboBox () {
        periodic = new HashMap<>();

        periodic.put("once", "Sekali");
        periodic.put("weekly", "Mingguan");
        periodic.put("monthly", "Bulanan");
        periodic.put("yearly", "Tahunan");

        draw();
    }

    private void draw() {
        getItems().addAll(
                new Label(periodic.get("once")),
                new Label(periodic.get("weekly")),
                new Label(periodic.get("monthly")),
                new Label(periodic.get("yearly"))
        );
    }

}
