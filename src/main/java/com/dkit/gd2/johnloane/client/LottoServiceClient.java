package com.dkit.gd2.johnloane.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class LottoServiceClient

        //Step 1: Establish a channel to communicate

        //Step 2: Build the input and output streams

        //Step 3: Get input from user

        //Step 4: Send message to server and display the response
{
    public static void main(String[] args)
    {
        LottoServiceClient client = new LottoServiceClient();
        client.start();
    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 50007);

            System.out.println("Client message: The Client is running and has connected to the server");

            System.out.println("Please enter a command: \n>");
            String command = in.nextLine();

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);

            socketWriter.println(command);

            Scanner socketReader = new Scanner(socket.getInputStream());

            boolean continueLooping = true;
            while (continueLooping == true){

                if(command.startsWith("Generate")){
                    String echoString = socketReader.nextLine();
                    System.out.println("Client message: Response from server Echo: " + echoString + "\n");
                }
                else if(command.startsWith("close")){
                    continueLooping = false;
                }
                else
                {
                    String input = socketReader.nextLine();
                    System.out.println("Client message: Response from server: \"" + input + "\n");
                }
                System.out.println("Please enter a command: `Generate` to Get Numbers \n>");
                command = in.nextLine();
                socketWriter.println(command);
            }

            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }
}

