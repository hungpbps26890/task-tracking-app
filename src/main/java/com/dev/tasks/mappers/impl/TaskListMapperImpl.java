package com.dev.tasks.mappers.impl;

import com.dev.tasks.domain.dto.TaskListDto;
import com.dev.tasks.domain.entities.Task;
import com.dev.tasks.domain.entities.TaskList;
import com.dev.tasks.domain.entities.TaskStatus;
import com.dev.tasks.mappers.TaskListMapper;
import com.dev.tasks.mappers.TaskMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {

    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskList fromDto(TaskListDto taskListDto) {
        return TaskList.builder()
                .id(taskListDto.id())
                .title(taskListDto.title())
                .description(taskListDto.description())
                .tasks(Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto)
                                .toList())
                        .orElse(null))
                .build();
    }

    @Override
    public TaskListDto toDto(TaskList taskList) {
        return TaskListDto.builder()
                .id(taskList.getId())
                .title(taskList.getTitle())
                .description(taskList.getDescription())
                .count(Optional.ofNullable(taskList.getTasks())
                        .map(List::size)
                        .orElse(0))
                .progress(calculateTaskListProgress(taskList.getTasks()))
                .tasks(Optional.ofNullable(taskList.getTasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::toDto)
                                .toList())
                        .orElse(null))
                .build();
    }

    private Double calculateTaskListProgress(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) return null;

        long closedTaskCount = tasks.stream()
                .filter(task -> task.getStatus() == TaskStatus.CLOSED)
                .count();

        return (double) (closedTaskCount / tasks.size());
    }
}
