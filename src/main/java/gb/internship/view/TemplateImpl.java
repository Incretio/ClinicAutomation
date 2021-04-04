package gb.internship.view;

import org.glassfish.jersey.process.internal.RequestScoped;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import java.util.Collections;
import java.util.Map;

@RequestScoped
public class TemplateImpl implements Templatable {

    @Context
    private ServletContext context;
    @Context HttpServletRequest request;
    @Context HttpServletResponse response;
    @Context ServletContext servletContext;

    @Override
    public String template(TemplateType templateName) {
        return template(templateName, Collections.emptyMap());
    }

    @Override
    public String template(TemplateType templateName, Map<String, Object> variables) {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(new ServletContextTemplateResolver(context));
        WebContext webContext = new WebContext(request, response, servletContext);
        new org.thymeleaf.context.Context();
        webContext.setVariables(variables);
        return templateEngine.process(templateName.getTemplatePath(), webContext);
    }

}
