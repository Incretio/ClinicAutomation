package gb.internship.controller;

import gb.internship.dto.ClientDto;
import gb.internship.service.ClientService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path ("clients")
public class ClientController {

    @Inject
    private ClientService clientService;

    @GET
    @Produces ({ MediaType.APPLICATION_JSON })
    public List<ClientDto> getClients() {
        return clientService.getClients();
    }
}
