package com.Crud.jqGridCrud.dao;

import com.Crud.jqGridCrud.model.StudentDetail;
import org.json.JSONObject;

import java.util.List;

public interface StudentDetailDao {
    JSONObject getStudentDetail(String query, StudentDetail studentDetail);

    JSONObject saveStudentDetail(String query, StudentDetail studentDetail);

    JSONObject updateStudentDetail(String query, StudentDetail studentDetail);

    JSONObject deleteStudentDetail(String query, String active, Integer id);

    JSONObject duplicateCheck(String query, String keyName, int id);


    List<StudentDetail> executeQuery(String sql, Object[] params);
    int executeCountQuery(String sql, Object[] params);
}
