package br.com.fiap.tech_challenge.adapters.driver.api.dto;

import br.com.fiap.tech_challenge.adapters.driver.api.validator.EnumNamePattern;
import br.com.fiap.tech_challenge.core.domain.models.enums.CategoryProductEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDTO(
        @Schema(example = "Cheese Hamburger")
        @NotBlank
        String name,

        @Schema(example = "MAIN_COURSE")
        @EnumNamePattern(regexp = "MAIN_COURSE|SIDE_DISH|DRINK|DESSERT")
        @NotNull
        CategoryProductEnum category,

        @Schema(example = "25.90")
        @DecimalMin(value = "0.00")
        @NotNull
        BigDecimal price,

        @Schema(example = "Hamburger with Cheese")
        @NotBlank
        String description
) {
}