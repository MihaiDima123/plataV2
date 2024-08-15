package com.plata.Plata.core.configuration.provider;

import com.plata.Plata.core.dto.BaseApiResponseDTOBuilder;

public interface ApiBuilderProvider {
    <T> BaseApiResponseDTOBuilder<T> builder();
}
