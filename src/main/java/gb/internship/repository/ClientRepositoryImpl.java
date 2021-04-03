package gb.internship.repository;

import gb.internship.entity.Client;
import gb.internship.entity.Doctor;
import gb.internship.entity.Sex;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Singleton
public class ClientRepositoryImpl implements ClientRepository {

    @Inject
    private Repository repository;

    @Override
    public List<Client> getClients() {
        Session session = repository.openSession();
        Transaction transaction = session.beginTransaction();
        Query<Client> query = session.createQuery("FROM Client", Client.class); //You will get Weayher object
        List<Client> result = query.list();
        transaction.commit();
        return result;
    }

    @Override
    public void setClients(String name, String  secondName, String patronymic, String birthDate, String sex) {
        Session session = null;
        Client client = new Client();
        client.setName(name);
        client.setSecondName(secondName);
        client.setPatronymic(patronymic);
        client.setBirthDate(Long.valueOf(birthDate));
        client.setSex(sex);
        try {
            session = repository.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(client);
            transaction.commit();
        } finally {
            if (session != null) session.close();
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
