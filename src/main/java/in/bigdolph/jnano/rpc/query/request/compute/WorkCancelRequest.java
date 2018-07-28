package in.bigdolph.jnano.rpc.query.request.compute;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import in.bigdolph.jnano.rpc.query.request.RpcRequest;
import in.bigdolph.jnano.rpc.query.response.RpcResponse;

public class WorkCancelRequest extends RpcRequest<RpcResponse> {
    
    @Expose
    @SerializedName("hash")
    private String blockHash;
    
    
    public WorkCancelRequest(String blockHash) {
        super("work_cancel", RpcResponse.class);
        this.blockHash = blockHash;
    }
    
    
    
    public String getBlockHash() {
        return blockHash;
    }
    
}