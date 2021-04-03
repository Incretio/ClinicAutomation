package gb.internship.view;

import java.util.Map;

public interface Templatable {

    String template(TemplateType templateType, Map<String, Object> variables);

}
