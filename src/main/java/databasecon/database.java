package databasecon;

import models.User;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class database {
    private static Connection connect;
    public static Statement statmt;
    public static ResultSet resSet;
    private String tableName = "users";
    private String[] fields = {"id", "surname", "name", "patronymic", "telephone", "dateofbirth"};
    private String fileDB;

    public static void main(String[] args)
    {
        /*List<User> arr = new ArrayList<>();
        try {
            File file = new File("user.csv");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] attr = line.split(";");
                    arr.add(new User(attr[0], attr[1], attr[2], attr[3], attr[4]));
                }
            }
            while (line != null);
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:" +"users.sqlite");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String query = String.format("DROP TABLE IF EXISTS 'users'");
        try {
            statmt = connect.createStatement();
            statmt.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        query = String.format("CREATE TABLE if not exists '%s' ('%s' INTEGER PRIMARY KEY AUTOINCREMENT, '%s' TEXT, '%s' TEXT, '%s' TEXT, '%s' TEXT, '%s' TIMESTAMP DEFAULT CURRENT_TIMESTAMP)",
                "users",
                "id",
                "surname",
                "name",
                "patronymic",
                "telephone",
                "dateofbirth"
        );
        try {
            statmt = connect.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            statmt.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        for (User item: arr) {
            System.out.print(item.toString());
            query = String.format("INSERT INTO %s ('%s', '%s', '%s', '%s', '%s') VALUES('%s', '%s', '%s', '%s', '%s')",
                    "users",
                    "surname",
                    "name",
                    "patronymic",
                    "telephone",
                    "dateofbirth",
                    item.getSurname(),
                    item.getName(),
                    item.getPatronymic(),
                    item.getTelephone(),
                    item.getDateofbirth());
            try {
                statmt.execute(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:" +"users.sqlite");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int idq;
        String surname;
        String name;
        String patronymic;
        String telephone;
        Timestamp dateofbirth;
        String query = String.format("SELECT * FROM %s WHERE id=%d", "users", 3);
        try {
            System.out.println("Query: " + query);
            statmt = connect.createStatement();
            resSet = statmt.executeQuery(query);
            idq = resSet.getInt("id");
            surname = resSet.getString("surname");
            name = resSet.getString("name");
            patronymic = resSet.getString("patronymic");
            telephone = resSet.getString("telephone");
            Timestamp temp = resSet.getTimestamp("dateofbirth");
            System.out.println(temp.toString());
            dateofbirth = resSet.getTimestamp("dateofbirth");
            System.out.println("День рождения: " + dateofbirth);
        } catch (SQLException e) {
            System.out.println("---------------------------------");
            System.out.println("         Ну, приехали");
            System.out.println("---------------------------------");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        User u = new User(idq, surname, name, patronymic, telephone, dateofbirth);
        System.out.println(u.toString());*/
    }

    public database(String file) {
        this.fileDB = file;
        try {
            Class.forName("org.sqlite.JDBC");
            this.connect = DriverManager.getConnection("jdbc:sqlite:" + this.fileDB);
            statmt = connect.createStatement();
            this.CreateTable();
        } catch (ClassNotFoundException e) {
            System.err.println("Could not load JDBC driver");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.err.println("Could not connect to database");
            throw new RuntimeException(e);
        }

        System.out.println("Подключение к БД успешно");
    }

    private void CreateTable() throws SQLException {
        String query = String.format("CREATE TABLE if not exists '%s' ('%s' INTEGER PRIMARY KEY AUTOINCREMENT, '%s' TEXT, '%s' TEXT, '%s' TEXT, '%s' TEXT, '%s' TIMESTAMP DEFAULT CURRENT_TIMESTAMP)",
                this.tableName,
                this.fields[0],
                this.fields[1],
                this.fields[2],
                this.fields[3],
                this.fields[4],
                this.fields[5]
                );
        //statmt = connect.createStatement();
        statmt.execute(query);
    }

    /*private void fillingTable()
    {
        List<User> arr = new ArrayList<>();
        try {
            File file = new File("users.csv");
            FileReader  fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            String line;
            do {
                line = reader.readLine();
                if (line != null) {
                    String[] attr = line.split(";");
                    arr.add(new User(attr[0], attr[1], attr[2], attr[3], attr[4]));
                }
            }
            while (line != null);
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (User item: arr) {
            item.toString();
            insert(item);
        }
    }*/

    public void insert(User obj) {
        String query = String.format("INSERT INTO %s ('%s', '%s', '%s', '%s', '%s') VALUES('%s', '%s', '%s', '%s', '%s')",
                this.tableName,
                this.fields[1],
                this.fields[2],
                this.fields[3],
                this.fields[4],
                this.fields[5],
                obj.getSurname(),
                obj.getName(),
                obj.getPatronymic(),
                obj.getTelephone(),
                obj.getDateofbirth());
        try {
            statmt.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> selectAll() {
        List<User> result = new ArrayList<>();
        String query = String.format("SELECT * FROM %s", this.tableName);
        try {
            resSet = statmt.executeQuery(query);
            while(resSet.next()) {
                int id = resSet.getInt(fields[0]);
                String surname = resSet.getString(fields[1]);
                String name = resSet.getString(fields[2]);
                String patronymic = resSet.getString(fields[3]);
                String telephone = resSet.getString(fields[4]);
                Timestamp dateofbirth = resSet.getTimestamp(fields[5]);
                result.add(new User(id, surname, name, patronymic, telephone, dateofbirth));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public User selectUser(int id)
    {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int num = 0;
        String surname = "";
        String name = "";
        String patronymic = "";
        String telephone = "";
        Timestamp dateofbirth = null;
        String query = String.format("SELECT * FROM %s WHERE id=%d", this.tableName, id);
        try {
            /*System.out.println("File DB: " + this.fileDB);
            Class.forName("org.sqlite.JDBC");
            this.connect = DriverManager.getConnection("jdbc:sqlite:" + this.fileDB);
            System.out.println(connect.isClosed());
            statmt = connect.createStatement();
            System.out.println(statmt.isClosed());*/
            resSet = statmt.executeQuery(query);
            if (resSet.isClosed())
            {
                System.out.println("Запрос закрыт");
            }
            else {
                //statmt = connect.createStatement();

                num = resSet.getInt(fields[0]);
                surname = resSet.getString(fields[1]);
                name = resSet.getString(fields[2]);
                patronymic = resSet.getString(fields[3]);
                telephone = resSet.getString(fields[4]);
                Timestamp temp = resSet.getTimestamp(fields[5]);
                System.out.println(temp.toString());
                dateofbirth = resSet.getTimestamp(fields[5]);
                System.out.println("День рождения: " + dateofbirth);
            }

        } catch (SQLException e) {
            System.out.println("---------------------------------");
            System.out.println("         Ну, приехали");
            System.out.println("---------------------------------");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println(dateofbirth);
        return new User(num, surname, name, patronymic, telephone, dateofbirth);
    }

    public void delete(Long id) {
        String query = String.format("DELETE FROM %s WHERE id=%d", this.tableName, id);
        try {
            statmt.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Long id, User user) {
        String query = String.format("UPDATE '%s' SET %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', WHERE %s=%d",
                this.tableName,
                fields[1],
                user.getSurname(),
                fields[2],
                user.getName(),
                fields[3],
                user.getPatronymic(),
                fields[4],
                user.getTelephone(),
                fields[5],
                user.getDateofbirth(),
                fields[0],
                id);
    }
}
