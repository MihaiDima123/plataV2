package com.plata.Plata.core.services;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class TranslatorService implements MessageTranslator {
    private final MessageSource messageSource;

    public TranslatorService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String translate(String text) {
        return translate(text, null);
    }

    public String translate(String text, String[] args) {
        System.out.println(text);
        System.out.println(LocaleContextHolder.getLocale().getLanguage());
        return messageSource.getMessage(
                text,
                args,
                text, // The default one
                LocaleContextHolder.getLocale()
        );
    }
}
