import me.miroslavst.hashtable.AdvancedHashTable;

public class Application {
    public static void main(String[] args) throws Exception {
        AdvancedHashTable<Integer> advancedHashTable = new AdvancedHashTable<>();

        advancedHashTable.read(Integer.class);

        System.out.println(advancedHashTable.get("proba"));
        System.out.println(advancedHashTable.get("test"));
        System.out.println(advancedHashTable.get("item"));

        advancedHashTable.put("proba", 55);

        System.out.println("---------------------");
        System.out.println(advancedHashTable.get("proba"));
    }
}
