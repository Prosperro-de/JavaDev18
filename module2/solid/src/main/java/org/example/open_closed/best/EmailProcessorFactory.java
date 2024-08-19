package org.example.open_closed.best;

import org.example.open_closed.EmailType;

import java.util.List;

public class EmailProcessorFactory {
    private final List<EmailProcessor> processors;

    public EmailProcessorFactory() {
        this.processors = List.of(new MarketingEmailProcessor(), new SupportEmailProcessor(),
                new BirthdayEmailProcessor(), new SpamEmailProcessor());
    }

    public EmailProcessor getProcessor(EmailType type) {
        return processors.stream()
                .filter(processor -> processor.getType().equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No processor found for type: " + type));
    }
}
