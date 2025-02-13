package com.rishith.firstjobapp.job;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rishith.firstjobapp.job.JobService;
import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {
//JobService is injected into the controller using constructor-based dependency injection.

//Spring Boot automatically injects the JobServiceImpl instance into JobController. Here's how:
//
//    Marking JobServiceImpl as a Service
//        In JobServiceImpl.java, we added the @Service annotation:
//    This tells Spring Boot to manage this class as a Spring Bean.
//
//Dependency Injection in JobController
//
//    The JobController has a constructor that takes JobService as a parameter:
//Spring Boot automatically provides an instance of JobServiceImpl when the application runs.
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public List<Job> findAll(){
        return jobService.findAll();
        }

@PostMapping("/jobs")
    public String createJob(@RequestBody Job job){
        jobService.createJob(job);
        return "Job created";
}
}
