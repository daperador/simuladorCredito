/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.simuladorcredito;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.elasticache.AmazonElastiCacheClient;
import com.amazonaws.services.elasticache.model.CacheCluster;
import com.amazonaws.services.elasticache.model.CreateCacheClusterRequest;
import com.amazonaws.services.elasticache.model.DeleteCacheClusterRequest;
import com.amazonaws.services.elasticache.model.DescribeCacheClustersResult;
import com.amazonaws.services.elasticache.model.Endpoint;
import com.amazonaws.services.elasticache.model.ModifyCacheClusterRequest;
import java.io.File;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.spy.memcached.MemcachedClient;

/**
 *
 * @author Fredy
 */
public class PruebaElasticCache {
    
    public static void main(String[] args){
        try {
            //eliminarCache();
            //crearCache();
            getInfoCache();
            crearMensaje();
            //leerMensaje();        
        } catch (Exception ex) {
            Logger.getLogger(PruebaCola.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void getInfoCache() throws Exception {
        
        AWSCredentials credentials=new PropertiesCredentials(new File("/tmp/dynamo.properties"));
        AmazonElastiCacheClient client = new AmazonElastiCacheClient(credentials);
        
        DescribeCacheClustersResult res=client.describeCacheClusters();
        List<CacheCluster> ccs=res.getCacheClusters();
        for (CacheCluster cc:ccs){
            System.out.println(cc.getConfigurationEndpoint().getAddress());
            System.out.println(cc.getConfigurationEndpoint().getPort());
            System.out.println(cc.getCacheClusterStatus());
        }
    }
    
    
    private static void crearCache() throws Exception {
        
        AWSCredentials credentials=new PropertiesCredentials(new File("/tmp/dynamo.properties"));
        AmazonElastiCacheClient client = new AmazonElastiCacheClient(credentials);
        
        CacheCluster cc = client.createCacheCluster(new CreateCacheClusterRequest("myclustercache", 1, "cache.t1.micro", "memcached", null));
        //CacheCluster cc = client.modifyCacheCluster(new ModifyCacheClusterRequest("myclustercache"));
        Endpoint ep=cc.getConfigurationEndpoint();
        System.out.println(ep.getAddress());
        System.out.println(ep.getPort());
    }
    
    private static void eliminarCache() throws Exception {
        
        AWSCredentials credentials=new PropertiesCredentials(new File("/tmp/dynamo.properties"));
        AmazonElastiCacheClient client = new AmazonElastiCacheClient(credentials);
        List<String> sec=new ArrayList();
        sec.add("default");
        CacheCluster cc = client.deleteCacheCluster(new DeleteCacheClusterRequest("myclustercache"));
    }

    private static void crearMensaje() throws Exception {
        String configEndpoint = "myclustercache.o78wts.cfg.usw2.cache.amazonaws.com";
        //String configEndpoint = "myclustercache.vyqp3g.cfg.use1.cache.amazonaws.com";
        Integer clusterPort = 11211;
        MemcachedClient client = new MemcachedClient(new InetSocketAddress(configEndpoint, clusterPort)); 
        System.out.println(client.isConfigurationInitialized());
        // The client will connect to the other cache nodes automatically
        // Store a data item for an hour. The client will decide which cache host will store this item. 
        //client.set("theKey", 3600, "This is the data value");
        
        /*AWSCredentials credentials=new PropertiesCredentials(new File("/tmp/dynamo.properties"));
        AmazonElastiCacheClient client = new AmazonElastiCacheClient(credentials);
        CreateQueueResult response = client.createQueue(new CreateQueueRequest("workerQueue"));
        client.sendMessage(response.getQueueUrl(), "hola mundo: mensaje de prueba");*/
    }

    private static void leerMensaje() throws Exception {
        String configEndpoint = "myclustercache.vyqp3g.cfg.use1.cache.amazonaws.com";
        Integer clusterPort = 11211;
        MemcachedClient client = new MemcachedClient(new InetSocketAddress(configEndpoint, clusterPort)); 
        // The client will connect to the other cache nodes automatically
        // Store a data item for an hour. The client will decide which cache host will store this item. 
        Object dato = client.get("theKey");
        System.out.println(dato);
        
    }
    
}
