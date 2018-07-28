package in.bigdolph.jnano.rpc.query.request.ledger;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import in.bigdolph.jnano.rpc.query.request.RpcRequest;
import in.bigdolph.jnano.rpc.query.response.AccountResponse;

public class BlockAccountRequest extends RpcRequest<AccountResponse> {
    
    @Expose
    @SerializedName("hash")
    private String blockHash;
    
    
    public BlockAccountRequest(String blockHash) {
        super("block_account", AccountResponse.class);
        this.blockHash = blockHash;
    }
    
    
    
    public String getBlockHash() {
        return blockHash;
    }
    
}