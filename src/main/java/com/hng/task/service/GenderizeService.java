package com.hng.task.service;

import com.hng.task.dto.GenderizeRaw;
import com.hng.task.dto.GenderizeResponse;
import com.hng.task.exceptions.CustomNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class GenderizeService {

    private final RestTemplate restTemplate;

    @Value("${genderize.api.base-url}")
    private String BASE_URL;

    public GenderizeResponse checkGenderByName(String name){

        URI uri = UriComponentsBuilder.fromUriString(BASE_URL)
                        .queryParam("name", name.trim())
                        .build().toUri();

        GenderizeRaw genderizeRaw = restTemplate.getForObject(uri, GenderizeRaw.class);
        assert genderizeRaw !=null;

        if(genderizeRaw.getGender() == null || genderizeRaw.getCount() == 0){
            throw new CustomNotFoundException("No prediction available for the provided name");
        }

        return new GenderizeResponse(
                genderizeRaw.getName(),
                genderizeRaw.getGender(),
                genderizeRaw.getProbability(),
                genderizeRaw.getCount()
        );
    }
}
