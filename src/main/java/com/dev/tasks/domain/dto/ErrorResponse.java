package com.dev.tasks.domain.dto;

import lombok.Builder;

@Builder
public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
