
package com.arogya.hospital.controller;

import com.arogya.hospital.model.Appointment;
import com.arogya.hospital.service.AppointmentService;
import com.arogya.hospital.service.PatientService;
import com.arogya.hospital.service.DoctorService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    public AppointmentController(
            AppointmentService appointmentService,
            PatientService patientService,
            DoctorService doctorService) {

        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping
    public String getAllAppointments(Model model) {
        model.addAttribute("appointments",
                appointmentService.getAllAppointments());
        return "appointments";
    }

    @GetMapping("/new")
    public String showBookingForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());

        return "appointment-form";
    }

    @PostMapping("/save")
    public String bookAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.bookAppointment(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("appointment",
                appointmentService.getAppointmentById(id));
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("doctors", doctorService.getAllDoctors());

        return "appointment-form";
    }

    @PostMapping("/update")
    public String updateAppointment(@ModelAttribute Appointment appointment) {
        appointmentService.updateAppointment(appointment);
        return "redirect:/appointments";
    }

    @PostMapping("/cancel/{id}")
    public String cancelAppointment(@PathVariable int id) {
        appointmentService.cancelAppointment(id);
        return "redirect:/appointments";
    }
}
