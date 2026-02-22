package ecom.icet.util;

public class IdGenerator {
    public static String generateNextId(String lastId, String prefix) {
        if (lastId == null || lastId.isEmpty()) {
            return prefix + "001";
        }

        String numericPartString = lastId.substring(prefix.length());
        int numericPart = Integer.parseInt(numericPartString);
        numericPart++;

        return prefix + String.format("%03d", numericPart);
    }
}