package com.plata.Plata.guild.validation.validator;

import com.plata.Plata.guild.enums.GuildVisibility;
import com.plata.Plata.guild.validation.ValidGuildVisibility;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class GuildVisibilityValidator implements ConstraintValidator<ValidGuildVisibility, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }

        return Arrays.stream(GuildVisibility.values()).anyMatch(v -> v.name().equalsIgnoreCase(value));
    }
}
