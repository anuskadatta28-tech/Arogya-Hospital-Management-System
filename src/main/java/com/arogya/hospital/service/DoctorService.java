
package com.arogya.hospital.service;

import com.arogya.hospital.model.Doctor;
import com.arogya.hospital.repository.DoctorRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    // Constructor injection
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Add a new doctor
    public void addDoctor(Doctor doctor) {

        if (doctor.getName() == null || doctor.getName().isBlank()) {
            throw new IllegalArgumentException("Doctor name is required.");
        }

        if (doctor.getSpecialization() == null ||
            doctor.getSpecialization().isBlank()) {
            throw new IllegalArgumentException("Specialization is required.");
        }

        doctorRepository.addDoctor(doctor);
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.getAllDoctors();
    }

    // Get doctor by ID
    public Doctor getDoctorById(int doctorId) {
        Doctor doctor = doctorRepository.getDoctorById(doctorId);

        if (doctor == null) {
            throw new IllegalArgumentException("Doctor not found.");
        }

        return doctor;
    }

    // Update doctor details
    public void updateDoctor(Doctor doctor) {
        getDoctorById(doctor.getDoctorId());
        doctorRepository.updateDoctor(doctor);
    }

    // Delete doctor
    public void deleteDoctor(int doctorId) {
        getDoctorById(doctorId);
        doctorRepository.deleteDoctor(doctorId);
    }
}

