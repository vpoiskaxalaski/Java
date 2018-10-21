package bstu.yuki;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("myValid")
public class MyValidator implements Validator
{
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {

        if(o.toString().length()<3)
        {
            FacesMessage msg =
                    new FacesMessage("Password validation failed.",
                            "Invalid");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }
}