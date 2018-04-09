package in.bigdolph.jnano.util;

import com.rfksystems.blake2b.Blake2b;
import in.bigdolph.jnano.util.helper.BaseEncoder;
import in.bigdolph.jnano.util.helper.EncodingHelper;

import java.util.Arrays;

public class AccountUtil {
    
    public static final BaseEncoder ADDRESS_ENCODER =   EncodingHelper.NANO_BASE32;
    public static final BaseEncoder KEY_ENCODER =       EncodingHelper.HEXADECIMAL;
    
    public static final String ADDRESS_PREFIX =     "xrb";
    public static final String PREFIX_CHARS =       "_-";
    public static final char DEFAULT_PREFIX_CHAR =  PREFIX_CHARS.charAt(0);
    
    public static final String GENESIS_ADDRESS =    "xrb_3t6k35gi95xu6tergt6p69ck76ogmitsa8mnijtpxm9fkcm736xtoncuohr3";
    public static final String LANDING_ADDRESS =    "xrb_13ezf4od79h1tgj9aiu4djzcmmguendtjfuhwfukhuucboua8cpoihmh8byo";
    public static final String FAUCET_ADDRESS =     "xrb_35jjmmmh81kydepzeuf9oec8hzkay7msr6yxagzxpcht7thwa5bus5tomgz9";
    public static final String BURN_ADDRESS =       "xrb_1111111111111111111111111111111111111111111111111111hifc8npp";
    
    
    
    public static boolean isAddressValid(byte[] keyBytes, String hash) {
        byte[] hashBytes;
        try {
            hashBytes = ADDRESS_ENCODER.decode(hash);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return Arrays.equals(hashBytes, generateChecksumBytes(keyBytes));
    }
    
    public static boolean isAddressValid(String keyAddr, String prefix) {
        //Parse address
        SegmentedAddress address;
        try {
            address = segmentAddress(keyAddr);
        } catch (IllegalArgumentException e) {
            return false;
        }
        //Check prefix
        if(prefix != null && !address.getPrefix().equalsIgnoreCase(prefix)) return false;
        //Convert to bytes
        byte[] keyBytes;
        try {
            keyBytes = ADDRESS_ENCODER.decode(address.getEncodedKey());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return isAddressValid(keyBytes, address.getEncodedChecksum());
    }
    
    public static boolean isAddressValid(String address) {
        return isAddressValid(address, ADDRESS_PREFIX);
    }
    
    
    public static String getHexAddressFromKey(byte[] keyBytes) {
        return KEY_ENCODER.encode(keyBytes);
    }
    
    public static String getHexAddressFromKey(SegmentedAddress address) {
        return getHexAddressFromKey(ADDRESS_ENCODER.decode(address.getEncodedKey()));
    }
    
    public static String getHexAddressFromKey(String keyAddr) {
        return getHexAddressFromKey(segmentAddress(keyAddr));
    }
    
    
    public static String getAddressFromKey(byte[] keyBytes, String prefix) {
        String encodedKey = ADDRESS_ENCODER.encode(keyBytes);
        String encodedChecksum = generateChecksum(keyBytes);
        return prefix.toLowerCase() + DEFAULT_PREFIX_CHAR + encodedKey + encodedChecksum;
    }
    
    public static String getAddressFromKey(String publicKey, String prefix) {
        return getAddressFromKey(KEY_ENCODER.decode(publicKey), prefix);
    }
    
    public static String getAddressFromKey(String publicKey) {
        return getAddressFromKey(publicKey, ADDRESS_PREFIX);
    }
    
    
    public static byte[] generateChecksumBytes(byte[] keyBytes) {
        //Digest
        Blake2b digest = new Blake2b(null, 5, null, null); //Blake2b algorithm, 5 bytes length
        digest.update(keyBytes, 0, keyBytes.length);
        byte[] out = new byte[5];
        digest.digest(out, 0);
        
        //Reverse byte array
        byte[] rev = new byte[out.length];
        for(int i=0; i<out.length; i++) {
            rev[i] = out[out.length - i - 1];
        }
        
        return rev;
    }
    
    public static String generateChecksum(byte[] keyBytes) {
        return ADDRESS_ENCODER.encode(generateChecksumBytes(keyBytes));
    }
    
    public static String generateChecksum(String keyAddress) {
        return generateChecksum(ADDRESS_ENCODER.decode(keyAddress));
    }
    
    
    /**
     * Converts an address string into the various different data segments.
     *
     * @param address the address to split
     * @return the addresses data segments
     */
    public static SegmentedAddress segmentAddress(String address) {
        if(address == null) throw new IllegalArgumentException("Address string cannot be null");
        if(address.length() < 60) throw new IllegalArgumentException("Invalid address format");
        
        address = address.toLowerCase(); //Convert to lowercase
        
        String prefix = "";
        if(address.length() > 60) { //Has prefix
            prefix = address.substring(0, address.length() - 61);
            if(PREFIX_CHARS.indexOf(address.charAt(address.length() - 61)) == -1) throw new IllegalArgumentException("Invalid address format");
        }
        
        return new SegmentedAddress(
                prefix,
                address.substring(address.length() - 60, address.length() - 8),
                address.substring(address.length() - 8));
    }
    
    
    
    public static class SegmentedAddress {
        
        private String prefix, key, checksum;
        
        SegmentedAddress(String prefix, String key, String checksum) {
            this.prefix = prefix;
            this.key = key;
            this.checksum = checksum;
        }
        
        
        public String getPrefix() {
            return prefix;
        }
        
        public String getEncodedKey() {
            return key;
        }
        
        public String getEncodedChecksum() {
            return checksum;
        }
        
        
        @Override
        public String toString() {
            return getPrefix() + PREFIX_CHARS.charAt(0) + getEncodedKey() + getEncodedChecksum();
        }
        
    }
    
}
