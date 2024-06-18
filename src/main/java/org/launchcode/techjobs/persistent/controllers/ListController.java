package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.launchcode.techjobs.persistent.models.JobData;

import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "list")
public class ListController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private EmployerRepository employerRepository;

    static HashMap<String, String> columnChoices = new HashMap<>();

    public ListController() {

        columnChoices.put("all", "All");
        columnChoices.put("employer", "Employer");
        columnChoices.put("skill", "Skill");

    }

    @RequestMapping("")
    public String list(Model model) {

//        model.addAttribute("jobs", jobRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("employers", employerRepository.findAll());

        return "list";
    }

    @RequestMapping(value = "jobs")
    public String listJobsByColumnAndValue(Model model, @RequestParam String column, @RequestParam String value) {
        Iterable<Job> jobs;
        if (column.toLowerCase().equals("all")) {
//        if (column.equalsIgnoreCase(column)){
            jobs = jobRepository.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
//            jobs = JobData.findByColumnAndValue(column, value, jobRepository.findAll());
            model.addAttribute("title", "Jobs with " + columnChoices.get(column) + ": " + value);

            jobs = JobData.findByColumnAndValue(column, value, jobRepository.findAll());
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("employers", employerRepository.findAll());
//        model.addAttribute("jobs", jobs);
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute("job.skills", jobRepository.findAll());

        return "list-jobs";
    }

}
