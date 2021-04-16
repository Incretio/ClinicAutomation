package gb.internship.service;

import gb.internship.dto.DoctorDto;
import gb.internship.entity.Specialization;

import java.util.List;

public interface SpecializationService {

    List<Specialization> getSpecializations();
}