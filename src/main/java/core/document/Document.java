package core.document;

import core.connection.BrowserAnswer;
import core.WebEngine;

import java.nio.charset.StandardCharsets;

public class Document
{
    private final WebEngine engine;

    private PageID current_page;
    private String current_url;

    public Document(WebEngine engine)
    {
        this.engine = engine;
    }

    public String load(PageID pageID)
    {
        BrowserAnswer answer = engine.getConnection().loadURL(pageID.getRequest());
        current_page = pageID;
        current_url = new String(answer.getData(), StandardCharsets.UTF_8);
        engine.forceExecuteJsFromFile("js/controller.js");
        return current_url;
    }

    public ClickElement get(ClickElementID elementID)
    {
        if(elementID.getMater().equals(current_page))
        {
            return new ClickElement(elementID, engine);
        }

        return null;
    }

    public ReadElement get(ReadElementID elementID)
    {
        if(elementID.getMater().equals(current_page))
        {
            return new ReadElement(elementID, engine);
        }

        return null;
    }

    public InputElement get(InputElementID elementID)
    {
        if(elementID.getMater().equals(current_page))
        {
            return new InputElement(elementID, engine);
        }

        return null;
    }
}
