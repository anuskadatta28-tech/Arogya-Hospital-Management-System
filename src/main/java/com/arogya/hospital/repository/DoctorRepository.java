
package com.arogya.hospital.repository;

import com.arogya.hospital.model.Doctor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorRepository {

    private final JdbcTemplate jdbcTemplate;

    // Constructor Injection
    public DoctorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Add a new doctor
    public int addDoctor(Doctor doctor) {

        String sql = "INSERT INTO doctors " +
                "(name, specialization, phone, email, available_days, available_time) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getPhone(),
                doctor.getEmail(),
                doctor.getAvailableDays(),
                doctor.getAvailableTime());
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {

        String sql = "SELECT * FROM doctors";

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("available_days"),
                        rs.getString("available_time")
                ));
    }

    // Find doctor by ID
    public Doctor getDoctorById(int id) {

        String sql = "SELECT * FROM doctors WHERE doctor_id = ?";

        List<Doctor> doctors = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("name"),
                        rs.getString("specialization"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("available_days"),
                        rs.getString("available_time")
                ), id);

        return doctors.isEmpty() ? null : doctors.get(0);
    }

    // Update doctor details
    public int updateDoctor(Doctor doctor) {

        String sql = "UPDATE doctors SET name=?, specialization=?, " +
                "phone=?, email=?, available_days=?, available_time=? " +
                "WHERE doctor_id=?";

        return jdbcTemplate.update(sql,
                doctor.getName(),
                doctor.getSpecialization(),
                doctor.getPhone(),
                doctor.getEmail(),
                doctor.getAvailableDays(),
                doctor.getAvailableTime(),
                doctor.getDoctorId());
    }

    // Delete doctor
    public int deleteDoctor(int id) {

        String sql = "DELETE FROM doctors WHERE doctor_id=?";

        return jdbcTemplate.update(sql, id);
    }
}

