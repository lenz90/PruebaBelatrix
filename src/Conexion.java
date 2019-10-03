import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

public class Conexion {

    private static Connection connection;
    private Properties prop;
    private static Map dbParams;


    private Conexion(Properties prop, Connection connection) {
        this.connection = connection;
        this.prop = prop;
        this.dbParams = new HashMap();
        dbParams.put("userName", Constantes.BD.USER_NAME);
        dbParams.put("password", Constantes.BD.PASSWORD);
        dbParams.put("dbms", Constantes.BD.DBMS);
        dbParams.put("serverName", Constantes.BD.SERVER_NAME);
        dbParams.put("portNumber", Constantes.BD.PORT);
    }

    public static Connection getConection() throws Exception {
        if (!Optional.ofNullable(connection).isPresent()) {
            Properties prop = new Properties();
            prop.put("user", dbParams.get("userName"));
            prop.put("password", dbParams.get("password"));
            Connection con = null;
            try {
                con = DriverManager.getConnection("jdbc:" + dbParams.get("dbms") + "://" + dbParams.get("serverName")
                        + ":" + dbParams.get("portNumber") + "/", prop);
            } catch (Exception ex) {
                throw new Exception("Error al crear conexión");
            }

            return con;
        }
        return connection;
    }

    public static Statement getStatement() throws Exception {
        try {
            return getConection().createStatement();
        } catch (Exception ex) {
            throw new Exception("Error al crear la sentencia.");
        }

    }


}