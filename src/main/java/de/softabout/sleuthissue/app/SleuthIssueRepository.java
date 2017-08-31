package de.softabout.sleuthissue.app;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SleuthIssueRepository extends ReactiveMongoRepository<SleuthIssue, String> {
}
