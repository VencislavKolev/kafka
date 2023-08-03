package org.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;

public class WikimediaChangesEventHandler implements EventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesEventHandler.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String kafkaTopic;

    public WikimediaChangesEventHandler(KafkaTemplate<String, String> kafkaTemplate, String kafkaTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
    }

    @Override
    public void onOpen() {

    }

    @Override
    public void onClosed() {

    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) {
        LOGGER.info(String.format("Event data -> %s", messageEvent.getData()));
        this.kafkaTemplate.send(this.kafkaTopic, messageEvent.getData());
    }

    @Override
    public void onComment(String comment) {

    }

    @Override
    public void onError(Throwable t) {

    }
}
