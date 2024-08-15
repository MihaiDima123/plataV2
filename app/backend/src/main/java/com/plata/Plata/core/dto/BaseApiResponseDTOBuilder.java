package com.plata.Plata.core.dto;

import com.plata.Plata.core.services.MessageTranslator;

public class BaseApiResponseDTOBuilder<T> {
    private final MessageTranslator translator;
    private String message;
    private T data;

    public BaseApiResponseDTOBuilder(MessageTranslator translator) {
        this.translator = translator;
    }

    public BaseApiResponseDTOBuilder<T> message(String message) {
        return message(message, null);
    }

    public BaseApiResponseDTOBuilder<T> message(String message, String[] args) {
        if (translator != null && message != null) {
            this.message = translator.translate(message, args);
            return this;
        }

        this.message = message;
        return this;
    }

    public BaseApiResponseDTOBuilder<T> data(T data) {
        this.data = data;
        return this;
    }

    public BaseApiResponseDTO<T> build() {
        final var response = new BaseApiResponseDTO<T>();
        response.setMessage(message);
        response.setData(data);

        return response;
    }
}
