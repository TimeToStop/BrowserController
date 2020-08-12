package core.document;

import core.WebEngine;
import core.scripts.ExceptionJS;

public class ClickElement extends Element
{
    public ClickElement(ClickElementID id, WebEngine engine)
    {
        super(id, engine);
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
}
