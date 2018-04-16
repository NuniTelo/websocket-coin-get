package com.bash.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        try {
            // open websocket
            String url = "wss://ws-feed.gdax.com";
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(url));

            // add listener
            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });

            // send message to websocket
            while(true) {
                clientEndPoint.sendMessage("{\n" +
                        "    \"type\": \"subscribe\",\n" +
                        "    \"product_ids\": [\n" +
                        "        \"ETH-USD\",\n" +
                        "        \"ETH-EUR\"\n" +
                        "    ],\n" +
                        "    \"channels\": [\n" +
                        "        \"level2\",\n" +
                        "        \"heartbeat\",\n" +
                        "        {\n" +
                        "            \"name\": \"ticker\",\n" +
                        "            \"product_ids\": [\n" +
                        "                \"ETH-BTC\",\n" +
                        "                \"ETH-USD\"\n" +
                        "            ]\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}");
                Thread.sleep(5000);
            }
            // wait 5 seconds for messages from websocket


        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }

    }
