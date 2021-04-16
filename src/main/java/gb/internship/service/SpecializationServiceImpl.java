package gb.internship.service;

import gb.internship.entity.Specialization;
import gb.internship.repository.SpecializationRepository;
import org.glassfish.jersey.process.internal.RequestScoped;

import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class SpecializationServiceImpl implements SpecializationService{

    @Inject
    private SpecializationRepository specializationRepository;

    @Override
    public List<Specialization> getSpecializations() {
        return specializationRepository.getSpecializations();
    }
}