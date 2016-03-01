package lgh;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class Main
{
    public static void main(String[] args) throws Exception 
    {
        System.out.println("==start");
        Server server = new Server(8080);

        ServletHandler handler1 = new ServletHandler();
        handler1.addServletWithMapping(HelloServlet.class, "/hello/*");
        server.insertHandler(handler1);

        server.start();
        server.join();
    }
}
