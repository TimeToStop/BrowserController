package core.document;

import core.WebEngine;
import core.scripts.ExceptionJS;

public class ClickElement extends Element
{
    public ClickElement(ClickElementID id, WebEngine engine)
    {
        super(id, engine);
    }

    public void click()
    {
        super.executeScript("clickByElementID('" + elementID.getPath() + "');");
    }
}
