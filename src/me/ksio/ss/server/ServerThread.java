package me.ksio.ss.server;

import java.io.*;
import java.net.*;
import java.util.Random;
 
/**
 * This thread is responsible to handle client connection.
 *
 * @author www.codejava.net
 */
public class ServerThread extends Thread {
    private Socket socket;
 
    static final String[] quotes = {
            "Be yourself; everyone else is already taken. - Oscar Wilde",
            "A room without books is like a body without a soul. - Marcus Tullius Cicero",
            "Be the change that you wish to see in the world. - Mahatma Gandhi",
            "If you tell the truth, you don't have to remember anything. - Mark Twain",
            "If you want to know what a man's like, take a good look at how he treats his inferiors, not his equals.- J.K. Rowling",
            "To live is the rarest thing in the world. Most people exist, that is all.- Oscar Wilde",
            "Without music, life would be a mistake. - Friedrich Nietzsche",
            "Always forgive your enemies, nothing annoys them so much. - Oscar Wilde",
            "Life isn't about getting and having, it's about giving and being. - Kevin Kruse",
            "Whatever the mind of man can conceive and believe, it can achieve. - Napoleon Hill",
            "Strive not to be a success, but rather to be of value. - Albert Einstein",                          
            }; 
    
    public ServerThread(Socket socket) {
        this.socket = socket;
    }
 
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
 
 
            String text;

        	Random r;
            do {
                text = reader.readLine();
                if (text == null || text.equals("")) continue;
                
                String value = "";
                if (text.equalsIgnoreCase("hit me")) {
                	r = new Random();
                	int index = r.nextInt(10);
                	value = quotes[index];
                }
                else if (text.equalsIgnoreCase("end"))
                	value = "Goodbye :)";
                else
                	value = "[Server]: Unknown command. Please say 'Hit me' to get a quote, or 'End' to quit.";
                //String reverseText = new StringBuilder(text).reverse().toString();
                writer.println(value);
 
            } while (!text.equalsIgnoreCase("end"));
 
            socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}