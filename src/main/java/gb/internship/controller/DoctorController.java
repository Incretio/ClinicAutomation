package gb.internship.controller;

import gb.internship.service.DoctorService;
import gb.internship.view.Templatable;
import gb.internship.view.TemplateType;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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

    @DELETE
    @Path("{doctorId}")
    public void delete(@PathParam("doctorId") int doctorId) {
        doctorService.delete(doctorId);
    }
}
