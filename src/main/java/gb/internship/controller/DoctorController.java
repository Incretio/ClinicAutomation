package gb.internship.controller;

import gb.internship.dto.DoctorDto;
import gb.internship.entity.TimeRange;
import gb.internship.service.DoctorService;
import gb.internship.service.ScheduleDoctorService;
import gb.internship.service.SpecializationService;
import gb.internship.utils.TimeRangeHelper;
import gb.internship.view.Templatable;
import gb.internship.view.TemplateType;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Path ("doctors")
public class DoctorController {

    @Inject
    private DoctorService doctorService;
    @Inject
    private ScheduleDoctorService scheduleDoctorService;
    @Inject
    private SpecializationService specializationService;
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
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("doctor", new DoctorDto(doctorService.getZeroDoctor()));
        variables.put("specializations", specializationService.getSpecializations());
        return templatable.template(TemplateType.EDIT_DOCTOR, variables);

    }

    @GET
    @Path ("edit")
    public String editDoctorPage(@QueryParam("id") int doctorId) {
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("doctor", new DoctorDto(doctorService.getDoctor(doctorId)));
        variables.put("specializations", specializationService.getSpecializations());
        return templatable.template(TemplateType.EDIT_DOCTOR, variables);
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void editDoctor(
            @FormParam("id") @DefaultValue("0") int id,
            @FormParam("name") String name,
            @FormParam("secondName") String secondName,
            @FormParam("patronymic") String patronymic,
            @FormParam("dateOfEmployment") String dateOfEmployment,
            @FormParam("specialization") int specialization) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfEmploymentDate = formatter.parse(dateOfEmployment);
        doctorService.saveOrUpdate(id, name, secondName, patronymic, dateOfEmploymentDate, specialization);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int doctorId) {
        doctorService.delete(doctorId);
    }

    @GET
    @Path("schedule")
    public String scheduleDoctorPage(
            @QueryParam("doctorId") int doctorId,
            @QueryParam("weekOffset") @DefaultValue("0") int weekOffset) {
        Map<String, Object> variables = new HashMap<>();
        Map<TimeRange, List<Boolean>> scheduleDoctor = scheduleDoctorService.takeScheduleDoctor(doctorId, weekOffset);
        variables.put("scheduleDoctor", scheduleDoctor);
        variables.put("doctor", doctorService.getDoctor(doctorId));
        variables.put("monday", TimeRangeHelper.takeMonday(weekOffset));
        int monday = TimeRangeHelper.takeMonday(weekOffset);
        variables.put("weekDays", TimeRangeHelper.takeWeekDays(monday));
        variables.put("weekOffset", weekOffset);
        variables.put("doctorId", doctorId);
        return templatable.template(TemplateType.SCHEDULE_DOCTOR, variables);
    }

    @POST
    @Path("schedule")
    public void toggleSchedule(
            @FormParam("doctorId") int doctorId,
            @FormParam("timeRangeId") int timeRangeId,
            @FormParam("dateOfReceipt") int dateOfReceipt) {
        scheduleDoctorService.toggleCellScheduleDoctor(doctorId, timeRangeId, dateOfReceipt);
    }

}
