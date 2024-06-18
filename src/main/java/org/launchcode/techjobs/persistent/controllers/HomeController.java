package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");
        model.addAttribute("jobs", jobRepository.findAll());

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	    model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute(new Job());
        return "add";
    }


    //    ------One TestTaskFour Test Does not work....Add Job does work
    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model) {
//                                    @RequestParam int employerId,
//                                    @RequestParam(required = false) List<Integer> skills) {

        if (errors.hasErrors()) {
	    model.addAttribute("title", "Add Job");


            return "add";
        }
//        Optional optEmployer = employerRepository.findById(employerId);
//        if (optEmployer.isPresent()) {
//            Employer employer = (Employer) optEmployer.get();
//            newJob.setEmployer(employer);
//        }

//        model.addAttribute("skills", skillRepository.findAllById(skills));
//        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
//        newJob.setSkills(skillObjs);
//        model.addAttribute("employers", employerRepository.findAllById(skills));
//        model.addAttribute("job", jobRepository.findAllById(skills));
        jobRepository.save(newJob);
        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        Optional<Job> optJob = jobRepository.findById(jobId);
        if (optJob.isPresent()) {
            Job job = (Job) optJob.get();
            model.addAttribute("job", job);
//            model.addAttribute("skills", skillRepository.findAll());
//            model.addAttribute("skill", skillRepository.findById(jobId));
        }
            return "view";
        }
}
