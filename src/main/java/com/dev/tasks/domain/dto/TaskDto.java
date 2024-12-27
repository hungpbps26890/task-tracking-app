package com.dev.tasks.domain.dto;

import com.dev.tasks.domain.entities.TaskPriority;
import com.dev.tasks.domain.entities.TaskStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskStatus status,
        TaskPriority priority
) {
}
