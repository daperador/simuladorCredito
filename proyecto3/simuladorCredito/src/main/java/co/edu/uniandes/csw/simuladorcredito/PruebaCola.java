/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class PruebaCola {
    
    public static void main(String[] args){
        try {
            //crearMensaje();
            leerMensaje();        
        } catch (Exception ex) {
            Logger.getLogger(PruebaCola.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void crearMensaje() throws Exception {
        AWSCredentials credentials=new PropertiesCredentials(new File("/tmp/dynamo.properties"));
        AmazonSQSClient client = new AmazonSQSClient(credentials);
        CreateQueueResult response = client.createQueue(new CreateQueueRequest("workerQueue"));
        client.sendMessage(response.getQueueUrl(), "hola mundo: mensaje de prueba");
    }

    private static void leerMensaje() throws Exception {
        AWSCredentials credentials=new PropertiesCredentials(new File("/tmp/dynamo.properties"));
        AmazonSQSClient client = new AmazonSQSClient(credentials);
        CreateQueueResult response = client.createQueue(new CreateQueueRequest("workerQueue"));
        ReceiveMessageResult message = client.receiveMessage(response.getQueueUrl());
        System.out.println(message.getMessages());
        System.out.println(message.getMessages().get(0));
        System.out.println(message.getMessages().get(0).getBody());
    }
    
}
