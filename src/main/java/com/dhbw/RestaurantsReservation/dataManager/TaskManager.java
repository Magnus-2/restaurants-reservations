package com.dhbw.RestaurantsReservation.dataManager;

import com.dhbw.RestaurantsReservation.model.student.Student;
import com.dhbw.RestaurantsReservation.model.task.Task;

import java.util.Collection;

public interface TaskManager {

    Collection<Task> getAllTasks(Student student);
    void addTask(Task task, Student student);

    // TODO
    // removeTask, getTasksInOrder, getTaskByTaskID, ...

    // TODO
    // Make the TaskManager handling students.

}
