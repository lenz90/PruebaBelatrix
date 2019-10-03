public class JobLoggerRefactor {
    private boolean logWarning;
    private boolean logError;
    private boolean logMensaje;

    private JobLoggerRefactor(boolean logWarning, boolean logError, boolean logMensaje) throws Exception {
        if (!logError && !logWarning) {
            throw new Exception("Error deberá especificar si es warning o error.");
        }
        this.logError = logError;
        this.logWarning = logWarning;
        this.logMensaje = logMensaje;

    }

    public static JobLoggerRefactor newInstance(boolean logWarning, boolean logError, boolean logMensaje) throws Exception {
        return new JobLoggerRefactor(logWarning, logError, logMensaje);
    }


    public void LogMessage(String messageText, boolean logToConsole, boolean logToFile, boolean logToDatabase, EnumTipoMensaje tipoMensaje) throws Exception {
        if ((!this.logWarning && EnumTipoMensaje.WARNING == tipoMensaje) || (!this.logError && EnumTipoMensaje.ERROR == tipoMensaje)) {
            throw new Exception("No se le permite este tipo de mensaje");
        }

        Mensaje m = Mensaje.newMensaje(messageText, logToFile, logToConsole, logToDatabase, tipoMensaje);
        Mensaje.startMensaje(m);
    }


}
