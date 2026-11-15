
package com.arogya.hospital.controller;

import com.arogya.hospital.model.Doctor;
import com.arogya.hospital.service.DoctorService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    // Constructor injection
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Display all doctors
    @GetMapping
    public String getAllDoctors(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctors";
    }

    // Show doctor registration form
    @GetMapping("/new")
    public String showDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "doctor-form";
    }

    // Add a new doctor
    @PostMapping("/save")
    public String addDoctor(@ModelAttribute Doctor doctor) {
        doctorService.addDoctor(doctor);
        return "redirect:/doctors";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("doctor", doctorService.getDoctorById(id));
        return "doctor-form";
    }

    // Update doctor details
    @PostMapping("/update")
    public String updateDoctor(@ModelAttribute Doctor doctor) {
        doctorService.updateDoctor(doctor);
        return "redirect:/doctors";
    }

    // Delete doctor
    @PostMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable int id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctors";
    }
}
