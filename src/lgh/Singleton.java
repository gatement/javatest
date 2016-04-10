package lgh;

public class Singleton {
    private static Singleton singleton = null;
    private String name;

    private Singleton() {}

    public static Singleton getSingleton()
    {
        if (singleton == null)
            singleton = new Singleton();

        return singleton; 
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }
}
