package in.bigdolph.jnano.util;

import in.bigdolph.jnano.tests.UtilTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.junit.Assert.*;

@Category(UtilTests.class)
public class AddressUtilTest {
    
    @Test @Category(UtilTests.class)
    public void testIsAddressValid() {
        assertTrue(AddressUtil.isAddressAndPrefixValid("xrb_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz")); //Valid (XRB)
        assertTrue(AddressUtil.isAddressAndPrefixValid("nano_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz")); //Valid (NANO)
        assertFalse(AddressUtil.isAddressAndPrefixValid("ban_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz")); //Invalid prefix
        
        assertTrue(AddressUtil.isAddressValid("ban_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz", "BAN")); //Valid
        assertTrue(AddressUtil.isAddressValid("ban_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz")); //Valid (no expected prefix specified)
        
        assertFalse(AddressUtil.isAddressValid("xrb_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpia")); //Invalid checksum
        assertFalse(AddressUtil.isAddressValid("xrb_34qjpc8t1u6wnb584pc4wsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz")); //Invalid length
        assertFalse(AddressUtil.isAddressValid("xrb_24qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz")); //Invalid key character
        assertFalse(AddressUtil.isAddressValid("xrb_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpi2")); //Invalid hash character
    }
    
    
    @Test @Category(UtilTests.class)
    public void testGetHexAddressFromKey() {
        //From plain address
        assertEquals("8AF1B28DA06C9CA2466159428733B971068BF154DBA2AB10372510D52E86CC97",
                AddressUtil.getHexAddressFromKey("34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz"));
    
        //From standard address
        assertEquals("8AF1B28DA06C9CA2466159428733B971068BF154DBA2AB10372510D52E86CC97",
                AddressUtil.getHexAddressFromKey("xrb_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz"));
        
        //From address with custom prefix
        assertEquals("8AF1B28DA06C9CA2466159428733B971068BF154DBA2AB10372510D52E86CC97",
                AddressUtil.getHexAddressFromKey("ban_34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz"));
        
        //From bytes
        byte[] bytes = new byte[32];
        for(int i=0; i<bytes.length; i++) bytes[i] = 0;
        assertEquals("0000000000000000000000000000000000000000000000000000000000000000", AddressUtil.getHexAddressFromKey(bytes));
    }
    
    
    @Test @Category(UtilTests.class)
    public void testGetAddressFromKey() {
        //From hex
        assertEquals(AddressUtil.DEFAULT_PREFIX_NAME + AddressUtil.DEFAULT_PREFIX_CHAR + "34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz",
                AddressUtil.getAddressFromKey("8AF1B28DA06C9CA2466159428733B971068BF154DBA2AB10372510D52E86CC97"));
        
        //From hex with custom prefix
        assertEquals("ban" + AddressUtil.DEFAULT_PREFIX_CHAR + "34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6qsgoacpiz",
                AddressUtil.getAddressFromKey("8AF1B28DA06C9CA2466159428733B971068BF154DBA2AB10372510D52E86CC97", "BAN"));
        
        //From bytes with custom prefix
        byte[] bytes = new byte[32];
        for(int i=0; i<bytes.length; i++) bytes[i] = 0;
        assertEquals("xrb" + AddressUtil.DEFAULT_PREFIX_CHAR + "1111111111111111111111111111111111111111111111111111hifc8npp", AddressUtil.getAddressFromKey(bytes, "xrb"));
    }
    
    
    @Test @Category(UtilTests.class)
    public void testGenerateChecksum() {
        assertEquals("sgoacpiz", AddressUtil.generateChecksum("34qjpc8t1u6wnb584pc4iwsukwa8jhrobpx4oea5gbaitnqafm6q"));
    }
    
}