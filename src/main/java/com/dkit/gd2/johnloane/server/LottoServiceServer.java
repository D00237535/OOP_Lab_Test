package com.dkit.gd2.johnloane.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

public class LottoServiceServer
{
    //Set up the listening socket
    public static void main(String[] args)
    {
        LottoServiceServer server = new LottoServiceServer();
        server.start();
    }

    public void start()
    {
        try
        {
            ServerSocket ss = new ServerSocket(50007);

            int clientNumber = 0;

            while (true)
            {
                Socket socket = ss.accept();

                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                Thread t = new Thread(new ClientHandler(socket, clientNumber));
                t.start();

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e)
        {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        public ClientHandler(Socket clientSocket, int clientNumber)
        {
            try
            {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true);

                this.clientNumber = clientNumber;

                this.socket = clientSocket;

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        //Do the main logic of the server

        @Override
        public void run()
        {
            String message;
            try
            {
                while ((message = socketReader.readLine()) != null)
                {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);

                    if (message.startsWith("Generate"))
                    {
                        message = message.substring(8);

                        Random rd = new Random();
                        int[] lottoNumbers = new int[6];

                        for (int i = 0; i < 6; i++)
                        {
                            lottoNumbers[i] = rd.nextInt(47) + 1;
                            for (int j = 0; j < i; j++)
                            {
                                if (lottoNumbers[i] == lottoNumbers[j])
                                {
                                    i--;
                                    break;
                                }
                            }
                        }
                        message = Arrays.toString(lottoNumbers);
                        socketWriter.println(message);
                    }
                    else if (message.startsWith("Close"))
                    {
                        message = message.substring(5);

                        message = "Server: (ClientHandler): Client " + clientNumber + " has closed the connection.";
                        socketWriter.println(message);
                        socket.close();
                        break;
                    }
                    else
                    {
                        socketWriter.println("I'm sorry I don't understand :(");
                    }
                }

                socket.close();

            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }

}

