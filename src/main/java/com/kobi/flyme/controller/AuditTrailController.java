package com.kobi.flyme.controller;

import com.kobi.flyme.service.AuditTrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auditTrails")

public class AuditTrailController {
    @Autowired
    AuditTrailService service;
    @GetMapping
    public ResponseEntity<?> getAllAudits(){
        return ResponseEntity.ok(service.findAll());
    }

}
