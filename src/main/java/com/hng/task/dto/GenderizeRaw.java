package com.hng.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GenderizeRaw{
    private Integer count;
    private String name;
    private String gender;
    private Float probability;
}
