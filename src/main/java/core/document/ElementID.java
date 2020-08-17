package core.document;

public class ElementID
{
    private final boolean wait_for_redirect;
    private final PageID master;
    private final String path;

    public ElementID(boolean wait_for_redirect, PageID master, String path)
    {
        this.wait_for_redirect = wait_for_redirect;
        this.master = master;
        this.path = path;
    }

    public PageID getMater()
    {
        return master;
    }

    public String getPath()
    {
        return path;
    }

    public boolean isWaitForRedirect()
    {
        return wait_for_redirect;
    }
}
