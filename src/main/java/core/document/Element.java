package core.document;

import core.scripts.ExceptionJS;
import core.WebEngine;

public class Element
{
    private final WebEngine engine;
    private final ElementID elementID;

    public Element(ElementID elementID, WebEngine engine)
    {
        this.engine = engine;
        this.elementID = elementID;
    }

    public String readInner()
    {
        try
        {
            return engine.executeJS("getInnerByElementPath('" + elementID.getPath() + "');");
        }
        catch (ExceptionJS e)
        {
            return  null;
        }
    }

    public boolean click()
    {
        try
        {
            engine.executeJS("clickByElementID('" + elementID.getPath() + "');");
            return true;
        }
        catch (ExceptionJS e)
        {
            return false;
        }
    }

    public void sendInput(String data)
    {
        engine.forceExecuteJS("sendTextInput('" + elementID.getPath() + "', '" + data + "');");
    }
}
