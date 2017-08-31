package de.softabout.sleuthissue.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SleuthIssueApi {

    @Autowired
    private SleuthIssueService sleuthIssueService;

    @RequestMapping(method = RequestMethod.POST, path="/")
    public ResponseEntity initData() {
        this.sleuthIssueService.createSleuthIssueData();
        return ResponseEntity.ok("Initialized Data");
    }

    @RequestMapping(method = RequestMethod.GET, path="/issue")
    public ResponseEntity showIssue() {
        return ResponseEntity.ok(this.sleuthIssueService.showIssue().block());
    }

    @RequestMapping(method = RequestMethod.GET, path="/noissue")
    public ResponseEntity noIssue() {
        return ResponseEntity.ok(this.sleuthIssueService.thisWorks().block());
    }
}
