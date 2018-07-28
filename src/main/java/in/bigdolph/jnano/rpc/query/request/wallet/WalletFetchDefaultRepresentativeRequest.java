package in.bigdolph.jnano.rpc.query.request.wallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import in.bigdolph.jnano.rpc.query.request.RpcRequest;
import in.bigdolph.jnano.rpc.query.response.AccountResponse;

public class WalletFetchDefaultRepresentativeRequest extends RpcRequest<AccountResponse> {
    
    @Expose
    @SerializedName("wallet")
    private String walletId;
    
    
    public WalletFetchDefaultRepresentativeRequest(String walletId) {
        super("wallet_representative", AccountResponse.class);
        this.walletId = walletId;
    }
    
    
    
    public String getWalletId() {
        return walletId;
    }
    
}