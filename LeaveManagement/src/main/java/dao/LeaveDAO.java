package dao;

import java.sql.*;

public class LeaveDAO {

    // 🔵 APPLY LEAVE
    public void applyLeave(int empId, String start, String end, String reason) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO leaves(emp_id, start_date, end_date, reason, status) VALUES(?,?,?,?,?)"
            );

            ps.setInt(1, empId);
            ps.setString(2, start);
            ps.setString(3, end);
            ps.setString(4, reason);
            ps.setString(5, "Pending");

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 👤 EMPLOYEE: VIEW OWN LEAVES (FIXED METHOD)
    public ResultSet getLeavesByEmployee(int empId) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT l.id, l.emp_id, e.name, l.start_date, l.end_date, l.reason, l.status " +
                "FROM leaves l " +
                "JOIN employees e ON l.emp_id = e.id " +
                "WHERE l.emp_id=? ORDER BY l.id DESC"
            );

            ps.setInt(1, empId);

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🔴 PENDING LEAVES (ADMIN)
    public ResultSet getPendingLeaves() {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT l.id, l.emp_id, e.name, l.start_date, l.end_date, l.reason, l.status " +
                "FROM leaves l " +
                "JOIN employees e ON l.emp_id = e.id " +
                "WHERE l.status='Pending'"
            );

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 🟢 APPROVED LEAVES
    public ResultSet getApprovedLeaves() {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT l.id, l.emp_id, e.name, l.start_date, l.end_date, l.reason, l.status " +
                "FROM leaves l " +
                "JOIN employees e ON l.emp_id = e.id " +
                "WHERE l.status='Approved'"
            );

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 📜 FULL HISTORY
    public ResultSet getAllLeaves() {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT l.id, l.emp_id, e.name, l.start_date, l.end_date, l.reason, l.status " +
                "FROM leaves l " +
                "JOIN employees e ON l.emp_id = e.id " +
                "ORDER BY l.id DESC"
            );

            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✏️ UPDATE STATUS
    public void updateStatus(int id, String status) {
        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "UPDATE leaves SET status=? WHERE id=?"
            );

            ps.setString(1, status);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔢 COUNTERS
    public int countPending() {
        return getCount("Pending");
    }

    public int countApproved() {
        return getCount("Approved");
    }

    public int countRejected() {
        return getCount("Rejected");
    }

    private int getCount(String status) {
        int count = 0;

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "SELECT COUNT(*) FROM leaves WHERE status=?"
            );

            ps.setString(1, status);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}