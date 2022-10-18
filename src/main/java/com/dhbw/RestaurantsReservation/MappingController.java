package com.dhbw.RestaurantsReservation;

import com.dhbw.RestaurantsReservation.dataManagerImpl.PostgresTaskManagerImpl;
import com.dhbw.RestaurantsReservation.model.alexa.AlexaRO;
import com.dhbw.RestaurantsReservation.model.alexa.OutputSpeechRO;
import com.dhbw.RestaurantsReservation.model.alexa.ResponseRO;
import com.dhbw.RestaurantsReservation.model.user.User;
import com.dhbw.RestaurantsReservation.model.task.Task;
import com.dhbw.RestaurantsReservation.model.task.TaskList;
import com.dhbw.RestaurantsReservation.model.user.UserList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.atomic.AtomicInteger;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1.0")
public class MappingController {

    // TODO
    // The student is completely ignored.
    //

    // TODO
    // delete, update, get by id, get sorted, ...
    //
   /* @DeleteMapping(
            path = "/delete/{userID}"

    )
    public String deleteUser(@PathVariable Integer userID){
        User user = TaskList.remove(userID);
        if(user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        String answer = "User wurde gelöscht";
        return answer;
    }
*/
    // TODO
    // Set the used DataProvider (ProperyFileManager, PostgresMaganer) here and not in TaskList
    //




    @GetMapping("/task/all")
    public TaskList getTask(@RequestParam(value = "userName", defaultValue = "userID") String name) {


        TaskList taskList = new TaskList(
                                    new User("Maximilian", "Muster", "test",
                                            "Muster@mail.com",  0)
                            );
        taskList.setTasks();

        return taskList;
    }


    @PostMapping(
            path = "/user",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public User createUser(@RequestBody User user){
        UserList userList = new UserList(
                new User("test", "testsurename", "hallo",
                        "test@test.com", 3)
        );
        return user;
    }

    @GetMapping("/user/all")
    public UserList getUser(@RequestParam(value = "userName",defaultValue = "eMail") String user) {


        UserList userList = new UserList(
                new User("Maximilian", "Muster", "test",
                        "Muster@mail.com",  0)
        );
        userList.setUsers();

        return userList;
    }


    @PostMapping(
            path = "/task",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public Task createTask(@RequestBody Task task) {

        TaskList taskList = new TaskList(
                                    new User("Maxasdf", "Musterasdf", "test",
                                            "Mustdffffer@mail.com", 2));
        taskList.addTask(task);
        return task;
    }


    @PostMapping(
            path = "/task/createtable"
    )
    @ResponseStatus(HttpStatus.OK)
    public String createTask() {

        final PostgresTaskManagerImpl postgresTaskManagerImpl =
                PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
        postgresTaskManagerImpl.createTableTask();

        return "Database Table created";
    }



    @PostMapping(
            path = "/alexa",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public AlexaRO getTasks(@RequestBody AlexaRO alexaRO) {

        if (alexaRO.getRequest().getType().equalsIgnoreCase("LaunchRequest")){
            return prepareResponse(alexaRO, "Welcome to the Mosbach Task Organizer. ", false);
        }

        if(alexaRO.getRequest().getType().equalsIgnoreCase("IntentRequest")
        &&
        (alexaRO.getRequest().getIntent().getName().equalsIgnoreCase("TaskReadIntent"))
        )

        {
            //ich weiß, jemand hat gesagt: Read all my tasks.
            StringBuilder outText = new StringBuilder ("Hello. You have to do the following tasks.");
            try {


                //tasks hinzufügen
                TaskList taskList = new TaskList(
                        new User("Max", "Muster", "test",
                                "Muster@email.com", 0));
                taskList.setTasks();
                AtomicInteger i = new AtomicInteger(0);
                taskList.getTasks().forEach(
                        task -> {
                            outText.append("Task number " + i.incrementAndGet() + "is:");
                            outText.append(task.getName() + "and has priority " + task.getPriority());
                        }
                );
                outText.append("Thank you for unsing your servie. ");
            }
            catch (Exception e){
                outText.append("Unfortunately, we cannot reach heroku. Our REST server is not responding");
            }
            return prepareResponse(alexaRO,outText.toString(),true);
        }
            return prepareResponse(alexaRO, "We could not help you. ", true);

    }

    private AlexaRO prepareResponse(AlexaRO alexaRO, String outText, boolean shouldEndSession) {

        alexaRO.setRequest(null);
        alexaRO.setSession(null);
        alexaRO.setContext(null);
        OutputSpeechRO outputSpeechRO = new OutputSpeechRO();
        outputSpeechRO.setType("PlainText");
        outputSpeechRO.setText(outText);
        ResponseRO response = new ResponseRO(outputSpeechRO, shouldEndSession);
        alexaRO.setResponse(response);
        return alexaRO;
    }

}
