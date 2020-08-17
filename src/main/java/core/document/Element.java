package core.document;

import core.WebEngine;

public class Element
{
    protected final WebEngine engine;
    protected final ElementID elementID;

    public Element(ElementID elementID, WebEngine engine)
    {
        this.engine = engine;
        this.elementID = elementID;
    }

    public boolean exists()
    {
        return engine.forceExecuteJS("exists(\"" + elementID.getPath() + "\")").equals("true");
    }

    public String executeScript(String script)
    {
        return engine.forceExecuteJS(elementID.isWaitForRedirect(), script);
    }
}
