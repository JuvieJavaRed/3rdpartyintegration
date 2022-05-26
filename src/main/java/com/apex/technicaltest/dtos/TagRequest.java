package com.apex.technicaltest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TagRequest {
    private List<String> tags;
}
