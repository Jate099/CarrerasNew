package com.example.a1143878729.carrerasnew;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Cliente extends Thread {

    DatagramSocket s;
    int puerto;
    InetAddress ip;

    Observer obs;
    MainActivity activity;

    public Cliente(MainActivity activity){
        this.obs = activity;
        this.activity = activity;
    }

    @Override
    public void run() {
        startCom();

        while(true){
            recibir();
        }
    }

    public void startCom() {
        try {
            s = new DatagramSocket(5000);
            ip = InetAddress.getByName("10.0.2.2");

            puerto = 5000;
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void enviar(final String mensaje) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatagramPacket message = new DatagramPacket(mensaje.getBytes(), mensaje.length(), ip, puerto);
                try {
                    s.send(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void recibir() {

        byte[] capacidad = new byte[1000];
        DatagramPacket message = new DatagramPacket(capacidad, capacidad.length);
        System.out.println("Esperando Datos");
        try {
            System.out.println("Datos recibidos");
            s.receive(message);


            String recibido = new String(message.getData()).trim();

            obs.getRecibido(recibido);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface Observer{
       public void getRecibido(String recibido);
    }





}
