package Controller.Listener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class TextfieldListener {

    /**
     * Adds a Listener to Textfields, so only numeric input is allowed.
     *
     * @param txt - Numeric Textfields
     * @return Textfield - with appropriate Listener.
     */
    public static TextField onlyNumbers(TextField txt) {
        txt.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txt.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        return txt;
    }
}
