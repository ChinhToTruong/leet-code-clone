package com.zev.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestcaseResult {
    private String input;
    private String expected;
    private String actual;
    private boolean passed;
    private String error;
}
