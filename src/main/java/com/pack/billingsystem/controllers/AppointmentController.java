package com.pack.billingsystem.controllers;

import com.pack.billingsystem.models.Appointment;
import com.pack.billingsystem.models.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController {

    public static List<Appointment> getAllAppointments(int patientID) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();

        String query = "SELECT * FROM Appointment WHERE IDPatient = ? AND paye = false ORDER BY appointmentDate DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, patientID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setIdAppointment(rs.getInt("IDAppointment"));
                    int IDDoctor = rs.getInt("IDDoctor");
                    Doctor doctor = new DoctorController().getDoctorById(IDDoctor); // Utilisation de DoctorController
                    appointment.setDoctor(doctor);
                    appointment.setAppointmentDate(rs.getDate("appointmentDate"));
                    appointment.setPrice(rs.getDouble("price"));
                    appointment.setPaye(rs.getBoolean("paye"));
                    appointment.setService(rs.getString("service"));
                    appointments.add(appointment);
                }
            }
        }
        return appointments;
    }

    public static void updateAppointmentStatus(int IDPatient) throws SQLException {
        List<Appointment> appointments = AppointmentController.getAllAppointments(IDPatient);
        for (Appointment appointment : appointments) {
            int IDAppointment = appointment.getIdAppointment();
            String updateAppointmentQuery = "UPDATE appointment SET paye = 1 WHERE IDAppointment = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(updateAppointmentQuery)) {
                pstmt.setInt(1, IDAppointment);
                pstmt.executeUpdate();
            }
        }
    }


}

