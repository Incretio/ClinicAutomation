package gb.internship.service;

import gb.internship.dto.DoctorDto;
import gb.internship.repository.DoctorRepository;
import org.glassfish.jersey.process.internal.RequestScoped;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class DoctorServiceImpl implements DoctorService {

    @Inject
    private DoctorRepository doctorRepository;

    public List<DoctorDto> getDoctors() {
        return doctorRepository.getDoctors().stream()
                               .map(DoctorDto::new)
                               .collect(Collectors.toList());
    }

}
