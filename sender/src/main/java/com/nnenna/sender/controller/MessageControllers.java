package com.nnenna.sender.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nnenna.sender.model.Dto.MessageDto;
import com.nnenna.sender.model.Message;
import com.nnenna.sender.model.pojo.CheckAlertPojo;
import com.nnenna.sender.model.pojo.MessagePojo;
import com.nnenna.sender.service.SenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/alert")
@RequiredArgsConstructor
public class MessageControllers {

    private final SenderService senderService;

    @PostMapping("/send")
    public ResponseEntity<MessagePojo> sendAlert(@RequestBody MessageDto messageDto) throws JsonProcessingException {
        return ResponseEntity.ok(senderService.sendMessage(messageDto));
    }

    @GetMapping("/ref/{ref}")
    public ResponseEntity<Message> getAlertByRef(@PathVariable("ref") String ref)  {
        return ResponseEntity.ok(senderService.getMessage(ref));
    }

    @GetMapping("/check")
    public ResponseEntity<CheckAlertPojo> checkAlerts()  {
        return ResponseEntity.ok(senderService.checkAlerts());
    }
}
