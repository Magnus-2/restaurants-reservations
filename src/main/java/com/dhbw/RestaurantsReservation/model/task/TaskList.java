package com.dhbw.RestaurantsReservation.model.task;

import com.dhbw.RestaurantsReservation.dataManager.TaskManager;
import com.dhbw.RestaurantsReservation.dataManagerImpl.PostgresTaskManagerImpl;
import com.dhbw.RestaurantsReservation.model.reservations.Reservations;
import com.dhbw.RestaurantsReservation.model.restaurant.Restaurant;
import com.dhbw.RestaurantsReservation.model.user.User;


import java.util.Collection;

public class TaskList {



	private User user;
	private  Restaurant restaurant;
	private  Reservations reservations;
	private Collection<Task> usertasks;
	private Collection<User> usertask;
	private Collection<Restaurant> restauranttask;

	private Collection<Reservations> reservationtask;

	public TaskList() { }

	public TaskList(User user) {
		this.user = user;
	}
	public TaskList(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public TaskList(Reservations reservations) {this.reservations =reservations;}


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Collection<User> getUsers() {
		return usertask;
	}


	public Restaurant getRestaurant(){
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public Collection <Restaurant> getRestaurants(){return restauranttask;}


	public Reservations getReservations (){return reservations;}
	public void setReservation(Reservations reservations) {this.reservations = reservations;}
	public Collection<Reservations> getReservation() {return reservationtask;}



	public Collection<Task> getTasks() {
		return usertasks;
	}



	public void setTasks() {
		TaskManager taskManager = PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
		usertasks = taskManager.getAllTasks(new User("me", "me"));
	}

	@SuppressWarnings("deprecation")
	public void addTask(Task task) {
		TaskManager taskManager = PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
		taskManager.addTask(task, new User("me", "me"));




		// Region euCentral = Region.getRegion(Regions.US_EAST_1);
		// sqs.setRegion(euCentral);
        // .withDelaySeconds(1);    
		
/*	
		AWSCredentials awsCredentials = new SimpleAWSCredentials();         
		AmazonSQS sqs = new AmazonSQSClient(awsCredentials);
		
		SendMessageRequest send_msg_request = new SendMessageRequest()
		        .withQueueUrl("https://sqs.us-east-1.amazonaws.com/887927861730/Mosbach-task-organizer-Created-new-task")
		        .withMessageBody("Added the following task: " + task.getName() + " with priority: " + task.getPriority());
		sqs.sendMessage(send_msg_request);
*/	
		
		
	}

	public void addRestaurant (Restaurant restaurant){
		TaskManager taskManager = PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
		taskManager.addRestaurant(restaurant);
		//, new RestaurantUser("me", "me") eintragen fall es nicht klappt
	}



	public void setRestaurant() {
		/*TaskManager restaurantManager = PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
		restauranttask = restaurantManager.getAllRestaurants(new Restaurant("rName", 9, 99999,
				"Addresse","Category",123456789, "E@mail","Password"));*/


		TaskManager restaurantManager = PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
		restauranttask = restaurantManager.getAllRestaurants(new Restaurant("me", "me"));
	}

	public void addUser (User user){
		TaskManager userManager = PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
		userManager.addUser(user);
		//, new RestaurantUser("me", "me") eintragen fall es nicht klappt
	}



	public void setUsers() {
		/*TaskManager restaurantManager = PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
		restauranttask = restaurantManager.getAllRestaurants(new Restaurant("rName", 9, 99999,
				"Addresse","Category",123456789, "E@mail","Password"));*/


		TaskManager userManager = PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
		usertask = userManager.getAllUsers(new User("email", "password"));
	}

	public void loginUser(User user) {
		// Get oder Post methode ab hier verwenden aber eher get da wir ja die Daten vom User ausgeben wollen
		TaskManager userManager = PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
		userManager.addUser(user);
	}


	public void addReservations(Reservations reservations) {
		TaskManager reservationsManager = PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
		reservationsManager.addReservations(reservations);
	}

	public void setReservation(){
		TaskManager reservationManager = PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
		reservationtask = reservationManager.getAllReservation(new Reservations("Firstname", "Restaurantname"));
	}


}