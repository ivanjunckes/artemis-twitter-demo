package twitter;

import javax.faces.bean.ManagedBean;

@Deprecated
@ManagedBean
public class BasicView {

    private String text;

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}