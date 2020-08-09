package core.document;

public class ElementID
{
    private final PageID master;
    private final String path;

    public ElementID(PageID master, String path)
    {
        this.master = master;
        this.path = path;
    }

    public PageID getMater()
    {
        return master;
    }

    public String getPath() {return path;}
}
