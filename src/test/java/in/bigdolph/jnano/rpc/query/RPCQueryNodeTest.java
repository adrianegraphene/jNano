package in.bigdolph.jnano.rpc.query;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import in.bigdolph.jnano.rpc.exception.RPCQueryException;
import in.bigdolph.jnano.rpc.query.request.RPCRequest;
import in.bigdolph.jnano.rpc.query.request.node.NodeVersionRequest;
import in.bigdolph.jnano.rpc.query.response.RPCResponse;
import in.bigdolph.jnano.rpc.query.response.specific.NodeVersionResponse;
import org.junit.Test;

import javax.management.Query;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

public class RPCQueryNodeTest {
    
    private TestNode node;
    
    public RPCQueryNodeTest() throws Exception {
        this.node = new TestNode();
    }
    
    
    @Test
    public void testSyncQuery() throws Exception {
        NodeVersionResponse res = node.processRequest(new NodeVersionRequest());
        assertNotNull(res);
        System.out.println(res.getNodeVendor());
    }
    
    @Test
    public void testAsyncQuery() throws Exception {
        //Test valid query
        TestCallback callback = new TestCallback();
        Future<RPCResponse> futureRes = node.processRequestAsync(new TestRequest("version"), callback);
        assertNotNull(futureRes);
        while(!callback.processed); //Wait for query to respond
        System.out.println("Async query processed");
        assertNull(callback.exception);
        assertNotNull(callback.response);
    
        //Test valid query
        callback = new TestCallback();
        futureRes = node.processRequestAsync(new TestRequest("invalid_command_ayy_lmao"), callback);
        assertNotNull(futureRes);
        while(!callback.processed); //Wait for query to respond
        System.out.println("Async query processed");
        assertNotNull(callback.exception);
        assertTrue(callback.exception instanceof RPCQueryException);
        assertNull(callback.response);
    }
    
    
    private static class TestCallback implements QueryCallback<RPCRequest<RPCResponse>, RPCResponse> {
        volatile boolean processed = false;
        volatile RPCResponse response;
        volatile Exception exception;
        
        @Override
        public void onResponse(RPCRequest<RPCResponse> query, RPCResponse response) {
            this.response = response;
            this.processed = true;
        }
        
        @Override
        public void onFailure(RPCRequest<RPCResponse> query, Exception ex) {
            this.exception = ex;
            this.processed = true;
        }
    }
    
    public static class TestRequest extends RPCRequest<RPCResponse> {
        public TestRequest(String cmd) {
            super(cmd, RPCResponse.class);
        }
    }
    
    
    
    @Test
    public void testEmptyArray() throws Exception {
        TestNode node = new TestNode();
        ArrayTestResponse deserialized;
        
        //2 items
        System.out.println("Test 2 items");
        deserialized = node.getGson().fromJson("{arr:[1,2]}", ArrayTestResponse.class);
        assertNotNull(deserialized);
        assertNotNull(deserialized.array);
        assertEquals(2, deserialized.array.length);
    
        //0 items
        System.out.println("Test empty array");
        deserialized = node.getGson().fromJson("{arr:[]}", ArrayTestResponse.class);
        assertNotNull(deserialized);
        assertNotNull(deserialized.array);
        assertEquals(0, deserialized.array.length);
    
        //empty string
        System.out.println("Test empty string");
        deserialized = node.getGson().fromJson("{arr:\"\"}", ArrayTestResponse.class);
        assertNotNull(deserialized);
        assertNotNull(deserialized.array);
        assertEquals(0, deserialized.array.length);
    }
    
    @Test
    public void testEmptyCollection() throws Exception {
        TestNode node = new TestNode();
        CollectionTestResponse deserialized;
        
        //2 items
        System.out.println("Test 2 items");
        deserialized = node.getGson().fromJson("{col:[1,2]}", CollectionTestResponse.class);
        assertNotNull(deserialized);
        assertNotNull(deserialized.col);
        assertEquals(2, deserialized.col.size());
        
        //0 items
        System.out.println("Test empty collection");
        deserialized = node.getGson().fromJson("{col:[]}", CollectionTestResponse.class);
        assertNotNull(deserialized);
        assertNotNull(deserialized.col);
        assertEquals(0, deserialized.col.size());
        
        //empty string
        System.out.println("Test empty string");
        deserialized = node.getGson().fromJson("{col:\"\"}", CollectionTestResponse.class);
        assertNotNull(deserialized);
        assertNotNull(deserialized.col);
        assertEquals(0, deserialized.col.size());
    }
    
    @Test
    public void testEmptyMap() throws Exception {
        TestNode node = new TestNode();
        MapTestResponse deserialized;
        
        //2 items
        System.out.println("Test 2 items");
        deserialized = node.getGson().fromJson("{map:{1:1,2:2}}", MapTestResponse.class);
        assertNotNull(deserialized);
        assertNotNull(deserialized.map);
        assertEquals(2, deserialized.map.size());
        
        //0 items
        System.out.println("Test empty map");
        deserialized = node.getGson().fromJson("{map:{}}", MapTestResponse.class);
        assertNotNull(deserialized);
        assertNotNull(deserialized.map);
        assertEquals(0, deserialized.map.size());
        
        //empty string
        System.out.println("Test empty string");
        deserialized = node.getGson().fromJson("{map:\"\"}", MapTestResponse.class);
        assertNotNull(deserialized);
        assertNotNull(deserialized.map);
        assertEquals(0, deserialized.map.size());
    }
    
    
    
    
    private static class ArrayTestResponse {
        @Expose
        @SerializedName("arr")
        public int[] array;
    }
    
    private static class MapTestResponse {
        @Expose
        @SerializedName("map")
        public Map<Integer, Integer> map;
    }
    
    private static class CollectionTestResponse {
        @Expose
        @SerializedName("col")
        public List<Integer> col;
    }
    
}