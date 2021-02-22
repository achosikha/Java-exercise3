package SQLite;

import java.sql.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public class MyMain {
    static String url = "jdbc:mysql://localhost:3306/authentication";
    static String user = "root";
    static String password = "achosikha";

    static int id = 1;
    static String username = "User ";
    static String pass = "Pass ";

    public static void main (String[] args) throws SQLException, ClassNotFoundException {
        // 1. Добавить в сетевой чат авторизацию через базу данных SQLite.

        // Method connecting with MySQL server on MySQL Workbench
        // Sending messages to insert a few rows
        // Printing USERS table
        // Cleaning USERS table and freeing memory
        manipulateMySQLServer();
        initiateServer();
    }

    static void manipulateMySQLServer() throws SQLException, ClassNotFoundException {
        // Connect to MySQL Server on MySQL Workbench with SQL


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Connection is Successful to the database: " + url);

        Statement getMyTable = connection.createStatement();

        // Send a message to create new rows with respective ID, nickname and password
        getMyTable.execute("INSERT INTO USERS (ID, nickname, password) VALUES (1, 'jack', 'pasWWr23')");
        getMyTable.execute("INSERT INTO USERS (ID, nickname, password) VALUES (2, 'john', 'pas24523')");
        getMyTable.execute("INSERT INTO USERS (ID, nickname, password) VALUES (3, 'jenny', 'stwqr90')");
        getMyTable.execute("INSERT INTO USERS (ID, nickname, password) VALUES (4, 'joshua', '8sk9sk')");

        // Send message and get all columns/rows
        ResultSet resultSet = getMyTable.executeQuery("SELECT * FROM USERS");

        while (resultSet.next()) {
            System.out.print(resultSet.getLong("ID") + " ");
            System.out.print(resultSet.getString("nickname") + " ");
            System.out.println(resultSet.getString("password") + " ");
        }

        // Clear USERS table
        getMyTable.execute("DELETE FROM USERS WHERE 1 = 1");

        // Free memory
        resultSet.close();
        getMyTable.close();
        connection.close();
    }

    static void initiateServer() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Connection is Successful to the database: " + url);

        Statement getMyTable = connection.createStatement();

        Thread serverThread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(8188))
            {
                System.out.println("Server is Online...");
                Socket socket = serverSocket.accept();

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                while (true)
                {
                    if (in.readUTF().equalsIgnoreCase("/end"))
                    {
                        // Clear USERS table
                        getMyTable.execute("DELETE FROM USERS WHERE 1 = 1");

                        // Free memory
                        getMyTable.close();
                        connection.close();
                        break;
                    }
                    out.writeUTF("Server: " + in.readUTF());
                }
            } catch (IOException | SQLException e)
            {
                e.printStackTrace();
            }
        });
        serverThread.start();

        try (Socket socket = new Socket("localhost", 8188))
        {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            String createData = "INSERT INTO USERS (ID, nickname, password) VALUES (" + id + "," + "'" + username + id + "'" + "," + "'" + pass + id++ + "'" + ")";

            System.out.println(createData);

            getMyTable.execute(createData);

            System.out.println("Client has connected.");

            output.writeUTF("Client: " + input.readUTF());
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}
