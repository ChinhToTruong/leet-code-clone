package com.zev.application.controller;

import com.zev.application.dto.request.CodeRequest;
import com.zev.application.dto.request.TestcaseResult;
import com.zev.application.service.CodeRunnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/code-runner")
@RequiredArgsConstructor
public class CodeRunnerController {

    private final CodeRunnerService codeRunnerService;

    @PostMapping("/cpp")
    public Map<String, List<TestcaseResult>> cpp(@RequestBody CodeRequest codeRequest) throws IOException, InterruptedException {
        return codeRunnerService.runCode(codeRequest);
    }
}
