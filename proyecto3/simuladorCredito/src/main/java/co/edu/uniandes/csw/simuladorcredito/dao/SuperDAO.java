/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.simuladorcredito.dao;

import co.edu.uniandes.csw.simuladorcredito.PruebaDB;
import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.Secuencia;
import co.edu.uniandes.csw.simuladorcredito.persistencia.entity.SuperPojo;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fredy
 */
public class SuperDAO <T extends SuperPojo> {
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
    private static AmazonDynamoDBClient client;
    protected static DynamoDBMapper mapper = new DynamoDBMapper(client);
    
    public SuperPojo insertar(SuperPojo objeto){
        long cuantos=mapper.count(objeto.getClass(), new DynamoDBScanExpression());
        objeto.setId(cuantos+1);
        mapper.save(objeto);
        return objeto;
    }
    
    public void actualizar(SuperPojo objeto){
        mapper.save(objeto);
    }
    
    public T leer(Class clase, Long llave){
        return (T)mapper.load(clase, llave);
    }
    
    
}
