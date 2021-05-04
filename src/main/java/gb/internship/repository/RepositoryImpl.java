package gb.internship.repository;

import gb.internship.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

@Singleton
public class RepositoryImpl implements Repository {

    private SessionFactory sessionFactory;

    @PostConstruct
    private void postConstruct() {
        Configuration configure = new Configuration();
        configure.addAnnotatedClass(Client.class);
        configure.addAnnotatedClass(Doctor.class);
        configure.addAnnotatedClass(ScheduleRecord.class);
        configure.addAnnotatedClass(Service.class);
        configure.addAnnotatedClass(Specialization.class);
        configure.addAnnotatedClass(TimeRange.class);
        configure.addAnnotatedClass(TimeRangeToDoctor.class);
        StandardServiceRegistry build = new StandardServiceRegistryBuilder().applySettings(configure.getProperties()).build();
        sessionFactory = configure.buildSessionFactory(build);
    }

    public Session openSession() {
        return sessionFactory.openSession();
    }
}
