package gb.internship.controller;

import gb.internship.service.ClientService;
import gb.internship.view.Templatable;
import gb.internship.view.TemplateType;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Collections;
import java.util.Map;

@Path ("clients")
public class ClientController {

    @Inject
    private ClientService clientService;
    @Inject
    private Templatable templatable;

    @GET
    public String getClients() {
        Map<String, Object> variables = Collections.singletonMap("clients", clientService.getClients());
        return templatable.template(TemplateType.CLIENTS, variables);
    }
}
