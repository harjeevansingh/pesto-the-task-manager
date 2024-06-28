package com.taskmanager.dao.model;

import com.taskmanager.dao.master.TaskDAO;
import com.taskmanager.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author harjeevanSingh
 */

@Component
public class TaskModel {

    @Autowired
    private TaskDAO taskDAO;

    public List<Task> getAllTasks() {
        return taskDAO.findAllByOrderByIdDesc();
    }

    public Task getTask(Long id) {
        return taskDAO.findById(id).orElse(null);
    }

    public Task saveTask(Task task) {
        return taskDAO.save(task);
    }

    public Task getTaskByTitle(String title) {
        return taskDAO.findByTitle(title);
    }
}
