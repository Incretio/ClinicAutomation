package gb.internship.repository;

import gb.internship.entity.Client;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ClientRepositoryImpl implements ClientRepository {

    @Inject
    private Repository repository;

    @Override
    public List<Client> getClients() {
        try (Session session = repository.openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Client> query = session.createQuery("FROM Client", Client.class); //You will get Weayher object
            List<Client> result = query.list();
            transaction.commit();
            return result;
        }
    }

    @Override
    public Client getClient(int id) {
        try (Session session = repository.openSession()) {
            Client client = session.get(Client.class, id);
            Hibernate.initialize(client.getScheduleRecords());
            return client;
        }
    }

    @Override
    public void saveOrUpdate(Client client) {
        try (Session session = repository.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(client);
            transaction.commit();
        }
    }

    @Override
    public void delete(int clientId) {
        try (Session session = repository.openSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = session.load(Client.class, clientId);
            session.delete(client);
            transaction.commit();
        }
    }

}
