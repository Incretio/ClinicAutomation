package gb.internship.service;

import gb.internship.dto.ClientDto;
import gb.internship.repository.ClientRepository;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class ClientServiceImpl implements ClientService {

    private Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Inject
    private ClientRepository clientRepository;

    public List<ClientDto> getClients() {
        return clientRepository.getClients().stream()
                .map(ClientDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public void setClients(String name, String secondName, String patronymic, String birthDate, String sex) {
        clientRepository.setClients(name, secondName, patronymic, birthDate, sex);
    }

    @Override
    public void delete(int clientId) {

        logger.info("ClientId = " + clientId);
        try {
            clientRepository.delete(clientId);
        } catch (EntityNotFoundException e) {
            logger.error("Client doctor by Id ERROR", e);
        }
    }

}


