package core.document;

import core.WebEngine;
import core.scripts.ExceptionJS;

public class ReadElement extends Element
{
    public ReadElement(ReadElementID id, WebEngine engine)
    {
        super(id, engine);
    }

    public String read()
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
}
