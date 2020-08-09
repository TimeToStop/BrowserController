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
        return current_url;
    }

    public Element get(ElementID elementID)
    {
        if(elementID.getMater().equals(current_page))
        {
            return new Element(elementID, engine);
        }

        return null;
    }
}
