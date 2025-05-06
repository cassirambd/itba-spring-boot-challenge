package com.itba.challenge.service.impl;

import com.itba.challenge.service.SmsService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    @Value("${twilio.phone.from}")
    private String sourceNumber;

    @Value("${twilio.phone.to}")
    private String destinationNumber;

    @Async
    @Override
    public void sendSms(String messageContent) {
        try {
            log.info("Thread executing SMS: {}", Thread.currentThread().getName());

            validateParameters(destinationNumber, messageContent);
            createMessage(destinationNumber, messageContent);

            log.info("SMS sent to {} with message {}!", destinationNumber, messageContent);
        } catch (Exception e) {
            log.error("Failed to send SMS: {}", e.getMessage(), e);
        }
    }

    private void createMessage(String number, String content) {
        Message.creator(
                        new PhoneNumber(number),
                        new PhoneNumber(sourceNumber),
                        content)
                .create();
    }

    private void validateParameters(String phoneNumber, String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be empty");
        }
        if (phoneNumber == null && destinationNumber == null) {
            throw new IllegalArgumentException("A phone number must be provided");
        }
    }

}
