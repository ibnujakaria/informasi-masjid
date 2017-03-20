package scenes.admin.schedules.pieces;

import com.jfoenix.controls.JFXComboBox;
import core.components.KeyValueLabelComponent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

/**
 * Created by basithdj on 3/20/17.
 */
public class PeriodicScheduleComboBox extends HBox {
    public KeyValueLabelComponent periodics[];
    private JFXComboBox periodicComboBox, intervalByComboBox, subIntervalByComboBox, intervalComboBox;

    public PeriodicScheduleComboBox () {
        periodics = new KeyValueLabelComponent[]{
                new KeyValueLabelComponent("once", "Sekali"),
                new KeyValueLabelComponent("weekly", "Mingguan"),
                new KeyValueLabelComponent("monthly", "Bulanan"),
                new KeyValueLabelComponent("yearly", "Tahunan")
        };

        periodicComboBox = new JFXComboBox();
        periodicComboBox.setPromptText("Periode");

        intervalByComboBox = new JFXComboBox();
        intervalByComboBox.setPromptText("Pilih Interval");
        intervalByComboBox.setVisible(false);
        periodicComboBox.getItems().addAll(periodics);

        subIntervalByComboBox = new JFXComboBox();
        subIntervalByComboBox.setVisible(false);

        intervalComboBox = new JFXComboBox();
        intervalComboBox.setVisible(false);

        getChildren().addAll(periodicComboBox, intervalByComboBox, subIntervalByComboBox, intervalComboBox);
        addListeners();
    }

    private void addListeners () {
        periodicComboBox.setOnAction(event -> {
            KeyValueLabelComponent selected = (KeyValueLabelComponent) periodicComboBox.getValue();
            System.out.println(periodicComboBox.getValue());

            if (selected.getKey().equals("monthly")) {
                intervalByComboBox.getItems().clear();
                intervalByComboBox.getItems().addAll(
                        new KeyValueLabelComponent("day", "Pertanggal"),
                        new KeyValueLabelComponent("week", "Perpekan ke-")
                );
                intervalByComboBox.setVisible(true);
            } else {
                intervalByComboBox.setVisible(false);
            }
        });

        intervalByComboBox.setOnAction(event -> {
            KeyValueLabelComponent selected = (KeyValueLabelComponent) intervalByComboBox.getValue();

            if (selected.getKey().equals("week")) {
                subIntervalByComboBox.getItems().clear();
                for (int i = 1; i <= 5; i++) {
                    subIntervalByComboBox.getItems().add(new KeyValueLabelComponent(i + "", "Pekan " + i));
                }
                subIntervalByComboBox.setPromptText("Pilih pekan");
                subIntervalByComboBox.setVisible(true);
            }
        });

        subIntervalByComboBox.setOnAction(event -> {
            KeyValueLabelComponent selected = (KeyValueLabelComponent) subIntervalByComboBox.getValue();

            if (selected != null) {
                intervalComboBox.getItems().clear();
                String days[] = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Ahad"};
                for (int i = 1; i <= 7; i++) {
                    intervalComboBox.getItems().add(new KeyValueLabelComponent("" + i, days[i-1]));
                }
                intervalComboBox.setPromptText("Pilih Hari");
                intervalComboBox.setVisible(true);
            }
        });
    }
}
