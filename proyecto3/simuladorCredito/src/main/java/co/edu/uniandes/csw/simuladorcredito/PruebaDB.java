/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito;

import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.Administrador;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.LocalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import java.util.ArrayList;

/**
 *
 * @author Daniel
 */
public class PruebaDB {
    private final static BasicAWSCredentials credentials=new BasicAWSCredentials("AKIAIRBT4XN3M3SCL6JQ", "/dfp6UJEPOpt7cXfViPonCcqEVHordb8i/x1YdHf");
    static AmazonDynamoDBClient client = new AmazonDynamoDBClient(credentials);
    
    public static void main(String[] args) {
        //crearTabla();
        
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        /*Administrador adm=new Administrador();
        adm.setNombres("prueba 1");
        adm.setApellidos("prueba 2");
        adm.setPassword("xxx");
        adm.setIdentificador(2L);
        adm.setEmail("a");
        adm.setId(1L);
        mapper.save(adm);*/
    
        Administrador adm2=mapper.load(Administrador.class,1L);
        System.out.println("adm: "+adm2);
        System.out.println("adm: "+adm2.getId());
        System.out.println("adm: "+adm2.getNombres());
    }

    private static void crearTabla() {
        System.out.println(client.listTables().getTableNames().size());
        
        CreateTableRequest ctr=new CreateTableRequest()
                .withTableName("Administrador")
                .withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        
        ctr.setAttributeDefinitions(new ArrayList<AttributeDefinition>());
        ctr.getAttributeDefinitions().add(new AttributeDefinition("id", "N"));
        
        ctr.setKeySchema(new ArrayList<KeySchemaElement>());
        ctr.getKeySchema().add(new KeySchemaElement("id", "HASH"));
        
        client.createTable(ctr);
        
        System.out.println(client.listTables().getTableNames().size());
        
    }
}
