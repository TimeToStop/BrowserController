package core.connection;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Connection
{
    private static final byte WAIT_FOR_REDIRECT = 1;
    private static final byte[] SPLIT = new byte[]{' '};
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private final byte[] buffer = new byte[1000];

    private static final byte[] connect_key    = "9456327810".getBytes(StandardCharsets.UTF_8);
    private static final byte[] disconnect_key = "034782938476123".getBytes(StandardCharsets.UTF_8);

    public static synchronized Connection connect(String file_name)
    {
        try
        {
            File f = new File(file_name);
            FileInputStream fis = new FileInputStream(f);
            byte[] data = new byte[(int) f.length()];
            fis.read(data);
            fis.close();
            Connection connection = new Connection(Integer.parseInt(new String(data, StandardCharsets.UTF_8)));
            if(connection.connect())
            {
                return connection;
            }
            else
            {
                System.err.println("Bad server answer.");
            }
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
        catch (NumberFormatException e)
        {
            System.err.println("Bad port number");
        }

        return null;
    }

    public synchronized BrowserAnswer loadURL(String url)
    {
        return execute(true, CommandType.LOAD_URL.getData(), url.getBytes(StandardCharsets.UTF_8));
    }

    public synchronized void log(LogType type, String msg)
    {
        execute(false, CommandType.LOG.getData(), new byte[]{type.getValue()}, msg.getBytes(StandardCharsets.UTF_8));
    }

    public synchronized BrowserAnswer executeJs(boolean wait_for_redirect, String script)
    {
        byte header = 0;
        if(wait_for_redirect)
        {
            header |= WAIT_FOR_REDIRECT;
        }

        return execute(true, CommandType.EXECUTE_JS.getData(), new byte[] {header}, script.getBytes(StandardCharsets.UTF_8));
    }

    public synchronized BrowserAnswer executeJsFile(boolean wait_for_redirect, String file_name)
    {
        byte header = 0;
        if(wait_for_redirect)
        {
            header |= WAIT_FOR_REDIRECT;
        }

        return execute(true, CommandType.EXECUTE_JS_FROM_FILE.getData(), new byte[] {header}, file_name.getBytes(StandardCharsets.UTF_8));
    }

    public synchronized void initialization(String[] default_js)
    {
        if(default_js.length != 0)
        {
            byte[] data =  compact(CommandType.INITIALIZATION.getData(), default_js[0].getBytes(StandardCharsets.UTF_8));

            for(int i = 1; i < default_js.length; i++)
            {
                data = compact(data, Connection.SPLIT, default_js[i].getBytes(StandardCharsets.UTF_8));
            }

            execute(false, data);
        }
    }

    public synchronized void close()
    {
        try
        {
            execute(false, CommandType.DISCONNECT.getData(), disconnect_key);
            in.close();
            out.close();
            socket.close();
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    private Connection(int port) throws IOException
    {
        this.socket = new Socket("localhost", port);
        this.in = new DataInputStream(socket.getInputStream());
        this.out = new DataOutputStream(socket.getOutputStream());
    }

    private synchronized boolean connect()
    {
        BrowserAnswer answer = execute(true, CommandType.CONNECT.getData(), connect_key);
        return Arrays.equals(answer.getData(), connect_key);
    }

    private synchronized BrowserAnswer execute(boolean wait_for_answer, byte[] ... request)
    {
        byte[] compact_request = compact(request);

        try
        {
            out.write(compact_request);
            out.flush();
            if(wait_for_answer)
            {
                int read = in.read(buffer);
                return new BrowserAnswer(Arrays.copyOf(buffer, read));
            }
        }
        catch (IOException e)
        {
            System.err.println("Cannot send request to server");
        }

        return null;
    }

    private static byte[] compact(byte[] ... arrays)
    {
        List<Byte> result = new ArrayList<>();
        for(byte[] array : arrays)
        {
            for(byte element : array)
            {
                result.add(element);
            }
        }

        Byte[] result_array =  result.toArray(new Byte[0]);
        byte[] unboxing = new byte[result_array.length];
        for(int i = 0; i < result_array.length; i++)
        {
            unboxing[i] = result_array[i];
        }

        return unboxing;
    }
}
