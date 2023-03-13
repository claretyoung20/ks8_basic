package com.nnenna.receiver.controller;

import com.nnenna.receiver.service.ReceiverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/receiver")
@RequiredArgsConstructor
public class ReceiverController {

    private final ReceiverService receiverService;

    @GetMapping("/check")
    public ResponseEntity<Long> checkAlerts()  {
        return ResponseEntity.ok(receiverService.getTotalMessage());
    }
}
