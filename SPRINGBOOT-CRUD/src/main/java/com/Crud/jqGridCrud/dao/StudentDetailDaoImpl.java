package com.Crud.jqGridCrud.dao;

import com.Crud.jqGridCrud.model.StudentDetail;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class StudentDetailDaoImpl implements StudentDetailDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public JSONObject getStudentDetail(String query, StudentDetail studentDetail) {
        JSONObject jsonResponse = new JSONObject();
        try {
            List<StudentDetail> allStudentsList = jdbcTemplate.query(query,
                    new BeanPropertyRowMapper<StudentDetail>(StudentDetail.class));
            System.out.println("all student:- "+allStudentsList);
            if(!allStudentsList.isEmpty()) {
                jsonResponse.put("success", true);
                jsonResponse.put("message", "Fetched student details successfully");
                jsonResponse.put("data", allStudentsList);
            }
            else {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Fetched student details failed");
            }
        } catch (Exception e) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error fetching students: " + e.getMessage());
        }
        return jsonResponse;
    }

    @Override
    public JSONObject saveStudentDetail(String query, StudentDetail studentDetail) {
        JSONObject jsonResponse = new JSONObject();
        try {
            int count =  jdbcTemplate.update(query, studentDetail.getName(),
                    studentDetail.getAddress(),
                    studentDetail.getDepartment(),
                    studentDetail.getKeyName(),
                    studentDetail.getActive());
            if(count > 0) {
                jsonResponse.put("success", true);
                jsonResponse.put("message", "Successfully saved student detail");
            }
            else {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Failed to save student detail");
            }
        } catch (Exception e) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error fetching students: " + e.getMessage());
        }
        return jsonResponse;
    }

    @Override
    public JSONObject updateStudentDetail(String query, StudentDetail studentDetail) {
        JSONObject jsonResponse = new JSONObject();
        try {
            int count =  jdbcTemplate.update(query, studentDetail.getName(),
                    studentDetail.getAddress(),
                    studentDetail.getDepartment(),
                    studentDetail.getActive(),
                    studentDetail.getKeyName(),
                    studentDetail.getId());
            if(count > 0) {
                jsonResponse.put("success", true);
                jsonResponse.put("message", "Successfully updated student detail");
            }
            else {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Failed to updated student detail");
            }
        } catch (Exception e) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error fetching students: " + e.getMessage());
        }
        return jsonResponse;
    }

    @Override
    public JSONObject deleteStudentDetail(String query, String isActive, Integer id) {
        JSONObject jsonResponse = new JSONObject();
        try {
            String action = "Yes".equalsIgnoreCase(isActive) ? "activated" : "deactivated";
            int count = jdbcTemplate.update(query, isActive, id);
            if (count > 0) {
                jsonResponse.put("success", true);
                jsonResponse.put("message", "Student successfully " + action);
            } else {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Failed to " + action + " student");
            }
        } catch (Exception e) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error deleting student detail: " + e.getMessage());
        }
        return jsonResponse;
    }

    @Override
    public JSONObject duplicateCheck(String query, String keyName, int id) {
        JSONObject jsonResponse = new JSONObject();
        try {
            Integer count = jdbcTemplate.queryForObject(query, Integer.class, keyName, id);
            if (count == null || count == 0) {
                jsonResponse.put("success", true);
                jsonResponse.put("message", "No duplicate");
            } else {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "Duplicate found: " + keyName);
            }
        } catch (Exception e) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error checking duplicate: " + e.getMessage());
        }
        return jsonResponse;
    }

//    @Override
//    public int getStudentCount(String countQuery) {
//        try {
//            return jdbcTemplate.queryForObject(countQuery, Integer.class);
//        } catch (Exception e) {
//            return 0;  // In case of an error, return 0 as a fallback
//        }
//    }
//
//    @Override
//    public JSONObject getStudentDetailGrid(String query, int offset, int rows) {
//        JSONObject jsonResponse = new JSONObject();
//        try {
//            // Execute the query with LIMIT and OFFSET
//            List<StudentDetail> allStudentsList = jdbcTemplate.query(query,
//                    new Object[] { rows, offset },
//                    new BeanPropertyRowMapper<>(StudentDetail.class));
//
//            if (!allStudentsList.isEmpty()) {
//                jsonResponse.put("success", true);
//                jsonResponse.put("message", "Fetched student details successfully");
//                jsonResponse.put("data", allStudentsList);
//            } else {
//                jsonResponse.put("success", false);
//                jsonResponse.put("message", "No student records found");
//            }
//        } catch (Exception e) {
//            jsonResponse.put("success", false);
//            jsonResponse.put("message", "Error fetching students: " + e.getMessage());
//        }
//        return jsonResponse;
//    }

    //Grid
    @Override
    public List<StudentDetail> executeQuery(String sql, Object[] params) {
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(StudentDetail.class));
    }

    @Override
    public int executeCountQuery(String sql, Object[] params) {
        return jdbcTemplate.queryForObject(sql, params, Integer.class);
    }
}
