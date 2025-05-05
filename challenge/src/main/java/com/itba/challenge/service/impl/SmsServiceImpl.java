package com.itba.challenge.service.impl;

import com.itba.challenge.service.SmsService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    @Value("${twilio.phone.from}")
    private String sourceNumber;

    @Value("${twilio.phone.to}")
    private String destinationNumber;

    @Override
    public void sendSms(String messageContent) {
        validateParameters(destinationNumber, messageContent);

        String number = destinationNumber;
        Message message = createMessage(number, messageContent);

        log.info("Sending sms to {} with message {}", destinationNumber, message);
    }

    private Message createMessage(String number, String content) {
        return Message.creator(
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
