package springpoi.app.util.editores;

import java.beans.PropertyEditorSupport;
import org.springframework.stereotype.Component;

@Component
public class MayusculasEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        try {
            setValue(text.substring(0, 1).toUpperCase() + text.substring(1).trim());
        } catch (Exception e) {
            setValue("");
        }
    }

}
