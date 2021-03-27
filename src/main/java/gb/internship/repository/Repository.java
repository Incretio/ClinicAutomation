package gb.internship.repository;

import org.hibernate.Session;

public interface Repository {

    Session openSession();

}
