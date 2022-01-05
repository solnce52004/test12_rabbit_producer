package com.example.test12.controller;

import com.example.test12.dto.MessageDto;
import com.example.test12.service.SendService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")

public class SendController {
    private final SendService service;

    @GetMapping("/q1/direct/message")
    public ResponseEntity<String> sendDir() {
        service.sendDir();
        return ResponseEntity.ok("Success emit to queue1");
    }

    @GetMapping("/q1/fanout/message")
    public ResponseEntity<String> sendFan() {
        service.sendFan();
        return ResponseEntity.ok("Success emit to queue1");
    }

    @GetMapping("/q1/topic/message")
    public ResponseEntity<String> sendTop() {
        service.sendTop();
        return ResponseEntity.ok("Success emit to queue1");
    }

    ////

    @PostMapping("/q2/message")
    public ResponseEntity<String> send(@RequestBody MessageDto msg) {
        service.send2(msg);

        return ResponseEntity.ok("Success emit to queue2");
    }
}

