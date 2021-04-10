package gb.internship.controller;

import gb.internship.dto.ScheduleDoctor;
import gb.internship.entity.Doctor;
import gb.internship.entity.TimeRangeToDoctor;
import gb.internship.service.DoctorService;
import gb.internship.utils.TimeRangeHelper;
import gb.internship.view.Templatable;
import gb.internship.view.TemplateType;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

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

    @GET
    @Path("schedule")
    public String scheduleDoctorPage(@QueryParam("doctorId") int doctorId) {
        Map<String, Object> variables = new HashMap<>();
//        variables.put("doctors", doctorService.getDoctors());
//        variables.put("currentDoctor", doctorId);
        Doctor doctor = doctorService.getDoctor(doctorId);
        Set<TimeRangeToDoctor> timeRangeToDoctors = doctor.getTimeRangeToDoctors();
        int now = TimeRangeHelper.toDaysPast(new Date());

        ScheduleDoctor scheduleDoctor = new ScheduleDoctor();
        int monday = now - (new DateTime().getDayOfWeek() - 1);
        for (int i = 0; i < 7; i++) {
            int dayNumber = monday + i;
            for (TimeRangeToDoctor timeRangeToDoctor : timeRangeToDoctors) {
                if (timeRangeToDoctor.getDateOfReceipt() == dayNumber) {
                    scheduleDoctor.add(dayNumber, timeRangeToDoctor);
                };
            }
        }
        scheduleDoctor.sort();


        Map<String, List<Boolean>> data = new LinkedHashMap<>();
        data.put("09:00 - 10:00", Arrays.asList(scheduleDoctor.isWorkTime(0, "09", "10")));
        data.put("10:00 - 11:00", Arrays.asList(scheduleDoctor.isWorkTime(0, "10", "11")));
        data.put("11:00 - 12:00", Arrays.asList(scheduleDoctor.isWorkTime(0, "11", "12")));
        data.put("12:00 - 13:00", Arrays.asList(scheduleDoctor.isWorkTime(0, "12", "13")));
        data.put("13:00 - 14:00", Arrays.asList(scheduleDoctor.isWorkTime(0, "13", "14")));
        data.put("14:00 - 15:00", Arrays.asList(scheduleDoctor.isWorkTime(0, "14", "15")));
        data.put("15:00 - 16:00", Arrays.asList(scheduleDoctor.isWorkTime(0, "15", "16")));
        data.put("16:00 - 17:00", Arrays.asList(scheduleDoctor.isWorkTime(0, "16", "17")));
        data.put("17:00 - 18:00", Arrays.asList(scheduleDoctor.isWorkTime(0, "17", "18")));
        data.put("18:00 - 19:00", Arrays.asList(scheduleDoctor.isWorkTime(0, "18", "19")));
        data.put("19:00 - 20:00", Arrays.asList(scheduleDoctor.isWorkTime(0, "19", "20")));
        data.put("20:00 - 21:00", Arrays.asList(scheduleDoctor.isWorkTime(0, "20", "21")));
        variables.put("scheduleDoctor", data);
        return templatable.template(TemplateType.SCHEDULE_DOCTOR, variables);
    }

}
