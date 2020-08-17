package core;

import core.connection.Connection;
import core.document.*;
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
        Connection connection = Connection.connect(settings.port_file_path);
        if(connection != null)
        {
            connection.initialization(settings.default_js_scripts);
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
        return scripts_manager.executeJS(false, script);
    }

    public String executeJsFromFile(String file_name) throws FileNotFoundException, ExceptionJS
    {
        return scripts_manager.executeJSFromFile(false, file_name);
    }

    public String executeJS(boolean wait_for_redirect,String script) throws ExceptionJS
    {
        return scripts_manager.executeJS(wait_for_redirect, script);
    }

    public String executeJsFromFile(boolean wait_for_redirect, String file_name) throws FileNotFoundException, ExceptionJS
    {
        return scripts_manager.executeJSFromFile(wait_for_redirect, file_name);
    }

    public String forceExecuteJS(boolean wait_for_redirect, String script)
    {
        return scripts_manager.forceExecuteJS(wait_for_redirect, script);
    }

    public String forceExecuteJsFromFile(boolean wait_for_redirect, String file_name)
    {
        return scripts_manager.forceExecuteJSFromFile(wait_for_redirect, file_name);
    }

    public String forceExecuteJS(String script)
    {
        return scripts_manager.forceExecuteJS(false, script);
    }

    public String forceExecuteJsFromFile(String file_name)
    {
        return scripts_manager.forceExecuteJSFromFile(false, file_name);
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

    public ClickElement get(ClickElementID e)
    {
        return document.get(e);
    }

    public ReadElement get(ReadElementID e)
    {
        return document.get(e);
    }

    public InputElement get(InputElementID e)
    {
        return document.get(e);
    }


    public void close()
    {
        connection.close();
    }
}
