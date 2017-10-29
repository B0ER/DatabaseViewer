package ua.b0er.buttonListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MSAccess{
    String sqlQuery = "";

    String name;

    public MSAccess(){
        name = "jdbc:ucanaccess://";
        /*

        SQLCreateTable = "CREATE TABLE voen ( Код INT default 1 PRIMARY KEY NOT NULL, [Короткий текс] VARCHAR,[Дата и время] DATETIME, Денежный INT, Логический BOOLEAN, [Длинный текст] VARCHAR, Числовой INTEGER);";
        SQLDropTable = "DROP TABLE voen";
        SQLRemoveDate = "DELETE FROM voen WHERE Код = 2"; // remove all data in voen table;

        Select = "SELECT * FROM [TABLENAME]";
        Insert = "INSERT INTO [TABLENAME](Код, [Короткий текс], [Дата и время], Денежный, Логический, [Длинный текст], Числовой)" +
                " VALUES (2, 2, 2, 2, 1, 2, 2);"; //добавление записи
        Update = "UPDATE voen SET [Код] = 2 WHERE [Код] = 3;"; //обновление записи
        */

        // загрузка драйвера
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public Connection Connect(String path, String user, String password) throws SQLException {
        //Передача сведений о подключении к базе данных в виде URL-адреса подключения
        return DriverManager.getConnection(name + path+";autosync=true", user, password);

    }
}
