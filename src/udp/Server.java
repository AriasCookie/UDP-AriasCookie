/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author alma_
 */
public class Server {

    public static void main(String[] args) throws SocketException, IOException {
        BufferedReader KeyBoard = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket server = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        System.out.println("Iniciando servidor...");
        System.out.println("...");
        System.out.println("...");
        System.out.println("...");
        System.out.println("Servidor conectado");
        int valor_inicial = 0;
        int i = valor_inicial;

        System.out.println("Para salir, escribe salir");
        System.out.println("Conectado con Cliente");
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            server.receive(receivePacket);
            String sentencia = new String(receivePacket.getData());
            System.out.println("Cliente dice: " + sentencia);
            
            if (i < 1) {

                System.out.println("Escribe un mensaje");
                i = 1;
            }
            int port = receivePacket.getPort();
            InetAddress ipaddress = receivePacket.getAddress();
            sentencia = KeyBoard.readLine();
            
            if (sentencia.equals("Salir") || sentencia.equals("salir")) {
                sentencia = "Hasta la proxima";
                sendData = sentencia.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipaddress, port);
                System.out.println("Servidor(tú): " + sentencia);
                server.send(sendPacket);
                System.exit(0);
            }
            sendData = sentencia.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipaddress, port);
            System.out.println("Servidor(tú): " + sentencia);
            server.send(sendPacket); 
        }
    }
}
