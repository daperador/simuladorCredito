/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito;

import co.edu.uniandes.csw.simuladorcredito.dao.AdministradorDAO;
import co.edu.uniandes.csw.simuladorcredito.dto.RegistroDTO;
import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.Administrador;
import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.SuperPojo;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.LocalSecondaryIndex;
import com.amazonaws.services.dynamodbv2.model.Projection;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class PruebaDB {
    static{
        try {
            AWSCredentials credentials=new PropertiesCredentials(new File("c:/tmp/dynamo.properties"));
            client = new AmazonDynamoDBClient(credentials);
        } catch (IOException ex) {
            Logger.getLogger(PruebaDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(PruebaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static AmazonDynamoDBClient client ;
    
    public static void main(String[] args) {
        //crearTabla();
        System.out.println(client.listTables().getTableNames().size());
        DynamoDBMapper mapper = new DynamoDBMapper(client);
        /*Administrador adm=new Administrador();
        adm.setNombres("prueba 1");
        adm.setApellidos("prueba 2");
        adm.setPassword("xxx");
        adm.setIdentificador(2L);
        adm.setEmail("a");
        adm.setId(1L);
        mapper.save(adm);*/
    
        Administrador adm2=mapper.load(Administrador.class,4L);
        System.out.println("adm: "+adm2);
        System.out.println("adm: "+adm2.getId());
        System.out.println("adm: "+adm2.getNombres());
        System.out.println("adm: "+adm2.getApellidos());
        System.out.println("adm: "+adm2.getPassword());
        
        
        Map<String, List<Object>> datos=mapper.batchLoad(new ArrayList<Object>());
        System.out.println(datos);
        
        long cuantos=mapper.count(Administrador.class, new DynamoDBScanExpression());
        Administrador adm3=new Administrador();
        adm3.setApellidos("Wilches");
        adm3.setEmail("fredy.wilches@gmail.com");
        adm3.setId(cuantos+1);
        adm3.setNombres("Fredy");
        adm3.setPassword("pwd");
        
        mapper.save(adm3);
        
        cuantos=mapper.count(Administrador.class, new DynamoDBScanExpression());
        
        System.out.println(cuantos);
        
        AdministradorDAO dao=new AdministradorDAO();
        SuperPojo entity=dao.leer(Administrador.class, 5L);
        System.out.println(entity);
        
        
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
