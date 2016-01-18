package lgh;

import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;

public class HelloWorld 
{
    public static void main(String[] args) 
    {
        //System.out.println("==start");

        new HelloWorld().start();

        //System.out.println("==end");
    }

	public void start()
    {
		try
        {
            int i = 0;
            while(true)
            {
                // the following way need a valid server cert
                //SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
                //SSLSocket socket = (SSLSocket) factory.createSocket("noti.gizwitsapi.com", 2015);
                TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() 
                    {
                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers()
                        {
                            return null;
                        }

                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                        {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                        {
                        }
                    }
                };
                SSLContext context = SSLContext.getInstance("SSL");  
                context.init(null, trustAllCerts, new SecureRandom());  
                SSLSocketFactory factory = context.getSocketFactory();  
                SSLSocket socket = (SSLSocket) factory.createSocket("noti.gizwitsapi.com", 2015);

                //socket.startHandshake();
                //socket.setKeepAlive(true);

                new SendThread(socket).start();
                new ReceiveThread(socket).start();

                Thread.sleep(10000);
                socket.close();
                System.out.println("===============loop: " + String.valueOf(i));
                i++;
            }
		}
		catch(Exception e)
        {
			e.printStackTrace();
		}
	}

	class SendThread extends Thread
    {
		private Socket socket;
		private boolean isLogin = false;

		public SendThread(Socket socket)
        {
			this.socket = socket;
		}

		@Override
		public void run()
        {
            //System.out.println("thread 'SendThread' start...");
			//while(true)
            for(int i = 0; i < 3; i++)
            {
				try
                {
					String send = "";
					if (!isLogin)
                    {
                        //Thread.sleep(1000);
						send = getLogin();
						isLogin = true;
                        System.out.println("send login");
					}
					else
                    {
                        Thread.sleep(4000);
						send = getSend();
                        System.out.println("send heartbeat");
					}
								
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
					pw.write(send);
					pw.flush();
				}
                catch (Exception e) 
                {
					e.printStackTrace();
				}
			}
		}
		
		public String getSend() throws InterruptedException
        {
			return "{\"cmd\": \"enterprise_ping\"}\n";
		}
		
		public String getLogin() throws InterruptedException
        {
			return "{\"cmd\": \"enterprise_login_req\",\"data\": {\"enterprise_id\": \"9ee67528b7ff477ea65c0fbdd816658f\",\"enterprise_secret\": \"dfde94bfcdff4908b35d0f073dd867ad\",\"prefetch_count\": 50}}\n";
		}
	}
	class ReceiveThread extends Thread
    {
		private Socket socket;
		
		public ReceiveThread(Socket socket)
        {
			this.socket = socket;
		}

		@Override
		public void run()
        {
            //System.out.println("thread 'ReceiveThread' start...");
			//while(true)
            for(int i = 0; i < 3; i++)
            {
				try
                {					
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
					String line = reader.readLine();
                    System.out.println("rcvd: " + line);
				}
                catch (Exception e)
                {
					e.printStackTrace();
				}
			}
		}
	}
}
