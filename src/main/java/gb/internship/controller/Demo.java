package gb.internship.controller;

import gb.internship.entity.Client;
import gb.internship.repository.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("demo")
public class Demo {

    @GET
    public String doGet() {
        Session session = new Repository().openSession();
        Transaction transaction = session.beginTransaction();

        Query<Client> query = session.createQuery("from Client", Client.class); //You will get Weayher object
        List<Client> list = query.list(); //You are accessing  as list<WeatherModel>
        transaction.commit();
        return "Application started successfully. Client count in database = " + list.size();
    }

}