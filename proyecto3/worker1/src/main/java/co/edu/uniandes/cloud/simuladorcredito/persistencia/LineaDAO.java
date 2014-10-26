/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.cloud.simuladorcredito.persistencia;

import co.edu.uniandes.cloud.simuladorcredito.jpa.Linea;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class LineaDAO extends SuperDAO<Linea>{
    
    public List<Linea> getLineaAdministrador(String idAdministrador){
        DynamoDBScanExpression dbscan = new DynamoDBScanExpression();
        dbscan.addFilterCondition("administrador", 
                new Condition()
                        .withComparisonOperator(ComparisonOperator.EQ)
                        .withAttributeValueList(new AttributeValue().withN(idAdministrador)));
        
        List<Linea> lista = mapper.scan(Linea.class, dbscan);
        return lista;
    }
}
