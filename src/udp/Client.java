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
import java.net.UnknownHostException;

/**
 *
 * @author alma_
 */
public class Client {

    public static void main(String[] args) throws SocketException, UnknownHostException, IOException {
        BufferedReader KeyBoard = new BufferedReader(new InputStreamReader(System.in));
        
        byte[] sendData= new byte[1024];
        byte[] receiveData= new byte[1024];
        System.out.println("Conectado con: Servidor");
        System.out.println("Para salir del chat, escribe Salir");
        System.out.println("Escribe un mensaje: ");
        while (true) {
            InetAddress ipaddress = InetAddress.getByName("localhost");
            DatagramSocket client = new DatagramSocket();
            String minisentence = KeyBoard.readLine();
            String sentence = minisentence + "                                                           ";
            System.out.println("Cliente(tú): " + sentence);
            if (sentence.equals("Salir") || sentence.equals("salir")) {
                sentence = "Hasta la proxima";
                sendData = sentence.getBytes();

                DatagramPacket pregunta = new DatagramPacket(sendData, sendData.length, ipaddress, 9876);
                client.send(pregunta);
                System.exit(0);
                break;
            }
            receiveData = sentence.getBytes();

            DatagramPacket pregunta = new DatagramPacket(receiveData, receiveData.length, ipaddress, 9876);
            client.send(pregunta);

            DatagramPacket peticion = new DatagramPacket(receiveData, receiveData.length);
            client.receive(peticion);
            sentence = new String(peticion.getData());
            System.out.println("Servidor dice:" + sentence);

            client.close();
        }
    }
}
