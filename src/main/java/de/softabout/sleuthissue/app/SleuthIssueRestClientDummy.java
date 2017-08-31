package de.softabout.sleuthissue.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SleuthIssueRestClientDummy {

    @Autowired
    private Tracer tracer;

    public Mono<SleuthIssue> doNothing(SleuthIssue sleuthIssue) {
        System.out.println("I do nothing but tell you the sleuth span");
        if (this.tracer.isTracing()) {
            System.out.println("Trace-id: " + this.tracer.getCurrentSpan().traceIdString());
        } else {
            System.out.println("Seems to me, the trace has been lost!");
        }
        return Mono.just(sleuthIssue);
    }
}
