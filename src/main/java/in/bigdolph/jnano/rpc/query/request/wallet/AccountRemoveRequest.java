package in.bigdolph.jnano.rpc.query.request.wallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import in.bigdolph.jnano.rpc.query.request.RpcRequest;
import in.bigdolph.jnano.rpc.query.response.AccountRemoveResponse;

public class AccountRemoveRequest extends RpcRequest<AccountRemoveResponse> {
    
    @Expose
    @SerializedName("wallet")
    private String walletId;
    
    @Expose
    @SerializedName("account")
    private String account;
    
    
    public AccountRemoveRequest(String walletId, String account) {
        super("account_remove", AccountRemoveResponse.class);
        this.walletId = walletId;
        this.account = account;
    }
    
    
    
    public String getWalletId() {
        return walletId;
    }
    
    public String getAccount() {
        return account;
    }
    
}