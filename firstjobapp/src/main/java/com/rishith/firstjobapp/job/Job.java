package com.rishith.firstjobapp.job;


import jakarta.persistence.*;

@Entity
//@Table(name="job_table")
public class Job {
    @Id
    //The annotation @GeneratedValue(strategy = GenerationType.IDENTITY) is used in Hibernate and JPA to specify how the primary key (ID) should be generated automatically by the database.
    //✅ @Id → Marks id as the primary key.
    //✅ @GeneratedValue(strategy = GenerationType.IDENTITY) → The database will auto-generate the primary key (Auto-Increment).
    //✅ No need to manually set the id value when inserting new records.

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //You should NOT provide an id in the request body when creating a new job.
    //The database will automatically generate a unique id when the job is saved.
    //If you include an id, Hibernate might try to insert a row with a pre-existing id, causing a duplicate key constraint violation or an org.hibernate.PersistentObjectException.
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    //below is a default constructor
//When using Spring Boot with JPA (Hibernate) and H2 database, Hibernate needs a default (no-argument) constructor to create objects when fetching data from the database.
//If you only have a parameterized constructor, Hibernate fails to create objects when fetching records from the H2 database.
//Hibernate talks to the database and fetches data as rows (like a table in Excel).
//But Java doesn’t understand rows—it needs objects.
//
//So, Hibernate does this:
//
//    Reads a row from the database (like a job with ID, title, and salary).
//    Creates a new Job object in Java.
//    Fills in the details from the database row.
//
//But Hibernate can’t create the object if there’s no default constructor!
//It needs an empty constructor to first create an object and then set values inside it.
    public Job() {
    }

    public Job(Long id, String title, String description, String minSalary, String maxSalary, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
