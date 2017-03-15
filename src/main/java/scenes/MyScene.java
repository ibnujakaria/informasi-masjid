package scenes;

import javafx.beans.NamedArg;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.paint.Paint;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public class MyScene extends Scene {
    public MyScene(@NamedArg("root") Parent root) {
        super(root);
    }

    public MyScene(@NamedArg("root") Parent root, @NamedArg("width") double width, @NamedArg("height") double height) {
        super(root, width, height);
    }

    public MyScene(@NamedArg("root") Parent root, @NamedArg(value = "fill", defaultValue = "WHITE") Paint fill) {
        super(root, fill);
    }

    public MyScene(@NamedArg("root") Parent root, @NamedArg("width") double width, @NamedArg("height") double height, @NamedArg(value = "fill", defaultValue = "WHITE") Paint fill) {
        super(root, width, height, fill);
    }

    public MyScene(@NamedArg("root") Parent root, @NamedArg(value = "width", defaultValue = "-1") double width, @NamedArg(value = "height", defaultValue = "-1") double height, @NamedArg("depthBuffer") boolean depthBuffer) {
        super(root, width, height, depthBuffer);
    }

    public MyScene(@NamedArg("root") Parent root, @NamedArg(value = "width", defaultValue = "-1") double width, @NamedArg(value = "height", defaultValue = "-1") double height, @NamedArg("depthBuffer") boolean depthBuffer, @NamedArg(value = "antiAliasing", defaultValue = "DISABLED") SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
    }
}
