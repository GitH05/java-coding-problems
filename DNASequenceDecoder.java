import java.util.*;

public class DNASequenceDecoder {

    public static String extractDNABases(String sequenceBinary) {
        // Checking if the string starts with the DNA identifier
        if (!sequenceBinary.startsWith("000")) {
            return "";
        }
        // Define the binary to nucleic base mapping
        Map<String, String> binaryToBase = new HashMap<>();
        binaryToBase.put("011", "A");
        binaryToBase.put("010", "G");
        binaryToBase.put("001", "C");
        binaryToBase.put("101", "T");
        binaryToBase.put("110", "U");

        // Removing  the '000' DNA prefix
        String dnaBits = sequenceBinary.substring(3);   //substring extract the given index like( o to 3-1)
        StringBuilder result = new StringBuilder();
        // System.out.println(binaryToBase.get("A"));
        // System.out.println(binaryToBase.get("110"));

        // Reading the bits in chunks of 3 that used to compare with each key
        for (int i = 0; i <= dnaBits.length() - 3; i += 3) {    //dnaBits.length()-3 == avoid lengthBound error
            String triplet = dnaBits.substring(i, i + 3);   //triplets store the extract values by substring(0,0+3 -1)
            String base = binaryToBase.getOrDefault(triplet, "");   //base stores and compare the value existences
            if (base.equals("U")) {
                base = "T"; //if there is "U" then it should be replaced by "T"
            }
            result.append(base);    //All the pairs stored accordingly in the result
        }

        return result.toString();   //return the final nucleicbase in string
    }

    public static void main(String[] args) {
        String sequenceBinary = "000001001011101010010110011";
        String result = extractDNABases(sequenceBinary);
        System.out.println("Nucleic Base Sequence: " + result);  // Output: AGCTT
        
    }
}
