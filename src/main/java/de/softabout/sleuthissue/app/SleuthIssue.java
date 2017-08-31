package de.softabout.sleuthissue.app;


import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class SleuthIssue {

    @Id
    private String id;

    private String issueName;
    private int severity;

    public SleuthIssue() {
    }

    public SleuthIssue(String id, String issueName, int severity) {
        this.id = id;
        this.issueName = issueName;
        this.severity = severity;
    }

}
