package com.example.backend.controller;

import com.example.backend.model.Town;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/default-data")
@CrossOrigin("http://localhost:3000/")
public class DefaultDataController {
    @GetMapping
    public ResponseEntity<Map<String, Object>> pobierzEnumIStr() {
        Map<String, Object> responseData = new HashMap<>();

        responseData.put("keywords", "key words, campaign, test");

        List<String> wartosciEnuma = Arrays.stream(Town.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        responseData.put("towns", wartosciEnuma);
        return ResponseEntity.ok(responseData);
    }
}
