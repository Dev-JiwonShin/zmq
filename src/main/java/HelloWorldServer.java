//package com.imatix.zguide.ex1_1;

import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

import static java.lang.String.format;
import static org.zeromq.ZMQ.REP;
import static org.zeromq.ZMQ.context;

/**
 * Hello World server in Java
 * Binds REP socket to tcp://*:5555
 * Expects "Hello" from client, replies with "World"
 *
 * @since 1.0
 */

public class HelloWorldServer {
    
    public static void main(String[] args) throws InterruptedException {
        Context context = context(10);
        // Socket to Talk to Clients
        Socket responder = context.socket(REP);
        responder.bind(Constants.URL.TCP);
        while (!Thread.currentThread().isInterrupted()) {
            // Wait for next request from clients
            byte[] request = responder.recv(0);
            System.out.println(format("Received  %s", new String(request)));
            //Do Some business
            Thread.sleep(100);
            // Send reply back to client
            responder.send("World".getBytes(), 0);
        }
        responder.close();
        context.term();
    }
}
