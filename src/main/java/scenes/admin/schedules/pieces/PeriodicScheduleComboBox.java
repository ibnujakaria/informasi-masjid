package scenes.admin.schedules.pieces;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import core.components.KeyValueLabelComponent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.security.Key;
import java.util.HashMap;

/**
 * Created by basithdj on 3/20/17.
 */
public class PeriodicScheduleComboBox extends HBox {
    public KeyValueLabelComponent periodics[];
    private JFXComboBox periodicComboBox, intervalByComboBox, subIntervalByComboBox, intervalComboBox;
    private JFXDatePicker datePicker;
    private String days[] = {"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Ahad"};

    public PeriodicScheduleComboBox () {
        periodics = new KeyValueLabelComponent[]{
                new KeyValueLabelComponent("once", "Sekali"),
                new KeyValueLabelComponent("weekly", "Mingguan"),
                new KeyValueLabelComponent("monthly", "Bulanan")
        };

        periodicComboBox = new JFXComboBox();
        periodicComboBox.setPromptText("Periode");

        intervalByComboBox = new JFXComboBox();
        intervalByComboBox.setVisible(false);
        periodicComboBox.getItems().addAll(periodics);

        subIntervalByComboBox = new JFXComboBox();
        subIntervalByComboBox.setVisible(false);

        intervalComboBox = new JFXComboBox();
        intervalComboBox.setVisible(false);

        datePicker = new JFXDatePicker();
        datePicker.setVisible(false);

        getChildren().addAll(periodicComboBox, intervalByComboBox, subIntervalByComboBox, intervalComboBox, datePicker);
        addListeners();
    }

    private void addListeners () {
        periodicComboBox.setOnAction(event -> {
            KeyValueLabelComponent selected = (KeyValueLabelComponent) periodicComboBox.getValue();
            System.out.println(periodicComboBox.getValue());

            if (selected.getKey().equals("once")) {
                datePicker.setVisible(true);
                intervalByComboBox.setVisible(false);
                intervalComboBox.setVisible(false);
                subIntervalByComboBox.setVisible(false);
            } else if (selected.getKey().equals("weekly")) {
                intervalByComboBox.getItems().clear();

                for (int i = 1; i <= 7; i++) {
                    intervalByComboBox.getItems().add(new KeyValueLabelComponent("" + i, days[i-1]));
                }
                intervalByComboBox.setPromptText("Pilih Hari");
                intervalByComboBox.setVisible(true);
                subIntervalByComboBox.setVisible(false);
                datePicker.setVisible(false);
            } else if (selected.getKey().equals("monthly")) {
                intervalByComboBox.setPromptText("Pilih Interval");
                intervalByComboBox.getItems().clear();
                intervalByComboBox.getItems().addAll(
                        new KeyValueLabelComponent("day", "Pertanggal"),
                        new KeyValueLabelComponent("week", "Perpekan ke-")
                );
                intervalByComboBox.setVisible(true);
                datePicker.setVisible(false);
                subIntervalByComboBox.setVisible(false);
            } else {
                intervalByComboBox.setVisible(false);
                subIntervalByComboBox.setVisible(false);
                datePicker.setVisible(false);
            }
        });

        intervalByComboBox.setOnAction(event -> {
            KeyValueLabelComponent selected = (KeyValueLabelComponent) intervalByComboBox.getValue();
            KeyValueLabelComponent selectedPeriodic = (KeyValueLabelComponent) periodicComboBox.getValue();
            if (selected != null && selectedPeriodic != null) {

                if (selectedPeriodic.getKey().equals("monthly") && selected.getKey().equals("week")) {
                    subIntervalByComboBox.getItems().clear();
                    for (int i = 1; i <= 5; i++) {
                        subIntervalByComboBox.getItems().add(new KeyValueLabelComponent(i + "", "Pekan " + i));
                    }
                    subIntervalByComboBox.setPromptText("Pilih pekan");
                    subIntervalByComboBox.setVisible(true);
                    intervalComboBox.setVisible(false);
                } else if (selectedPeriodic.getKey().equals("monthly") && selected.getKey().equals("day")) {
                    subIntervalByComboBox.getItems().clear();
                    for (int i = 1; i <= 31; i++) {
                        subIntervalByComboBox.getItems().add(new KeyValueLabelComponent("" + i, "" + i));
                    }
                    subIntervalByComboBox.setPromptText("Pilih tanggal");
                    subIntervalByComboBox.setVisible(true);
                    intervalComboBox.setVisible(false);
                }
            }
        });

        subIntervalByComboBox.setOnAction(event -> {
            KeyValueLabelComponent selectedPeriodic = (KeyValueLabelComponent) periodicComboBox.getValue();
            KeyValueLabelComponent selectedIntervalBy = (KeyValueLabelComponent) intervalByComboBox.getValue();
            KeyValueLabelComponent selected = (KeyValueLabelComponent) subIntervalByComboBox.getValue();

            if (selected != null && selectedPeriodic.getKey().equals("monthly") &&
                    selectedIntervalBy.getKey().equals("week")) {
                intervalComboBox.getItems().clear();

                for (int i = 1; i <= 7; i++) {
                    intervalComboBox.getItems().add(new KeyValueLabelComponent("" + i, days[i-1]));
                }
                intervalComboBox.setPromptText("Pilih Hari");
                intervalComboBox.setVisible(true);
            }
        });
    }

    public String getPeriodic () {
        return ((KeyValueLabelComponent) periodicComboBox.getValue()).getKey().toString();
    }


    public String getIntervalBy() {
        if (((KeyValueLabelComponent) periodicComboBox.getValue()).getKey().equals("monthly")) {
            return ((KeyValueLabelComponent) intervalByComboBox.getValue()).getKey().toString();
        } else if (((KeyValueLabelComponent) periodicComboBox.getValue()).getKey().equals("weekly")) {
            return ((KeyValueLabelComponent) intervalByComboBox.getValue()).getKey().toString();
        }
        return null;
    }

    public String getSubIntervalBy() {
        if (((KeyValueLabelComponent) periodicComboBox.getValue()).getKey().equals("monthly") &&
                ((KeyValueLabelComponent) intervalByComboBox.getValue()).getKey().equals("week")) {
            return ((KeyValueLabelComponent) subIntervalByComboBox.getValue()).getKey().toString();
        }
        return null;
    }

    public String getExactDate() {
        if (((KeyValueLabelComponent) periodicComboBox.getValue()).getKey().equals("once")) {
            return datePicker.getValue().toString();
        }
        return null;
    }

    public String getInterval() {
        if (((KeyValueLabelComponent) periodicComboBox.getValue()).getKey().equals("monthly") &&
                ((KeyValueLabelComponent) intervalByComboBox.getValue()).getKey().equals("week")) {
            return ((KeyValueLabelComponent) intervalComboBox.getValue()).getKey().toString();
        }
        return null;
    }
}
