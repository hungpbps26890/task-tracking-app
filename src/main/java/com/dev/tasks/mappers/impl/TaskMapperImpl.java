package com.dev.tasks.mappers.impl;

import com.dev.tasks.domain.dto.TaskDto;
import com.dev.tasks.domain.entities.Task;
import com.dev.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public Task fromDto(TaskDto taskDto) {
        return Task.builder()
                .id(taskDto.id())
                .title(taskDto.title())
                .description(taskDto.description())
                .dueDate(taskDto.dueDate())
                .status(taskDto.status())
                .priority(taskDto.priority())
                .build();
    }

    @Override
    public TaskDto toDto(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .status(task.getStatus())
                .priority(task.getPriority())
                .build();
    }
}
