package core.other;

import core.WebEngine;
import core.connection.LogType;

public class Console
{
    private final WebEngine engine;

    public Console(WebEngine engine)
    {
        this.engine = engine;
    }

    public void log(String msg)
    {
        engine.getConnection().log(LogType.LOG, msg);
    }

    public void warning(String msg)
    {
        engine.getConnection().log(LogType.WARNING, msg);
    }

    public void error(String msg)
    {
        engine.getConnection().log(LogType.ERROR, msg);
    }
}
