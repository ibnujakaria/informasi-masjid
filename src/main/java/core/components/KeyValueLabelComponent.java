package core.components;

import javafx.scene.control.Label;

/**
 * Created by ibnujakaria on 20/03/17.
 */
public class KeyValueLabelComponent extends Label {

    private Object key;

    public KeyValueLabelComponent (Object key, String text) {
        super (text);
        this.key = key;
    }

    public Object getKey () {
        return key;
    }

    @Override
    public String toString() {
        return "key[" + key + "], text[" + getText() + "]";
    }
}
