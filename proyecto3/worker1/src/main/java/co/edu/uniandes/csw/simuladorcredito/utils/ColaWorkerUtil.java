/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito.utils;

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
public class ColaWorkerUtil {
    private static AWSCredentials credentials;
    private static AmazonSQSClient client;
    private static CreateQueueResult response;
    static{
        try {
            credentials=new PropertiesCredentials(new File("/tmp/dynamo.properties"));
            client = new AmazonSQSClient(credentials);
            response = client.createQueue(new CreateQueueRequest("workerQueue"));
        } catch (IOException ex) {
            Logger.getLogger(ColaWorkerUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ColaWorkerUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void crearMensaje(String mensaje) {
        client.sendMessage(response.getQueueUrl(), mensaje);
    }

    private static ReceiveMessageResult message;
    
    public static String leerMensaje() {
        message = client.receiveMessage(response.getQueueUrl());
        if(message.getMessages().isEmpty()){
            return null;
        }else{
            return message.getMessages().get(0).getBody();
        }
    }
    
    public static void borrarUltimoMensaje(){
        if(message!=null){
            if(!message.getMessages().isEmpty()){
                client.deleteMessage(response.getQueueUrl(), message.getMessages().get(0).getReceiptHandle());
            }
        }
    }
}
