package com.shabab.UniversityManagementSystem.admin.restcontroller;

import com.shabab.UniversityManagementSystem.admin.model.University;
import com.shabab.UniversityManagementSystem.admin.model.University;
import com.shabab.UniversityManagementSystem.admin.service.UniversityService;
import com.shabab.UniversityManagementSystem.admin.service.UniversityService;
import com.shabab.UniversityManagementSystem.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project: UniversityManagementSystem-SpringBoot
 * Author: Shabab
 * Created on: 26/08/2024
 */

@RestController
@RequestMapping("/api/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @GetMapping("/getAll")
    public ApiResponse getAll() {
        return universityService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResponse getById(@PathVariable Long id) {
        return universityService.getById(id);
    }

    @PostMapping("/save")
    public ApiResponse save(@Valid @RequestBody University university) {
        return universityService.save(university);
    }

    @PutMapping("/update")
    public ApiResponse update(@Valid @RequestBody University university) {
        return universityService.update(university);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable Long id) {
        return universityService.deleteById(id);
    }
}
