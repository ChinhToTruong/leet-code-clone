package com.zev.application.service;

import com.zev.application.dto.request.CodeRequest;
import com.zev.application.dto.request.TestcaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

@Service
public class CodeRunnerService {

    private static final Logger log = LoggerFactory.getLogger(CodeRunnerService.class);

    public Map<String, List<TestcaseResult>> runCode(CodeRequest request) throws IOException, InterruptedException {
        String sessionId = UUID.randomUUID().toString();
        Path tempDir = Files.createTempDirectory(sessionId);

        // Write source.cpp
        Path sourceFile = tempDir.resolve("source.cpp");
        Files.writeString(sourceFile, request.getSource());

        // Write testcases input
        for (int i = 0; i < request.getTestcases().size(); i++) {
            Files.writeString(tempDir.resolve("input" + i + ".txt"), request.getTestcases().get(i).getInput());
        }

        // Compile with Docker
        ProcessBuilder compileBuilder = new ProcessBuilder(
                "docker", "run", "--rm", "-v", tempDir.toAbsolutePath() + ":/code", "gcc:13.2",
                "g++", "/code/source.cpp", "-o", "/code/app.out"
        );
        Process compileProc = compileBuilder.start();
        String compileError = new String(compileProc.getErrorStream().readAllBytes(), StandardCharsets.UTF_8);
        if (compileProc.waitFor() != 0) {
            return Map.of("results", List.of(new TestcaseResult(null, null, null, false, "Compile error: " + compileError)));
        }

        // Run testcase
        List<TestcaseResult> results = new ArrayList<>();
        for (int i = 0; i < request.getTestcases().size(); i++) {
            String[] runCmd = new String[]{
                    "docker", "run", "--rm", "-v", tempDir.toAbsolutePath() + ":/code", "gcc:13.2",
                    "bash", "-c", "timeout 2s /code/app.out < /code/input" + i + ".txt"
            };
            Process runProc = new ProcessBuilder(runCmd).start();
            String actualOutput = new String(runProc.getInputStream().readAllBytes(), StandardCharsets.UTF_8).trim();
            String runError = new String(runProc.getErrorStream().readAllBytes(), StandardCharsets.UTF_8).trim();
            boolean passed = actualOutput.equals(request.getTestcases().get(i).getExpectedOutput().trim());
            TestcaseResult tcResult = new TestcaseResult();
            tcResult.setInput(request.getTestcases().get(i).getInput());
            tcResult.setExpected(request.getTestcases().get(i).getExpectedOutput().trim());
            tcResult.setActual(actualOutput);
            tcResult.setPassed(passed);
            tcResult.setError(runError);
            results.add(tcResult);
        }

        // Clean up
        Files.walk(tempDir)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);

        log.info("results: {}", results);
        return Map.of("results", results);
    }
}
