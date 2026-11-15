
package com.arogya.hospital.service;

import com.arogya.hospital.model.Patient;
import com.arogya.hospital.repository.PatientRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    // Constructor Injection
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // Register a patient
    public String registerPatient(Patient patient) {

        if (patient.getName() == null ||
                patient.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "Patient name cannot be empty");
        }

        if (patient.getAge() <= 0 || patient.getAge() > 120) {
            throw new IllegalArgumentException(
                    "Please enter a valid patient age");
        }

        if (patient.getPhone() == null ||
                !patient.getPhone().matches("\\d{10,15}")) {
            throw new IllegalArgumentException(
                    "Please enter a valid phone number");
        }

        int result = patientRepository.addPatient(patient);

        return result > 0
                ? "Patient registered successfully!"
                : "Patient registration failed!";
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.getAllPatients();
    }

    // Search patient by ID
    public Patient getPatientById(int id) {

        Patient patient = patientRepository.getPatientById(id);

        if (patient == null) {
            throw new IllegalArgumentException(
                    "Patient not found with ID: " + id);
        }

        return patient;
    }

    // Update patient details
    public String updatePatient(Patient patient) {

        getPatientById(patient.getPatientId());

        if (patient.getName() == null ||
                patient.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "Patient name cannot be empty");
        }

        int result = patientRepository.updatePatient(patient);

        return result > 0
                ? "Patient details updated successfully!"
                : "Patient update failed!";
    }

    // Delete patient
    public String deletePatient(int id) {

        getPatientById(id);

        int result = patientRepository.deletePatient(id);

        return result > 0
                ? "Patient deleted successfully!"
                : "Patient deletion failed!";
    }
}
