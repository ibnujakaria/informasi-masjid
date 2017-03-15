package scenes;

import javafx.beans.NamedArg;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;
import javafx.stage.Window;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class MyScene extends Scene implements ChangeListener<Number> {
    public static double height = 600, width = 800;

    public MyScene(@NamedArg("root") Parent root) {
        super(root);
        widthProperty().addListener(this);
        heightProperty().addListener(this);
    }

    public MyScene(@NamedArg("root") Parent root, @NamedArg("width") double width, @NamedArg("height") double height) {

        super(root, width, height);
        widthProperty().addListener(this);
        heightProperty().addListener(this);
    }

    public MyScene(@NamedArg("root") Parent root, @NamedArg(value = "fill", defaultValue = "WHITE") Paint fill) {
        super(root, fill);
        widthProperty().addListener(this);
        heightProperty().addListener(this);
    }

    public MyScene(@NamedArg("root") Parent root, @NamedArg("width") double width, @NamedArg("height") double height, @NamedArg(value = "fill", defaultValue = "WHITE") Paint fill) {
        super(root, width, height, fill);
        widthProperty().addListener(this);
        heightProperty().addListener(this);
    }

    public MyScene(@NamedArg("root") Parent root, @NamedArg(value = "width", defaultValue = "-1") double width, @NamedArg(value = "height", defaultValue = "-1") double height, @NamedArg("depthBuffer") boolean depthBuffer) {
        super(root, width, height, depthBuffer);
        widthProperty().addListener(this);
        heightProperty().addListener(this);
    }

    public MyScene(@NamedArg("root") Parent root, @NamedArg(value = "width", defaultValue = "-1") double width, @NamedArg(value = "height", defaultValue = "-1") double height, @NamedArg("depthBuffer") boolean depthBuffer, @NamedArg(value = "antiAliasing", defaultValue = "DISABLED") SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
        widthProperty().addListener(this);
        heightProperty().addListener(this);
    }

    @Override
    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
        height = getHeight();
        width = getWidth();
    }
}
