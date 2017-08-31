package de.softabout.sleuthissue.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SleuthIssueService {

    public static final String ONE_AND_ONLY_RECORD_ID = "OneAndOnlyRecordId";
    @Autowired
    private Tracer tracer;

    @Autowired
    private SleuthIssueRepository sleuthIssueRepository;

    @Autowired
    private SleuthIssueRestClientDummy sleuthIssueRestClientDummy;

    public void createSleuthIssueData() {
        this.sleuthIssueRepository.deleteAll().block();
        this.sleuthIssueRepository.save(new SleuthIssue(ONE_AND_ONLY_RECORD_ID, "SleuthIssue", 100)).block();
    }

    public Mono<SleuthIssue> showIssue() {
        System.out.println("Trace-Id before reactive call to mongo: " +
                (this.tracer.isTracing() ? this.tracer.getCurrentSpan().traceIdString() : "Not tracing"));
        return this.sleuthIssueRepository.findById(ONE_AND_ONLY_RECORD_ID)
                .flatMap(this.sleuthIssueRestClientDummy::doNothing);
    }

    public Mono<SleuthIssue> thisWorks() {
        System.out.println("No-Issue-Trace-Id before reactive call to mongo: " +
                (this.tracer.isTracing() ? this.tracer.getCurrentSpan().traceIdString() : "Not tracing"));
        SleuthIssue oneAndOnlySleuthIssue = this.sleuthIssueRepository.findById(ONE_AND_ONLY_RECORD_ID).block();
        return this.sleuthIssueRestClientDummy.doNothing(oneAndOnlySleuthIssue);
    }
}
