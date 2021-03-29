package gb.internship.init;

import gb.internship.repository.ClientRepository;
import gb.internship.repository.ClientRepositoryImpl;
import gb.internship.repository.Repository;
import gb.internship.repository.RepositoryImpl;
import gb.internship.service.ClientService;
import gb.internship.service.ClientServiceImpl;
import gb.internship.view.Templatable;
import gb.internship.view.TemplateImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(ClientServiceImpl.class).to(ClientService.class);
        bind(ClientRepositoryImpl.class).to(ClientRepository.class);
        bind(TemplateImpl.class).to(Templatable.class);
        bind(RepositoryImpl.class).to(Repository.class);
    }
}
