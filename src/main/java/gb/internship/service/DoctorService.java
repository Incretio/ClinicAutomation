package gb.internship.service;

import gb.internship.dto.DoctorDto;

import java.util.List;

public interface DoctorService {

    List<DoctorDto> getDoctors();
    void delete(int doctorId);
}
