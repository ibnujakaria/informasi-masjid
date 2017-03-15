package scenes;

import javafx.scene.Group;

/**
 * Created by ibnujakaria on 3/15/17.
 */
public abstract class MyGroup extends Group{

    protected MyScene view;
    protected abstract void prepareLayout();
    protected abstract void addListeners();

    public MyGroup() {
        prepareLayout();
        addListeners();
    }

    public MyScene getView() {
        return view;
    }

    public void setView(MyScene view) {
        this.view = view;
    }
}
