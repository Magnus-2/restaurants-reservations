package com.dhbw.restaurantsReservation;

import com.dhbw.restaurantsReservation.dataManagerImpl.PostgresTaskManagerImpl;
import com.dhbw.restaurantsReservation.model.alexa.AlexaRO;
import com.dhbw.restaurantsReservation.model.alexa.OutputSpeechRO;
import com.dhbw.restaurantsReservation.model.alexa.ResponseRO;
import com.dhbw.restaurantsReservation.model.student.Student;
import com.dhbw.restaurantsReservation.model.task.Task;
import com.dhbw.restaurantsReservation.model.task.TaskList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    // TODO
    // Set the used DataProvider (ProperyFileManager, PostgresMaganer) here and not in TaskList
    //


    @GetMapping("/task/all")
    public TaskList getTasks(@RequestParam(value = "name", defaultValue = "Student") String name) {


        TaskList taskList = new TaskList(
                                    new Student("me", name)
                            );
        taskList.setTasks();

        return taskList;
    }


    @PostMapping(
            path = "/task",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public Task createTask(@RequestBody Task task) {

        TaskList taskList = new TaskList(
                                    new Student("me", "ignore")
                            );
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
                        new Student("me", "me")
                );
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
