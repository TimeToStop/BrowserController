package core.document;

import core.WebEngine;

public class InputElement extends Element
{
    public InputElement(InputElementID id, WebEngine engine)
    {
        super(id, engine);
    }

    public void sendInput(String data)
    {
        engine.forceExecuteJS("sendTextInput('" + elementID.getPath() + "', '" + data + "');");
    }
}
