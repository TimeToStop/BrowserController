package core.scripts;

public class FileNotFoundException extends Exception
{
    private final String file_name;

    public FileNotFoundException(String file_name)
    {
        super("File not found: " + file_name);
        this.file_name = file_name;
    }

    public String getFileName()
    {
        return file_name;
    }
}
