package com.hng.task.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GenderizeResponse {
    private final String name;
    private final String gender;
    private final Float probability;
    private final Integer sampleSize;
    private final Boolean isConfident;
    private final LocalDateTime processedAt;

    public GenderizeResponse(String gender, String name, Float probability, Integer sampleSize) {
        this.gender = gender;
        this.isConfident = (probability != null && probability >= 0.7 && sampleSize >=100);
        this.name = name;
        this.probability = probability;
        this.sampleSize = sampleSize;
        this.processedAt = LocalDateTime.now();
    }
}
