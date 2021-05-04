package gb.internship.controller;

import gb.internship.entity.Client;
import gb.internship.service.ClientService;
import gb.internship.service.ScheduleService;
import gb.internship.utils.TimeRangeHelper;
import gb.internship.view.Templatable;
import gb.internship.view.TemplateType;

import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Path("schedule")
public class ScheduleController {

    @Inject
    private Templatable templatable;
    @Inject
    private ScheduleService scheduleService;
    @Inject
    private ClientService clientService;

    @GET
    public String schedulePage(
        @QueryParam("clientId") @DefaultValue("1") int clientId,
        @QueryParam("dayOffset") @DefaultValue("0") int dayOffset) {
        Client client = clientService.getClient(clientId);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("timeRangeList", scheduleService.takeAllTimeRange());
        parameters.put("scheduleList", scheduleService.takeSchedule(client, TimeRangeHelper.takeDayPast(dayOffset)));
        parameters.put("doctorList", scheduleService.takeAllDoctors());
        parameters.put("clientId", clientId);
        parameters.put("dayOffset", dayOffset);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = formatter.format(TimeRangeHelper.toDate(TimeRangeHelper.takeDayPast(dayOffset)));
        parameters.put("currentDate", currentDate);

        return templatable.template(TemplateType.SCHEDULE, parameters);
    }

}
