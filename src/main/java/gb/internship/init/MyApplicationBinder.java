package gb.internship.init;

import gb.internship.repository.*;
import gb.internship.service.*;
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
        bind(ScheduleDoctorServiceImpl.class).to(ScheduleDoctorService.class);

        bind(SpecializationRepositoryImpl.class).to(SpecializationRepository.class);
        bind(SpecializationServiceImpl.class).to(SpecializationService.class);

        bind(TemplateImpl.class).to(Templatable.class);
        bind(RepositoryImpl.class).to(Repository.class);

        bind(ScheduleServiceImpl.class).to(ScheduleService.class);
        bind(ScheduleRepositoryImpl.class).to(ScheduleRepository.class);
    }
}
