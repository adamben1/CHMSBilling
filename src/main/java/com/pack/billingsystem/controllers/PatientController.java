package com.pack.billingsystem.controllers;

import com.pack.billingsystem.models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientController {

    public static Patient getPatientById(int id) throws SQLException {
        Patient patient = null;

        String query = """
                SELECT p.Nom AS nom_patient, p.Prenom AS prenom_patient, p.Tel AS tel, i.Pourcentage AS assurance
                FROM Patient p
                JOIN reclamationassurance r ON p.IDPatient = r.IDPatient
                JOIN insurance i ON r.IDInsurance = i.IDInsurance
                WHERE p.IDPatient = ?
                """;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    patient = new Patient();
                    patient.setPourcentageAssurance(rs.getDouble("assurance"));
                    patient.setPrenom(rs.getString("prenom_patient"));
                    patient.setNom(rs.getString("nom_patient"));
                    patient.setTelephone(rs.getString("tel"));
                }
            }
        }
        return patient;
    }
}

