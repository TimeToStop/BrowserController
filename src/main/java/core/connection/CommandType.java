package core.connection;

public enum CommandType
{
    DISCONNECT((byte)0),
    CONNECT((byte)1),
    LOAD_URL((byte)2),
    LOG((byte)3),
    EXECUTE_JS((byte)4),
    EXECUTE_JS_FROM_FILE((byte)5);

    private final byte data;

    CommandType(byte data)
    {
        this.data = data;
    }

    public byte[] getData()
    {
        return new byte[]{this.data};
    }

    public boolean equals(CommandType other)
    {
        return this.data == other.data;
    }
}
