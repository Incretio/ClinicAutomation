package gb.internship.dto;

import gb.internship.entity.Client;
import gb.internship.entity.Sex;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class ClientDtoTest {

    private DateTime testDateTime;
    private ClientDto testClientDto;
    private final String TEST = "Test";


    @BeforeEach
    void setUp() {
        testDateTime = new DateTime(2000, 1, 1, 0, 0);

        Client client = new Client();
        client.setId(0);
        client.setPatronymic("");
        client.setName("");
        client.setSecondName("");
        client.setBirthDate(new Date());
        client.setSex("MALE");
        testClientDto = new ClientDto(client);
    }

    @Test
    void getId() {
        testClientDto.setId(1);
        Assertions.assertEquals(1, testClientDto.getId());
    }

    @Test
    void getName() {
        testClientDto.setName(TEST);
        Assertions.assertEquals(TEST, testClientDto.getName());
    }

    @Test
    void getSecondName() {
        testClientDto.setSecondName(TEST);
        Assertions.assertEquals(TEST, testClientDto.getSecondName());
    }

    @Test
    void getPatronymic() {
        testClientDto.setPatronymic(TEST);
        Assertions.assertEquals(TEST, testClientDto.getPatronymic());
    }

    @Test
    void getBirthDate() {
        testClientDto.setBirthDate(testDateTime.toDate());
        Assertions.assertEquals(testDateTime.toDate(), testClientDto.getBirthDate());
    }

    @Test
    void getBirthDateIso() throws ParseException {
        testClientDto.setBirthDate(testDateTime.toDate());
        String testClientDtoFormat = new SimpleDateFormat("yyyy-MM-dd").format(testDateTime.toDate());
        Assertions.assertEquals(testClientDtoFormat, testClientDto.getBirthDateIso());
    }

    @Test
    void getSex() {
        testClientDto.setSex(Sex.FEMALE);
        Assertions.assertEquals(Sex.FEMALE, testClientDto.getSex());
        testClientDto.setSex(Sex.MALE);
        Assertions.assertEquals(Sex.MALE, testClientDto.getSex());
    }
}