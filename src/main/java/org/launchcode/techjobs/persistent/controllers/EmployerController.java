package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private JobRepository jobRepository;

    private SkillRepository skillRepository;

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("name", "Create Event");
            return "employers/add";
        }

       employerRepository.save(newEmployer);
        return "redirect:../";
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable int employerId) {

        Optional optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("Employer", "View Job");
            model.addAttribute("employer", employer);
            Optional optJob = jobRepository.findById(employerId);
            if (!optJob.isEmpty()) {
                Job jobs = (Job) optJob.get();
                model.addAttribute("jobs", jobs);
            }
//            model.addAttribute("skills", skillRepository.findAll());
//            model.addAttribute(new Employer());
            return "employers/view";
        } else {
            employerRepository.findById(employerId);
            return "redirect:../";
        }
    }
        @GetMapping("/")
//        @RequestMapping("/")
            public String index (Model model){
//                model.addAttribute("employer", "employers");
                model.addAttribute("employers", employerRepository.findAll());
                return "index";
            }


}
