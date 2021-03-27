package gb.internship.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path ("demo")
public class Demo {

    @GET
    public String doGet() {
        return "Application started successfully.";
    }

}