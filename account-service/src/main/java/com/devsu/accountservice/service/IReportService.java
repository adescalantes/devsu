package com.devsu.accountservice.service;

import com.devsu.accountservice.model.response.ReportResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IReportService {

    ReportResponse findByCriteria(LocalDate startDate, LocalDate endDate, long clientId);
}
