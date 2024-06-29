package com.taskmanager.dao.master;

import com.taskmanager.constants.enums.TaskStatus;
import com.taskmanager.entities.Task;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskDAOTest {

    @Autowired
    private TaskDAO taskDAO;

    @Test
    public void TaskDAO_save_returnSavedTaskList(){
        //Arrange
        Task task = new Task(
                "Title",
                "Description",
                TaskStatus.TODO,
                true,
                LocalDateTime.now());

        //Act
        Task savedTask = taskDAO.save(task);

        //Assert
        Assertions.assertThat(savedTask).isNotNull();
        Assertions.assertThat(savedTask.getId()).isGreaterThan(0);
        Assertions.assertThat(savedTask.getTitle()).isEqualTo("Title");
    }

    @Test
    public void TaskDAO_findById_returnTask(){
        //Arrange
        Task task = new Task(
                "Title",
                "Description",
                TaskStatus.TODO,
                true,
                LocalDateTime.now());

        //Act
        taskDAO.save(task);
        Task fetchedTask = taskDAO.findById(task.getId()).get();

        //Assert
        Assertions.assertThat(fetchedTask).isNotNull();
    }

    @Test
    public void TaskDAO_findAllByOrderByIdDesc_returnTaskList(){
        //Arrange
        Task task1 = new Task(
                "Title1",
                "Description1",
                TaskStatus.TODO,
                true,
                LocalDateTime.now());

        Task task2 = new Task(
                "Title2",
                "Description2",
                TaskStatus.IN_PROGRESS,
                true,
                LocalDateTime.now());

        //Act
        taskDAO.save(task1);
        taskDAO.save(task2);

        List<Task> fetchedTasks = taskDAO.findAllByOrderByIdDesc();

        //Assert
        Assertions.assertThat(taskDAO.findAllByOrderByIdDesc()).isNotNull();
        Assertions.assertThat(taskDAO.findAllByOrderByIdDesc().size()).isEqualTo(2);
        Assertions.assertThat(fetchedTasks.get(0).getId()).isGreaterThan(fetchedTasks.get(1).getId());
    }

}
