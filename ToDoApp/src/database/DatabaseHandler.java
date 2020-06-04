package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import controller.AddItemFormController;
import javafx.scene.control.Alert;
import model.Task;
import model.User;



public class DatabaseHandler extends Configs {
	Connection connection;
	
	private PreparedStatement preparedStatement;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		//"jdbc:mysql://localhost:3306/todo
		
		String ConnectionString = "jdbc:mysql://"+dbhost+":"+dbport+"/"+dbName;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		connection = DriverManager.getConnection(ConnectionString, dbUser, dbPass);
		System.out.println("connected");
		return connection;
		
	}
	
	//Write user in the database
	public void signUpUser(User user) {
	String insert = "INSERT INTO "+Const.USERS_TABLE+"("+Const.USERS_FIRSTNAME+","+Const.USERS_LASTNAME+","
					+Const.USERS_USERNAME+","+Const.USERS_PASSWORD+","+Const.USERS_LOCATION+","
						+Const.USERS_GENDER+")"+"VALUES(?,?,?,?,?,?)";
	 try {
		preparedStatement = getConnection().prepareStatement(insert);
		
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2,user.getLastName());
		preparedStatement.setString(3,user.getUserName());
		preparedStatement.setString(4, user.getPassword());
		preparedStatement.setString(5,user.getLocation());
		preparedStatement.setString(6,user.getGender());
		
		preparedStatement.executeUpdate();
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 }
	
	public ResultSet getTaskByUser(int userId) {
		ResultSet resultTasks = null;
		String query = "SELECT * FROM "+ Const.TASKS_TABLE+ " WHERE " +Const.USERS_ID+"=?";
		
		try {
			preparedStatement = getConnection().prepareStatement(query);

			preparedStatement.setInt(1, userId);
			
			resultTasks = preparedStatement.executeQuery();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultTasks;
	}
	
	//Checking the data in the database
	 public ResultSet getUser(User user) {
		 ResultSet resultSet = null;
		if(!user.getUserName().equals("") || !user.getPassword().equals("")) {
			String query = "SELECT * FROM "+ Const.USERS_TABLE+ " WHERE " +Const.USERS_USERNAME+"=?"+" AND "
							+Const.USERS_PASSWORD+"=?";
			try {
				preparedStatement = getConnection().prepareStatement(query);
				preparedStatement.setString(1, user.getUserName());
				preparedStatement.setString(2, user.getPassword());
				
				resultSet = preparedStatement.executeQuery();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("Please enter your credentials");
		}
		return resultSet;
	 }
	 
	 //Writing task in the database
	 public void inserTask(Task task) {
		 String insert = "INSERT INTO "+Const.TASKS_TABLE+"("+Const.USERS_ID+","+Const.TASK_TASK+","+Const.TASKS_DATE+","
					+Const.TASKS_DESCRIPTION+")"+"VALUES(?,?,?,?)";
		 
		 try {
				preparedStatement = getConnection().prepareStatement(insert);
				
				preparedStatement.setInt(1, task.getUserId());
				preparedStatement.setString(2, task.getTask());
				preparedStatement.setTimestamp(3, task.getDatecreated());
				preparedStatement.setString(4,task.getDescription());
				
				preparedStatement.executeUpdate();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 public int getAllTasks(int userId) throws ClassNotFoundException, SQLException {
			String query = "SELECT COUNT(*) FROM "+Const.TASKS_TABLE+ " WHERE "+Const.USERS_ID+"=?";
			
			preparedStatement = getConnection().prepareStatement(query);
			preparedStatement.setInt(1, userId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				return resultSet.getInt(1);
			}
			return resultSet.getInt(1);
		}
		

	
	//Update Task
	 public void updateTask(String task, Timestamp datecreated,String description, int taskId) throws SQLException, ClassNotFoundException {

		 String query = "UPDATE tasks SET task=?, datecreated=?, description=? HERE taskid=?";


	        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
	        preparedStatement.setString(1, task);
	        preparedStatement.setTimestamp(2, datecreated);
	        preparedStatement.setString(3, description);
	       // preparedStatement.setInt(4, userId);
	        preparedStatement.setInt(4, taskId);
	        preparedStatement.executeUpdate();
	        preparedStatement.close();

	    }
	//Delete
	public void deleteTask(int userId, int taskId) throws SQLException, ClassNotFoundException {
		String delete = "DELETE FROM "+Const.TASKS_TABLE+" where "+Const.USERS_ID+"=?"+ " AND "+Const.TASKS_ID+"=?";
		
		preparedStatement = getConnection().prepareStatement(delete);
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(2, taskId);
		preparedStatement.execute();
		preparedStatement.close();
	}

	
}
