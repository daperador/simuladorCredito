/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito.persistencia.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMarshaller;
import com.google.gson.Gson;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class CuotaMarshaller implements DynamoDBMarshaller<List<Cuota>>{

    @Override
    public String marshall(List<Cuota> t) {
        return new Gson().toJson(t);
    }

    @Override
    public List<Cuota> unmarshall(Class<List<Cuota>> type, String string) {
        return new Gson().fromJson(string, type);
    }
    
}
