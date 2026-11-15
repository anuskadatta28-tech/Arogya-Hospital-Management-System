
package com.arogya.hospital.repository;

import com.arogya.hospital.model.Patient;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientRepository {

    private final JdbcTemplate jdbcTemplate;

    // Constructor Injection
    public PatientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Register a new patient
    public int addPatient(Patient patient) {

        String sql = "INSERT INTO patients " +
                "(name, age, gender, phone, email, address) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                patient.getName(),
                patient.getAge(),
                patient.getGender(),
                patient.getPhone(),
                patient.getEmail(),
                patient.getAddress());
    }

    // Retrieve all patients
    public List<Patient> getAllPatients() {

        String sql = "SELECT * FROM patients";

        RowMapper<Patient> mapper = (rs, rowNum) ->
                new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address")
                );

        return jdbcTemplate.query(sql, mapper);
    }

    // Find patient by ID
    public Patient getPatientById(int id) {

        String sql = "SELECT * FROM patients WHERE patient_id = ?";

        List<Patient> patients = jdbcTemplate.query(sql,
                (rs, rowNum) -> new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address")
                ), id);

        return patients.isEmpty() ? null : patients.get(0);
    }

    // Update patient details
    public int updatePatient(Patient patient) {

        String sql = "UPDATE patients SET name=?, age=?, " +
                "gender=?, phone=?, email=?, address=? " +
                "WHERE patient_id=?";

        return jdbcTemplate.update(sql,
                patient.getName(),
                patient.getAge(),
                patient.getGender(),
                patient.getPhone(),
                patient.getEmail(),
                patient.getAddress(),
                patient.getPatientId());
    }

    // Delete patient
    public int deletePatient(int id) {

        String sql = "DELETE FROM patients WHERE patient_id=?";

        return jdbcTemplate.update(sql, id);
    }
}

