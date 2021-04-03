package gb.internship.service;

import gb.internship.dto.DoctorDto;
import gb.internship.repository.DoctorRepository;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.hibernate.annotations.common.util.impl.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class DoctorServiceImpl implements DoctorService {

    private Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);

    @Inject
    private DoctorRepository doctorRepository;

    @Override
    public List<DoctorDto> getDoctors() {
        return doctorRepository.getDoctors().stream()
                               .map(DoctorDto::new)
                               .collect(Collectors.toList());
    }

    @Override
    public void delete(int doctorId) {
        logger.info("DoctorId = " + doctorId);
        try {
            doctorRepository.delete(doctorId);
        } catch (EntityNotFoundException e) {
            logger.error("Delete doctor by Id ERROR", e);
        }
    }

}
