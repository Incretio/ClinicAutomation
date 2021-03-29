package gb.internship.view;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import java.util.Map;

@RequestScoped
public class TemplateImpl implements Templatable {

    @Context
    private ServletContext context;

    @Override
    public String template(TemplateType templateName, Map<String, Object> variables) {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(new ServletContextTemplateResolver(context));
        org.thymeleaf.context.Context webContext = new org.thymeleaf.context.Context();
        webContext.setVariables(variables);
        return templateEngine.process(templateName.getTemplatePath(), webContext);
    }

}
