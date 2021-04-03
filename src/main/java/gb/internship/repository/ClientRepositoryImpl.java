package gb.internship.repository;

import gb.internship.entity.Client;
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
        String id = "6";
        Date birthDateFake = new Date();
        birthDateFake.setSeconds(0);
        birthDateFake.setHours(0);
        birthDateFake.setMinutes(0);
        System.out.println(" -------------- " + birthDateFake);
        System.out.println(" -------------- " + name + " - " + secondName  + " - " + patronymic + " - " + birthDate + " - " + sex + " - "  );

        Session session = null;
        Client client = new Client();
        //client.setId(5);
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

}
