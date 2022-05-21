package com.github.annzem.natlex_rest.model;

import com.github.annzem.natlex_rest.enums.JobStatus;
import com.github.annzem.natlex_rest.enums.JobType;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class Job {

    public Job () {
    }

    public Job (JobType jobType, JobStatus jobStatus) {
        this.jobType = jobType;
        this.jobStatus = jobStatus;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated (EnumType.STRING)
    private JobType jobType;

    @Enumerated (EnumType.STRING)
    private JobStatus jobStatus;

    @CreatedDate
    private OffsetDateTime created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(JobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }
}
