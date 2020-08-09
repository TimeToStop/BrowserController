package core.connection;

public enum LogType
{
    LOG((byte)1), WARNING((byte)2), ERROR((byte)3);

    private final byte value;

    LogType(byte value)
    {
        this.value = value;
    }

    public byte getValue() {return value;}
}
