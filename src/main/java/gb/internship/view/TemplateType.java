package gb.internship.view;

public enum TemplateType {
    DEMO("templates/demo.html"),
    CLIENTS("templates/clients.html"),
    EDIT_CLIENT("templates/editClient.html"),
    DOCTORS("templates/doctors.html"),
    EDIT_DOCTOR("templates/editDoctor.html"),
    SCHEDULE_DOCTOR("templates/scheduleDoctor.html"),
    SCHEDULE("templates/schedule.html");

    private final String templatePath;

    TemplateType(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getTemplatePath() {
        return templatePath;
    }
}
