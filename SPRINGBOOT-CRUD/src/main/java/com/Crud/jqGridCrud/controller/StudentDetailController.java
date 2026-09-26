package com.Crud.jqGridCrud.controller;

import com.Crud.jqGridCrud.model.GridResponse;
import com.Crud.jqGridCrud.model.StudentDetail;
import com.Crud.jqGridCrud.service.StudentDetailService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentDetailController {

    @Autowired
    private StudentDetailService studentDetailService;

    @PostMapping(value = "/save-student-detail", produces = "application/json")
    public String saveStudentDetail(@ModelAttribute StudentDetail studentDetail) {
        JSONObject response = new JSONObject();
        response = studentDetailService.saveStudentDetail(studentDetail);
        return response.toString();
    }

    @GetMapping(value = "/get-student-detail", produces = "application/json")
    public String getStudentDetail( @ModelAttribute StudentDetail studentDetail) {
        JSONObject response = new JSONObject();
        try {
            response = studentDetailService.getStudentDetail(studentDetail);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    @PutMapping(value = "/update-student-detail", produces = "application/json")
    public String updateStudentDetail(@RequestParam Integer id,
                                      @ModelAttribute StudentDetail studentDetail) {
        JSONObject response = new JSONObject();
        response = studentDetailService.updateStudentDetail(id, studentDetail);
        return response.toString();
    }

    @DeleteMapping(value = "/delete-student-detail", produces = "application/json")
    public String deleteStudentDetail(@RequestParam Integer id, @ModelAttribute StudentDetail studentDetail) {
        JSONObject response = new JSONObject();
        response = studentDetailService.deleteStudentDetail(id, studentDetail);
        return response.toString();
    }

    //Grid
    @GetMapping("/get-student-grid")
    public GridResponse getPaginatedEmployees(
            @RequestParam int page,
            @RequestParam int rows) {
        return studentDetailService.getPaginatedEmployees(page, rows);
    }

}
