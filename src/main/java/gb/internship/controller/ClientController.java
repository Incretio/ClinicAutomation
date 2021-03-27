package gb.internship.controller;

import gb.internship.dto.ClientDto;
import gb.internship.service.ClientService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("clients")
public class ClientController {

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public List<ClientDto> getClients() {
        return new ClientService().getClients();
    }
}
