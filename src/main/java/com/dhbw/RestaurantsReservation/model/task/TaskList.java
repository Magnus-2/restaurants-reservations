package com.dhbw.RestaurantsReservation.model.task;

import com.dhbw.RestaurantsReservation.dataManager.TaskManager;
import com.dhbw.RestaurantsReservation.dataManagerImpl.PostgresTaskManagerImpl;
import com.dhbw.RestaurantsReservation.model.user.User;


import java.util.Collection;

public class TaskList {
	
	private User user;
	private Collection<Task> usertasks;

	public TaskList() { }

	public TaskList(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<Task> getTasks() {
		return usertasks;
	}

	public void setTasks() {
		//TaskManager taskManager = PropertyFileTaskManagerImpl.getPropertyFileTaskManagerImpl("src/main/resources/TaskList.properties");
		TaskManager taskManager = PostgresTaskManagerImpl.getPostgresTaskManagerImpl();
		usertasks = taskManager.getAllTasks(new User("me", "me"));
	}

	@SuppressWarnings("deprecation")
	public void addTask(Task task) {
		//TaskManager taskManager = PropertyFileTaskManagerImpl.getPropertyFileTaskManagerImpl("src/main/resources/TaskList.properties");
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


}