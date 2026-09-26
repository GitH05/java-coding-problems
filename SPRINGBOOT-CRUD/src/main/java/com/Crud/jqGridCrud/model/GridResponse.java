package com.Crud.jqGridCrud.model;

import java.util.List;

public class GridResponse {
    private int page;
    private int total;
    private int records;
    private List<StudentDetail> rows;

    public GridResponse(int page, int total, int records, List<StudentDetail> rows) {
        this.page = page;
        this.total = total;
        this.records = records;
        this.rows = rows;
    }

    // Getters and Setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRecords() {
        return records;
    }

    public void setRecords(int records) {
        this.records = records;
    }

    public List<StudentDetail> getRows() {
        return rows;
    }

    public void setRows(List<StudentDetail> rows) {
        this.rows = rows;
    }
}