package com.plata.Plata.core.services;

public interface MessageTranslator {
    String translate(String message);
    String translate(String message, String[] args);
}
