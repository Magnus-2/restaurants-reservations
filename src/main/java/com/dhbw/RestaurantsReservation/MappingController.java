package com.dhbw.RestaurantsReservation;

import com.dhbw.RestaurantsReservation.dataManagerImpl.PostgresTaskManagerImpl;
import com.dhbw.RestaurantsReservation.model.restaurant.Restaurant;
import com.dhbw.RestaurantsReservation.model.alexa.AlexaRO;
import com.dhbw.RestaurantsReservation.model.alexa.OutputSpeechRO;
import com.dhbw.RestaurantsReservation.model.alexa.ResponseRO;
import com.dhbw.RestaurantsReservation.model.user.User;
import com.dhbw.RestaurantsReservation.model.task.Task;
import com.dhbw.RestaurantsReservation.model.task.TaskList;
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


    @GetMapping("/task/allUser")
    public TaskList getTasksUser(@RequestParam(value = "name", defaultValue = "User") String name) {


        TaskList taskList = new TaskList(
                                    new User("me", name)
                            );
        taskList.setTasks();

        return taskList;
    }


    @PostMapping(
            path = "/task/User",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public Task createTaskUser(@RequestBody Task task) {

        TaskList taskList = new TaskList(
                                    new User("me", "ignore")
                            );
        taskList.addTask(task);
        return task;
    }


    @PostMapping(
            path = "/task/createtable/User"
    )
    @ResponseStatus(HttpStatus.OK)
    public String createTaskUser() {

        final PostgresTaskManagerImpl postgresTaskManagerImpl =
                PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
        postgresTaskManagerImpl.createTableTask(true);

        return "Database Table created";
    }



    @GetMapping("/allRestaurants")
    public TaskList getTasksRestaurants(@RequestParam(value = "name", defaultValue = "Restaurants") String name) {


        TaskList restaurantList = new TaskList(
                new Restaurant(name, 9, 99999, "Addresse",
                        "Category",123456789, "E@mail","Password")
        );
        restaurantList.setRestaurant();

        return restaurantList;
    }


    @PostMapping(
            path = "/task/Restaurants",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    public Restaurant createTaskRestaurants(@RequestBody Restaurant restaurant) {

        TaskList taskList = new TaskList(
                new User("me", "ignore")
        );
        taskList.addRestaurant(restaurant);
        return restaurant;
    }


    @PostMapping(
            path = "/task/createtable/Restaurants"
    )
    @ResponseStatus(HttpStatus.OK)
    public String createTaskRestaurants() {

        final PostgresTaskManagerImpl postgresTaskManagerImpl =
                PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
        postgresTaskManagerImpl.createTableTask(false);

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
            //ich weiß, jemand hat gesagt: Read all my usertasks.
            StringBuilder outText = new StringBuilder ("Hello. You have to do the following usertasks.");
            try {


                //usertasks hinzufügen
                TaskList taskList = new TaskList(
                        new User("me", "me")
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
