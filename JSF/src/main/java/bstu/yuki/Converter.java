package bstu.yuki;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

@FacesConverter("myConvert")
public class Converter implements javax.faces.convert.Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        int a = Integer.valueOf(s);
        return a*2.1;
    }


    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return ((Double) o).toString();
    }
}
