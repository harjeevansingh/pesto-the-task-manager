package com.taskmanager.controllers.v1;

import com.taskmanager.dto.request.CreateTaskRequestDTO;
import com.taskmanager.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author harjeevanSingh
 */

@RestController
@RequestMapping("/api/v1/task")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/")
    public ResponseEntity<?> addTask(@Validated @RequestBody CreateTaskRequestDTO createTaskRequestDTO) {
        log.info("Request received to add Task");
        return ResponseEntity.ok(taskService.addTask(createTaskRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> addTask(@PathVariable Long id) {
        log.info("Request received for Task details: " + id);
        return ResponseEntity.ok(taskService.getTask(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getTasks() {
        log.info("Request received for all tasks");
        return ResponseEntity.ok(taskService.getAllTasks());
    }
}
