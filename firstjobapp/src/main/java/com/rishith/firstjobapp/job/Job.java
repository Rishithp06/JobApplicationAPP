package com.rishith.firstjobapp.job;


import com.rishith.firstjobapp.company.Company;
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

    @ManyToOne
    //This means:
    //
    //    Each Job must be linked to a Company.
    //    In the database, the Job table will have a company_id column (which refers to Company).
    //    When creating a new job, we must specify which company it belongs to.


    //2️⃣ Why Do We Pass company in JSON?
    //
    //When we send a request to create a job, the backend needs to know:
    //
    //    Which company this job belongs to? (since company_id is required)
    //    Instead of passing just company_id, we pass a Company object with its id.
    //
    //✅ Valid JSON request for creating a Job:
    //
    //{
    //  "title": "Software Engineer-II",
    //  "description": "Develop and maintain applications",
    //  "minSalary": "50000",
    //  "maxSalary": "100000",
    //  "location": "Bangalore",
    //  "company": {
    //    "id": 1
    //  }
    //}
    //3️⃣ How Does This Work Internally?
    //
    //    Spring Boot automatically converts the JSON into a Java object.
    //    It creates a Job object and sets its properties (title, description, etc.).
    //    Since Job has a Company object, Spring creates a Company object and sets the id as 1.
    //    Hibernate saves this in the Job table, using company_id = 1.


//    Instead of sending company: { "id": 1 }, we could just send company_id, but since our Job entity expects a Company object, we pass the whole object.
//
//💡 If we change the Job entity like this:
//
//    private Long companyId; // Instead of Company object
//
//    Then we could just send:
//
//    {
//        "title": "Software Engineer-II",
//            "description": "Develop and maintain applications",
//            "minSalary": "50000",
//            "maxSalary": "100000",
//            "location": "Bangalore",
//            "companyId": 1
//    }
//
//    But in your case, since the entity expects a Company object, we must pass the company key in JSON.
//
    //one company many jobs so yueah
    private Company company;
    //in that company.java see that mappedby=company
    //line 30 alli purple company ilva ade mappedby value


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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
