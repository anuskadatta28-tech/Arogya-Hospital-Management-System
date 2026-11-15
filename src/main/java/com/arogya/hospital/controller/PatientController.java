
package com.arogya.hospital.controller;

import com.arogya.hospital.model.Patient;
import com.arogya.hospital.service.PatientService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    // Constructor injection
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Display all patients
    @GetMapping
    public String getAllPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients";
    }

    // Show registration form
    @GetMapping("/new")
    public String showRegistrationForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "patient-form";
    }

    // Register a patient
    @PostMapping("/save")
    public String registerPatient(@ModelAttribute Patient patient) {
        patientService.registerPatient(patient);
        return "redirect:/patients";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("patient", patientService.getPatientById(id));
        return "patient-form";
    }

    // Update patient
    @PostMapping("/update")
    public String updatePatient(@ModelAttribute Patient patient) {
        patientService.updatePatient(patient);
        return "redirect:/patients";
    }

    // Delete patient
    @PostMapping("/delete/{id}")
    public String deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }
}
