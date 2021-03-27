package gb.internship.controller;

import gb.internship.service.ClientService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("demo")
public class Demo {

    @GET
    public String doGet() {
        int count = new ClientService().getClients().size();
        return "Application started successfully. Client count in database = " + count;
    }

}