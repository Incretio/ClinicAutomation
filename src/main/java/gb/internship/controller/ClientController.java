package gb.internship.controller;

import gb.internship.entity.Client;
import gb.internship.service.ClientService;
import gb.internship.view.Templatable;
import gb.internship.view.TemplateType;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.Map;

@Path("clients")
public class ClientController {

    @Inject
    private ClientService clientService;
    @Inject
    private Templatable templatable;

    @GET
    public String clientsPage() {
        Map<String, Object> variables = Collections.singletonMap("clients", clientService.getClients());
        return templatable.template(TemplateType.CLIENTS, variables);
    }

    @GET
    @Path("add")
    public String addClientPage() {
        return templatable.template(TemplateType.EDIT_CLIENT);
    }

    @GET
    @Path("edit")
    public String editClientPage(@QueryParam("id") int clientId) {
        Map<String, Object> variables = Collections.singletonMap("client", clientService.getClient(clientId));
        return templatable.template(TemplateType.EDIT_CLIENT, variables);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void editClient(
            @FormParam("id") @DefaultValue("0") int clientId,
            @FormParam("name") String name,
            @FormParam("secondName") String secondName,
            @FormParam("patronymic") String patronymic,
            @FormParam("birthDate") String birthDate,
            @FormParam("sex") String sex) {
        clientService.saveOrUpdate(clientId, name, secondName, patronymic, birthDate, sex);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int clientId) {
        clientService.delete(clientId);
    }

}
