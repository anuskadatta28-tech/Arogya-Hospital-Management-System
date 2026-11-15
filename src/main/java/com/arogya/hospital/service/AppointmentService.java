
package com.arogya.hospital.service;

import com.arogya.hospital.model.Appointment;
import com.arogya.hospital.repository.AppointmentRepository;
import com.arogya.hospital.repository.PatientRepository;
import com.arogya.hospital.repository.DoctorRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository) {

        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public void bookAppointment(Appointment appointment) {

        if (patientRepository.getPatientById(appointment.getPatientId()) == null) {
            throw new IllegalArgumentException("Patient not found.");
        }

        if (doctorRepository.getDoctorById(appointment.getDoctorId()) == null) {
            throw new IllegalArgumentException("Doctor not found.");
        }

        if (appointment.getAppointmentDate() == null ||
                appointment.getAppointmentTime() == null) {
            throw new IllegalArgumentException("Date and time are required.");
        }

        boolean booked = appointmentRepository.isSlotBooked(
                appointment.getDoctorId(),
                appointment.getAppointmentDate().toString(),
                appointment.getAppointmentTime().toString(),
                0
        );

        if (booked) {
            throw new IllegalArgumentException(
                    "This doctor's time slot is already booked."
            );
        }

        appointment.setStatus("BOOKED");
        appointmentRepository.bookAppointment(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    public Appointment getAppointmentById(int appointmentId) {

        Appointment appointment =
                appointmentRepository.getAppointmentById(appointmentId);

        if (appointment == null) {
            throw new IllegalArgumentException("Appointment not found.");
        }

        return appointment;
    }

    public void updateAppointment(Appointment appointment) {

        getAppointmentById(appointment.getAppointmentId());

        if (patientRepository.getPatientById(appointment.getPatientId()) == null) {
            throw new IllegalArgumentException("Patient not found.");
        }

        if (doctorRepository.getDoctorById(appointment.getDoctorId()) == null) {
            throw new IllegalArgumentException("Doctor not found.");
        }

        if (appointment.getAppointmentDate() == null ||
                appointment.getAppointmentTime() == null) {
            throw new IllegalArgumentException("Date and time are required.");
        }

        boolean booked = appointmentRepository.isSlotBooked(
                appointment.getDoctorId(),
                appointment.getAppointmentDate().toString(),
                appointment.getAppointmentTime().toString(),
                appointment.getAppointmentId()
        );

        if (booked) {
            throw new IllegalArgumentException(
                    "This doctor's time slot is already booked."
            );
        }

        appointment.setStatus("BOOKED");
        appointmentRepository.updateAppointment(appointment);
    }

    public void cancelAppointment(int appointmentId) {
        getAppointmentById(appointmentId);
        appointmentRepository.cancelAppointment(appointmentId);
    }
}
