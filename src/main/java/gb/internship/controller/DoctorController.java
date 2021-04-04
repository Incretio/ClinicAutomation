package gb.internship.controller;

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
    public String getDoctors() {
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
        // ToDo: implement getting client by Id and transfer it to template
        return templatable.template(TemplateType.EDIT_DOCTOR);
    }

    @POST
    @Path ("add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addDoctor(
            @FormParam("name") String name,
            @FormParam("secondName") String secondName,
            @FormParam("patronymic") String patronymic,
            @FormParam("dateOfEmployment") String dateOfEmployment,
            @FormParam("specialization") String specialization) {
        doctorService.setDoctor(name, secondName, patronymic, dateOfEmployment, specialization);
    }

    @DELETE
    @Path("{doctorId}")
    public void delete(@PathParam("doctorId") int doctorId) {
        doctorService.delete(doctorId);
    }
}
