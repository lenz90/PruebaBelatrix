public enum EnumTipoMensaje {
    MENSAJE("mensaje"),
    WARNING("warning"),
    ERROR("error");

    private String tipo;
    EnumTipoMensaje(String tipo) {
        this.tipo = tipo;
    }


    @Override
    public String toString() {
        return this.tipo;
    }


}
