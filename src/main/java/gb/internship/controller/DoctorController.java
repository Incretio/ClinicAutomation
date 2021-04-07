package gb.internship.controller;

import gb.internship.entity.Doctor;
import gb.internship.service.DoctorService;
import gb.internship.view.Templatable;
import gb.internship.view.TemplateType;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.Map;

@Path ("doctors")
public class DoctorController {

    @Inject
    private DoctorService doctorService;
    @Inject
    private Templatable templatable;

    @GET
    public String doctorsPage() {
        Map<String, Object> variables = Collections.singletonMap("doctors", doctorService.getDoctors());
        return templatable.template(TemplateType.DOCTORS, variables);
    }

    @GET
    @Path ("add")
    public String addDoctorPage() {
        return templatable.template(TemplateType.EDIT_DOCTOR);
    }

    @GET
    @Path ("edit")
    public String editDoctorPage(@QueryParam("doctorId") int doctorId) {
        Doctor doctor = doctorService.getDoctor(doctorId);
        return templatable.template(TemplateType.EDIT_DOCTOR, Collections.singletonMap("doctor", doctor));
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void editDoctor(
            @FormParam("id") @DefaultValue("0") int id,
            @FormParam("name") String name,
            @FormParam("secondName") String secondName,
            @FormParam("patronymic") String patronymic,
            @FormParam("dateOfEmployment") String dateOfEmployment,
            @FormParam("specialization") String specialization) {
        doctorService.saveOrUpdate(id, name, secondName, patronymic, dateOfEmployment, specialization);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int doctorId) {
        doctorService.delete(doctorId);
    }

}
