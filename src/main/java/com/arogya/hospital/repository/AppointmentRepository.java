
package com.arogya.hospital.repository;

import com.arogya.hospital.model.Appointment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppointmentRepository {

    private final JdbcTemplate jdbcTemplate;

    // Constructor Injection
    public AppointmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Check whether a doctor already has a booking
    public boolean isSlotBooked(int doctorId, String date,
                                String time, int excludeAppointmentId) {

        String sql = "SELECT COUNT(*) FROM appointments " +
                "WHERE doctor_id = ? " +
                "AND appointment_date = ? " +
                "AND appointment_time = ? " +
                "AND status = 'BOOKED' " +
                "AND appointment_id != ?";

        Integer count = jdbcTemplate.queryForObject(
                sql, Integer.class,
                doctorId, date, time, excludeAppointmentId
        );

        return count != null && count > 0;
    }

    // Book a new appointment
    public int bookAppointment(Appointment appointment) {

        String sql = "INSERT INTO appointments " +
                "(patient_id, doctor_id, appointment_date, " +
                "appointment_time, status) " +
                "VALUES (?, ?, ?, ?, 'BOOKED')";

        return jdbcTemplate.update(sql,
                appointment.getPatientId(),
                appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime());
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {

        String sql = "SELECT * FROM appointments";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getDate("appointment_date").toLocalDate(),
                        rs.getTime("appointment_time").toLocalTime(),
                        rs.getString("status")
                ));
    }

    // Find appointment by ID
    public Appointment getAppointmentById(int id) {

        String sql = "SELECT * FROM appointments WHERE appointment_id = ?";

        List<Appointment> appointments = jdbcTemplate.query(
                sql, (rs, rowNum) ->
                        new Appointment(
                                rs.getInt("appointment_id"),
                                rs.getInt("patient_id"),
                                rs.getInt("doctor_id"),
                                rs.getDate("appointment_date").toLocalDate(),
                                rs.getTime("appointment_time").toLocalTime(),
                                rs.getString("status")
                        ), id);

        return appointments.isEmpty() ? null : appointments.get(0);
    }

    // Modify an appointment
    public int updateAppointment(Appointment appointment) {

        String sql = "UPDATE appointments SET " +
                "patient_id = ?, doctor_id = ?, " +
                "appointment_date = ?, appointment_time = ? " +
                "WHERE appointment_id = ? AND status = 'BOOKED'";

        return jdbcTemplate.update(sql,
                appointment.getPatientId(),
                appointment.getDoctorId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getAppointmentId());
    }

    // Cancel an appointment
    public int cancelAppointment(int id) {

        String sql = "UPDATE appointments SET status = 'CANCELLED' " +
                "WHERE appointment_id = ? AND status = 'BOOKED'";

        return jdbcTemplate.update(sql, id);
    }
}
