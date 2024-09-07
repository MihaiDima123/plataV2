package com.plata.Plata.guild.validation;

import com.plata.Plata.guild.validation.validator.GuildVisibilityValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GuildVisibilityValidator.class)
public @interface ValidGuildVisibility {
    String message() default "Invalid guild visibility";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
