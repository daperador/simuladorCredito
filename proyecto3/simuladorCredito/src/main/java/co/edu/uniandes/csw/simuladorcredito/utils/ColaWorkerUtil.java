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

/**
 *
 * @author Daniel
 */
public class ColaWorkerUtil {
    
    public static void crearMensaje(String mensaje) throws Exception {
        AWSCredentials credentials=new PropertiesCredentials(new File("/tmp/dynamo.properties"));
        AmazonSQSClient client = new AmazonSQSClient(credentials);
        CreateQueueResult response = client.createQueue(new CreateQueueRequest("workerQueue"));
        client.sendMessage(response.getQueueUrl(), mensaje);
    }

    public static String leerMensaje() throws Exception {
        AWSCredentials credentials=new PropertiesCredentials(new File("/tmp/dynamo.properties"));
        AmazonSQSClient client = new AmazonSQSClient(credentials);
        CreateQueueResult response = client.createQueue(new CreateQueueRequest("workerQueue"));
        ReceiveMessageResult message = client.receiveMessage(response.getQueueUrl());
        if(message.getMessages().isEmpty()){
            return null;
        }else{
            return message.getMessages().get(0).getBody();
        }
    }
}
