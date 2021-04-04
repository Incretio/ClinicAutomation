package gb.internship.service;

import gb.internship.dto.DoctorDto;
import gb.internship.entity.Client;
import gb.internship.entity.Doctor;
import gb.internship.entity.Specialization;
import gb.internship.repository.DoctorRepository;
import org.glassfish.jersey.process.internal.RequestScoped;
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
    public void setDoctor(String name, String secondName, String patronymic, String dateOfEmployment, String specialization) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setSecondName(secondName);
        doctor.setPatronymic(patronymic);
        doctor.setDateOfEmployment(dateOfEmployment);
        Specialization spec = new Specialization();
        spec.setId(Integer.parseInt(specialization));
        doctor.setSpecialization(spec);
        doctorRepository.addDoctor(doctor);
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
