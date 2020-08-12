package core.document;

import core.scripts.ExceptionJS;
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
}
