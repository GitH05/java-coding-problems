package com.Crud.jqGridCrud.service;

import com.Crud.jqGridCrud.dao.StudentDetailDao;
import com.Crud.jqGridCrud.model.GridResponse;
import com.Crud.jqGridCrud.model.StudentDetail;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDetailServiceImpl implements StudentDetailService {

    @Autowired
    private StudentDetailDao studentDetailDao;

    @Override
    public JSONObject getStudentDetail(StudentDetail studentDetail) {
        JSONObject jsonResponse = new JSONObject();
        String query = """
                SELECT id, name, keyName, address, department, IF(active=1, 'Yes', 'No') as active FROM students_detail
                """;
        jsonResponse = studentDetailDao.getStudentDetail(query, studentDetail);
        return jsonResponse;
    }

    @Override
    public JSONObject saveStudentDetail(StudentDetail studentDetail) {
        JSONObject jsonResponse = new JSONObject();
        try {
            JSONObject duplicateJson = new JSONObject(duplicateCheck(studentDetail));
            if (duplicateJson.getBoolean("success")) {
                String query = """
                        INSERT INTO students_detail( name, address, department, keyName, active ) 
                        VALUES (?, ?, ?, ?, ?)
                        """;
                jsonResponse = studentDetailDao.saveStudentDetail(query, studentDetail);
            }else{
            jsonResponse.put("success", false);
            jsonResponse.put("message", duplicateJson.getString("message"));
        }
    }
        catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put("Error", true);
            jsonResponse.put ("message", "Unable to update");
        }
         return jsonResponse;
    }

    @Override
    public JSONObject updateStudentDetail(Integer id, StudentDetail studentDetail) {
        JSONObject jsonResponse = new JSONObject();
        try {
            JSONObject duplicateJson = new JSONObject(duplicateCheck(studentDetail));
            if (duplicateJson.getBoolean("success")) {
                String query = """
                        UPDATE students_detail SET name=?, address=?, department=?, active=?, keyName=? WHERE id=?
                        """;
                jsonResponse = studentDetailDao.updateStudentDetail(query, studentDetail);
            }
            else {
                jsonResponse.put("success", false);
                jsonResponse.put("message", duplicateJson.getString("message"));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put("Error", true);
            jsonResponse.put ("message", "Unable to update");
        }
        return jsonResponse;
    }

    public String duplicateCheck(StudentDetail studentDetail) {
        JSONObject jsonResponse;
        try {
            String query = """
            SELECT COUNT(*) FROM students_detail
            WHERE keyName = ? AND id != ?
        """;
            jsonResponse = studentDetailDao.duplicateCheck(query, studentDetail.getKeyName(), studentDetail.getId());
        } catch (Exception e) {
            jsonResponse = new JSONObject();
            jsonResponse.put("success", false);
            jsonResponse.put("message", "Error in service: " + e.getMessage());
        }
        return jsonResponse.toString();
    }


    @Override
    public JSONObject deleteStudentDetail(Integer id, StudentDetail studentDetail) {
        JSONObject jsonResponse = new JSONObject();
        try {
            if (studentDetail.getId() > 0) {
                String query = "UPDATE students_detail SET active=? WHERE id=?";
                String isActive = "0".equals(studentDetail.getActive()) ? "Yes" : "No";
                jsonResponse = studentDetailDao.deleteStudentDetail(query, isActive, id);
            }
            else {
                jsonResponse.put("status", "error");
                jsonResponse.put("message", "id is null");
            }
        }
        catch (Exception e) {
            jsonResponse.put("error",true);
            jsonResponse.put("message","Error in deleting");
        }
        return jsonResponse;
    }

//    @Override
//    public JSONObject getStudentDetailGrid(int page, int rows) {
//        JSONObject jsonResponse = new JSONObject();
//
//        // Calculate the offset for pagination
//        int offset = (page - 1) * rows;
//
//        String query = """
//                SELECT id, name, keyName, address, department, IF(active=1, 'Yes', 'No') as active
//                FROM students_detail
//                LIMIT ? OFFSET ?
//                """;
//
//        jsonResponse = studentDetailDao.getStudentDetailGrid(query, offset, rows);
//
//        // Get total count of students for pagination (this is necessary for jqGrid to show total pages)
//        String countQuery = "SELECT COUNT(*) FROM students_detail";
//        int totalRecords = studentDetailDao.getStudentCount(countQuery);
//
//        // Add the count to the response
//        jsonResponse.put("totalRecords", totalRecords);
//        jsonResponse.put("totalPages", Math.ceil((double) totalRecords / rows));
//
//        return jsonResponse;
//    }

    @Override
    public GridResponse getPaginatedEmployees(int page, int rows) {
        // Calculate the offset based on page and row count
        int offset = (page - 1) * rows;

        // Define the SQL queries for fetching employees and counting records
        String sql = "SELECT * FROM students_detail LIMIT ? OFFSET ?";
        String countSql = "SELECT COUNT(*) FROM students_detail";

        // Fetch paginated employees
        List<StudentDetail> employees = studentDetailDao.executeQuery(sql, new Object[]{rows, offset});

        // Fetch total employee count
        int totalEmployees = studentDetailDao.executeCountQuery(countSql, new Object[]{});

        // Calculate total pages for pagination
        int totalPages = (int) Math.ceil((double) totalEmployees / rows);

        // Return the response in the expected format for jqGrid
        return new GridResponse(page, totalPages, totalEmployees, employees);
    }
}
