package core;

import core.connection.Connection;
import core.document.Document;
import core.document.Element;
import core.document.ElementID;
import core.document.PageID;
import core.scripts.ExceptionJS;
import core.scripts.FileNotFoundException;
import core.scripts.ScriptsManager;
import core.other.Console;
import core.other.Settings;

public class WebEngine
{
    private final Connection connection;
    private final Document document;
    private final Console console;
    private final ScriptsManager scripts_manager;

    public WebEngine(Connection connection)
    {
        this.connection      = connection;
        this.document        = new Document(this);
        this.console         = new Console(this);
        this.scripts_manager = new ScriptsManager(this);
    }

    public static WebEngine create(Settings settings)
    {
        Connection connection = Connection.connect(settings.port);
        if(connection != null)
        {
            return new WebEngine(connection);
        }
        else
        {
            return null;
        }
    }

    public Connection getConnection()
    {
        return connection;
    }

    public String loadURL(PageID pageID)
    {
        return document.load(pageID);
    }

    public String executeJS(String script) throws ExceptionJS
    {
        return scripts_manager.executeJS(script);
    }

    public String executeJsFromFile(String file_name) throws FileNotFoundException, ExceptionJS
    {
        return scripts_manager.executeJSFromFile(file_name);
    }

    public String forceExecuteJS(String script)
    {
        return scripts_manager.forceExecuteJS(script);
    }

    public String forceExecuteJsFromFile(String file_name)
    {
        return scripts_manager.forceExecuteJSFromFile(file_name);
    }

    public void log(String msg)
    {
        console.log(msg);
    }

    public void warning(String msg)
    {
        console.warning(msg);
    }

    public void error(String msg)
    {
        console.error(msg);
    }

    public Element get(ElementID id)
    {
        return document.get(id);
    }

    public void close()
    {
        connection.close();
    }
}
