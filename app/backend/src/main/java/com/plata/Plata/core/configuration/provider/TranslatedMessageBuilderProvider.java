package com.plata.Plata.core.configuration.provider;

import com.plata.Plata.core.dto.BaseApiResponseDTOBuilder;
import com.plata.Plata.core.services.MessageTranslator;
import org.springframework.stereotype.Component;

@Component
public class TranslatedMessageBuilderProvider implements ApiBuilderProvider {
    private final MessageTranslator translator;

    public TranslatedMessageBuilderProvider(MessageTranslator translator) {
        this.translator = translator;
    }
    
    @Override
    public <T> BaseApiResponseDTOBuilder<T> builder() {
        return new BaseApiResponseDTOBuilder<>(translator);
    }
}
