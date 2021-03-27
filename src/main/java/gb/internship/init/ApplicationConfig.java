package gb.internship.init;

import gb.internship.controller.ClientController;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath ("/api")
public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        register(ClientController.class);
        register(new MyApplicationBinder());
    }
}
