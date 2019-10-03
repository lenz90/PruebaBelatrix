import java.io.File;
import java.util.logging.*;


public class Mensaje {
    private String messageText;
    private boolean logToFileParam;
    private boolean logToConsoleParam;
    private boolean logToDatabaseParam;
    private EnumTipoMensaje tipoMensaje;
    private static Logger logger;

    private Mensaje(String messageText, boolean logToFileParam, boolean logToConsoleParam, boolean logToDatabaseParam, EnumTipoMensaje tipoMensaje) throws Exception {
        if (messageText == null || messageText.length() == 0) {
            throw new Exception("Deberá crear correctamente el mensaje");
        } else if (!logToConsoleParam && !logToFileParam && !logToDatabaseParam) {
            throw new Exception("Configuracion invalida");
        } else {
            this.messageText = messageText;
            this.logToFileParam = logToFileParam;
            this.logToConsoleParam = logToConsoleParam;
            this.logToDatabaseParam = logToDatabaseParam;
            this.tipoMensaje = tipoMensaje;
            logger = Logger.getLogger("MyLog");
        }

    }

    public static Mensaje newMensaje(String messageText, boolean logToFileParam, boolean logToConsoleParam,
                                     boolean logToDatabaseParam, EnumTipoMensaje tipoMensaje) throws Exception {
        return new Mensaje(messageText, logToFileParam, logToConsoleParam, logToDatabaseParam, tipoMensaje);
    }

    public static void startMensaje(Mensaje mensaje) throws Exception {
        Handler h = null;
        if (mensaje.isLogToConsoleParam()) {
            h = new ConsoleHandler();
        }
        try {
            if (mensaje.isLogToFileParam()) {
                File logFile = new File(Constantes.FILE.RUTA_FILE);
                h = new FileHandler(Constantes.FILE.RUTA_FILE);
                if (!logFile.exists()) {
                    logFile.createNewFile();
                }
            }

            if (mensaje.isLogToDatabaseParam()) {
                Conexion.getStatement().executeUpdate("insert into Log_Values('" + mensaje.getMessageText() + "')");
            }
        } catch (Exception ex) {
            throw new Exception("Error al generar File");
        }
        printLog(mensaje.getMessageText(), h, mensaje.getTipoMensaje());

    }

    private static void printLog(String mensaje, Handler h, EnumTipoMensaje tipo) {
        logger.addHandler(h);
        if (tipo == EnumTipoMensaje.WARNING) {
            logger.log(Level.WARNING, mensaje);
        } else if (tipo == EnumTipoMensaje.ERROR) {
            logger.log(Level.SEVERE, mensaje);
        } else if (tipo == EnumTipoMensaje.MENSAJE) {
            logger.log(Level.INFO, mensaje);
        }

    }

    public String getMessageText() {
        return messageText;
    }

    public boolean isLogToFileParam() {
        return logToFileParam;
    }

    public boolean isLogToConsoleParam() {
        return logToConsoleParam;
    }

    public boolean isLogToDatabaseParam() {
        return logToDatabaseParam;
    }

    public EnumTipoMensaje getTipoMensaje() {
        return tipoMensaje;
    }
}
