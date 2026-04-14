package com.hng.task.controller;

import com.hng.task.dto.GenderizeResponse;
import com.hng.task.service.GenderizeService;
import com.hng.task.util.ApiResponse;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class GenderizeController {
    private final GenderizeService genderizeService;

    @GetMapping("/api/classify")
    public ResponseEntity<ApiResponse> classify(@RequestParam @NotBlank(message = "name is required") String name) {
        GenderizeResponse genderizeResponse = genderizeService.checkGenderByName(name);

        return ResponseEntity.ok(ApiResponse.success(genderizeResponse));
    }
}
