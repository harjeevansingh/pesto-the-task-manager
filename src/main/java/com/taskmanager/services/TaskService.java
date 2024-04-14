package com.taskmanager.services;

import com.taskmanager.constants.enums.exceptionCodes.RootExceptionCodes;
import com.taskmanager.dao.model.TaskModel;
import com.taskmanager.dto.ResponseDTO.ResponseDTO;
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

    public ResponseDTO<?> addTask() {
        log.info("Add Task");
        return new ResponseDTO<>("Add Task");
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
