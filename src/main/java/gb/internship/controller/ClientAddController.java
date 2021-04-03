package gb.internship.controller;

import gb.internship.service.ClientService;
import gb.internship.view.Templatable;
import gb.internship.view.TemplateType;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.Map;

@Path("clients-add")
public class ClientAddController {

    @Inject
    private ClientService clientService;
    @Inject
    private Templatable templatable;

    @GET
    public String getClients() {
        Map<String, Object> variables = Collections.singletonMap("clients-add", clientService.getClients());
        return templatable.template(TemplateType.CLIENTSADD, variables);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String setClients(@FormParam("name") String name, @FormParam("secondName") String secondName, @FormParam("patronymic") String patronymic, @FormParam("birthDate") String birthDate, @FormParam("sex") String sex) {
        clientService.setClients(name, secondName, patronymic, birthDate, sex);
        return "OK1";
    }

}
