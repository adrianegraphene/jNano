package in.bigdolph.jnano.util;

import in.bigdolph.jnano.tests.UtilTests;
import in.bigdolph.jnano.util.helper.EncodingHelper;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

@Category(UtilTests.class)
public class AccountUtilTest {
    
    @Test @Category(UtilTests.class)
    public void testIsAddressValid() {
        assertTrue(AccountUtil.isAddressValid("xrb_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz")); //Valid
        assertTrue(AccountUtil.isAddressValid("ban_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz", "BAN")); //Valid
        assertFalse(AccountUtil.isAddressValid("ban_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz")); //Invalid prefix
        assertFalse(AccountUtil.isAddressValid("xrb_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpia")); //Invalid checksum
        assertFalse(AccountUtil.isAddressValid("xrb_34qjpc8t1u6wnb584pc4wsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz")); //Invalid length
        assertFalse(AccountUtil.isAddressValid("xrb_24qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz")); //Invalid key character
        assertFalse(AccountUtil.isAddressValid("xrb_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpi2")); //Invalid hash character
    }
    
    
    @Test @Category(UtilTests.class)
    public void testGetHexAddressFromKey() {
        //From plain address
        assertEquals("8AF1B28DA06C9CA2466159428733B971068BF154DBA2AB10372510D52E86CC97",
                AccountUtil.getHexAddressFromKey("34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz"));
    
        //From standard address
        assertEquals("8AF1B28DA06C9CA2466159428733B971068BF154DBA2AB10372510D52E86CC97",
                AccountUtil.getHexAddressFromKey("xrb_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz"));
        
        //From address with custom prefix
        assertEquals("8AF1B28DA06C9CA2466159428733B971068BF154DBA2AB10372510D52E86CC97",
                AccountUtil.getHexAddressFromKey("ban_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz"));
        
        //From bytes
        byte[] bytes = new byte[32];
        for(int i=0; i<bytes.length; i++) bytes[i] = 0;
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000", AccountUtil.getHexAddressFromKey(bytes));
    }
    
    
    @Test @Category(UtilTests.class)
    public void testGetAddressFromKey() {
        //From hex
        assertEquals("xrb_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz",
                AccountUtil.getAddressFromKey("8AF1B28DA06C9CA2466159428733B971068BF154DBA2AB10372510D52E86CC97"));
        
        //From hex with custom prefix
        assertEquals("ban_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz",
                AccountUtil.getAddressFromKey("8AF1B28DA06C9CA2466159428733B971068BF154DBA2AB10372510D52E86CC97", "BAN"));
        
        //From bytes with custom prefix
        byte[] bytes = new byte[32];
        for(int i=0; i<bytes.length; i++) bytes[i] = 0;
        assertEquals("xrb_1111111111111111111111111111111111111111111111111111hifc8npp", AccountUtil.getAddressFromKey(bytes, "xrb"));
    }
    
    
    @Test @Category(UtilTests.class)
    public void testGenerateChecksum() {
        assertEquals("sgoacpiz", AccountUtil.generateChecksum("34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6q"));
    }
    
}