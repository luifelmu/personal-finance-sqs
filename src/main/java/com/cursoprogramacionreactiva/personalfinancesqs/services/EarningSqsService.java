package com.cursoprogramacionreactiva.personalfinancesqs.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.cursoprogramacionreactiva.personalfinancesqs.models.Earning;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EarningSqsService {
  private final AmazonSQS sqsClient;

  private String getQueueUrl() {
    return sqsClient.getQueueUrl("earnings").getQueueUrl();
  }

  private MessageAttributeValue attributeValue(String value) {
    return new MessageAttributeValue().withDataType("String").withStringValue(value);
  }

  public String publishQueueMessage(Earning earning) {
    Map<String, MessageAttributeValue> attributes = new HashMap<>();

    attributes.put("id", attributeValue(String.valueOf(earning.id())));
    attributes.put("name", attributeValue(earning.name()));
    attributes.put("amount", attributeValue(String.valueOf(earning.amount())));
    attributes.put("date", attributeValue(earning.date()));
    attributes.put("account", attributeValue(String.valueOf(earning.account())));
    attributes.put("category", attributeValue(String.valueOf(earning.category())));

    SendMessageRequest request = new SendMessageRequest()
        .withQueueUrl(this.getQueueUrl())
        .withMessageBody(earning.name())
        .withMessageAttributes(attributes);

    return sqsClient.sendMessage(request).getMessageId();
  }
}
