package gb.internship.controller;

import gb.internship.entity.Client;
import gb.internship.view.Templatable;
import gb.internship.view.TemplateType;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path ("demo")
public class DemoController {

    @Inject
    private Templatable templatable;

    @GET
    public String doGet() {
        Client client = new Client();
        client.setId(159);
        List<String> list = Arrays.asList("one_value", "two_value", "three_value");
        Map<String, Object> variables = new HashMap<>();
        variables.put("demo_parameter", "demo_parameter_value");
        variables.put("demo_object", client);
        variables.put("demo_iterable_object", list);
        return templatable.template(TemplateType.DEMO, variables);
    }


}
