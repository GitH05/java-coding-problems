package com.Crud.jqGridCrud.service;

import com.Crud.jqGridCrud.model.GridResponse;
import com.Crud.jqGridCrud.model.StudentDetail;
import org.json.JSONObject;

public interface StudentDetailService {
    JSONObject getStudentDetail(StudentDetail studentDetail);

    JSONObject saveStudentDetail(StudentDetail studentDetail);

    JSONObject updateStudentDetail(Integer id, StudentDetail studentDetail);

    JSONObject deleteStudentDetail(Integer id, StudentDetail studentDetail);

    GridResponse getPaginatedEmployees(int page, int rows);

}
