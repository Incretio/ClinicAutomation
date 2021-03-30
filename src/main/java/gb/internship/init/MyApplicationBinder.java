package gb.internship.init;

import gb.internship.repository.*;
import gb.internship.service.ClientService;
import gb.internship.service.ClientServiceImpl;
import gb.internship.service.DoctorService;
import gb.internship.service.DoctorServiceImpl;
import gb.internship.view.Templatable;
import gb.internship.view.TemplateImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(ClientServiceImpl.class).to(ClientService.class);
        bind(ClientRepositoryImpl.class).to(ClientRepository.class);

        bind(DoctorServiceImpl.class).to(DoctorService.class);
        bind(DoctorRepositoryImpl.class).to(DoctorRepository.class);

        bind(TemplateImpl.class).to(Templatable.class);
        bind(RepositoryImpl.class).to(Repository.class);
    }
}
