package uk.oczadly.karl.jnano.rpc.request.wallet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import uk.oczadly.karl.jnano.rpc.request.RpcRequest;
import uk.oczadly.karl.jnano.rpc.response.ResponseValidation;

/**
 * This request class is used to check whether a wallet's password is valid.
 * The server responds with a {@link ResponseValidation} data object.<br>
 * Calls the internal RPC method {@code password_valid}.
 *
 * @see <a href="https://docs.nano.org/commands/rpc-protocol/#password_valid">Official RPC documentation</a>
 */
public class RequestPasswordValid extends RpcRequest<ResponseValidation> {
    
    @Expose @SerializedName("wallet")
    private final String walletId;
    
    
    /**
     * @param walletId the wallet's ID
     */
    public RequestPasswordValid(String walletId) {
        super("password_valid", ResponseValidation.class);
        this.walletId = walletId;
    }
    
    
    /**
     * @return the wallet's ID
     */
    public String getWalletId() {
        return walletId;
    }
    
}
