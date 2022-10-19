package com.dhbw.restaurantsReservation.dataManager;

import com.dhbw.restaurantsReservation.model.student.Student;
import com.dhbw.restaurantsReservation.model.task.Task;

import java.util.Collection;

public interface TaskManager {

    Collection<Task> getAllTasks(Student student);
    void addTask(Task task, Student student);

    // TODO
    // removeTask, getTasksInOrder, getTaskByTaskID, ...

    // TODO
    // Make the TaskManager handling students.

}
