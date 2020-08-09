package core.document;

public class PageID
{
    private final int id;
    private final String request;
    private final String target;

    public PageID(int id, String request, String target)
    {
        this.id = id;
        this.request = request;
        this.target = target;
    }

    public String getRequest()
    {
        return request;
    }

    public String getTarget()
    {
        return target;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof PageID)
        {
            return ((PageID)obj).id == this.id;
        }

        return false;
    }
}
