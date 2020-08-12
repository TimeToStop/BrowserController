package core.connection;

public enum CommandType
{
    DISCONNECT((byte)0),
    CONNECT((byte)1),
    INITIALIZATION((byte)2),
    LOAD_URL((byte)3),
    LOG((byte)4),
    EXECUTE_JS((byte)5),
    EXECUTE_JS_FROM_FILE((byte)6);

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
