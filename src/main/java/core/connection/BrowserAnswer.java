package core.connection;

import java.util.Arrays;

public class BrowserAnswer
{
    private final int header;
    private final byte[] data;

    public BrowserAnswer(byte[] answer)
    {
        this.header = answer[0];
        this.data = Arrays.copyOfRange(answer, 1, answer.length);
    }

    public int getHeader()
    {
        return this.header;
    }

    public byte[] getData()
    {
        return this.data;
    }
}
