package com.taskmanager.services;

import com.taskmanager.constants.enums.TaskStatus;
import com.taskmanager.constants.enums.exceptionCodes.RootExceptionCodes;
import com.taskmanager.dao.model.TaskModel;
import com.taskmanager.dto.ResponseDTO.ResponseDTO;
import com.taskmanager.dto.request.CreateTaskRequestDTO;
import com.taskmanager.entities.Task;
import com.taskmanager.exceptions.RootException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author harjeevanSingh
 */


@Service
@Slf4j
public class TaskService {

    @Autowired
    private TaskModel taskModel;

    public ResponseDTO<?> addTask(CreateTaskRequestDTO createTaskRequestDTO) {
        log.info("Add Task - " + createTaskRequestDTO.toString());
        Task existingTask = taskModel.getTaskByTitle(createTaskRequestDTO.getTitle());
        if(existingTask != null) {
            throw new RootException(RootExceptionCodes.DUPLICATE_TASK_TITLE);
        }
        Task task = new Task();
        task.setTitle(createTaskRequestDTO.getTitle());
        task.setDescription(createTaskRequestDTO.getDescription());
        task.setDueDate(createTaskRequestDTO.getDueDate());
        task.setStatus(TaskStatus.TODO);
        task.setActive(true);
        task = taskModel.saveTask(task);
        return new ResponseDTO<>(task);
    }

    public ResponseDTO<?> getTask(Long id) {
        log.info("Get Task details: " + id);
        Task task = taskModel.getTask(id);
        if(task == null) {
            throw new RootException(RootExceptionCodes.TASK_NOT_FOUND);
        }
        return new ResponseDTO<>(taskModel.getTask(id));
    }

    public ResponseDTO<?> getAllTasks() {
        log.info("All tasks");
        return new ResponseDTO<>(taskModel.getAllTasks());
    }
}
