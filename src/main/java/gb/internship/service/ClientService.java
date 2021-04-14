package gb.internship.service;

import gb.internship.dto.ClientDto;
import gb.internship.entity.Client;

import java.util.Date;
import java.util.List;

public interface ClientService {

    List<ClientDto> getClients();
    Client getClient(int id);
    Client getZeroClient();
    void saveOrUpdate(int id, String name, String  secondName, String patronymic, Date birthDate, String sex);
    void delete(int clientId);

}
