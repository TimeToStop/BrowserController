package core.scripts;

import core.connection.BrowserAnswer;
import core.WebEngine;

import java.nio.charset.StandardCharsets;

public class ScriptsManager
{
    private static final int EXECUTE_JS_EXCEPTION = 0x1;

    private static final int EXECUTE_JS_FROM_FILE_NOT_FOUND = 0x1;
    private static final int EXECUTE_JS_FROM_FILE_EXCEPTION = 0x2;

    private final WebEngine engine;

    public ScriptsManager(WebEngine engine)
    {
        this.engine = engine;
    }

    public String executeJS(String script) throws ExceptionJS
    {
        BrowserAnswer answer = engine.getConnection().executeJs(script);
        String result = new String(answer.getData(), StandardCharsets.UTF_8);
        if((answer.getHeader() & ScriptsManager.EXECUTE_JS_EXCEPTION) != 0)
        {
            throw new ExceptionJS(result);
        }
        else
        {
            return result;
        }
    }

    public String executeJSFromFile(String file_name) throws ExceptionJS, FileNotFoundException
    {
        BrowserAnswer answer = engine.getConnection().executeJsFile(file_name);
        String result = new String(answer.getData(), StandardCharsets.UTF_8);
        System.out.println(result);
        if((answer.getHeader() & ScriptsManager.EXECUTE_JS_FROM_FILE_NOT_FOUND) != 0)
        {
            throw new FileNotFoundException(file_name);
        }
        else if((answer.getHeader() & ScriptsManager.EXECUTE_JS_FROM_FILE_EXCEPTION) != 0)
        {
            throw new ExceptionJS(result);
        }
        else
        {
            return result;
        }
    }

    public String forceExecuteJS(String script)
    {
        BrowserAnswer answer = engine.getConnection().executeJs(script);
        String f = new String(answer.getData(), StandardCharsets.UTF_8);
        System.out.println(f);
        return f;
    }

    public String forceExecuteJSFromFile(String file_name)
    {
        BrowserAnswer answer = engine.getConnection().executeJsFile(file_name);
        return new String(answer.getData(), StandardCharsets.UTF_8);
    }
}
