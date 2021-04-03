package gb.internship.service;

import gb.internship.dto.ClientDto;
import gb.internship.entity.Client;
import gb.internship.repository.ClientRepository;
import org.glassfish.jersey.process.internal.RequestScoped;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ClientServiceImpl implements ClientService {

    @Inject
    private ClientRepository clientRepository;

    public List<ClientDto> getClients() {
        return clientRepository.getClients().stream()
                               .map(ClientDto::new)
                               .collect(Collectors.toList());
    }

    @Override
    public void setClients(String name, String  secondName, String patronymic, String birthDate, String sex) {
        Client client = new Client();
        client.setName(name);
        client.setSecondName(secondName);
        client.setPatronymic(patronymic);
        client.setBirthDate(Long.valueOf(birthDate));
        client.setSex(sex);
        clientRepository.addClient(client);
    }

}
