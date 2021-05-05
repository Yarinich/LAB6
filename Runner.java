package LB6;

class Item{

    private String key;

    public Item(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

class HashTable {

    //массив для хранения элементов
    private final Item[] table;
    //количество элементов в таблице
    private int count;
    //размер таблицы
    private final int size;

    public HashTable(int size) {
        this.size = size;
        table = new Item[size];
    }

    private int hash(String key) {
        int hash = 0;
        int k = key.length();
        double d = ((Math.sqrt(5) - 1 ) / 2);
        for(int i = 0; i < k; i++) {
            hash = (int) (size * ((int) (k * d) - (k * d)));
            if(hash < 0) {
                hash *= (-1);
            }
        }
        return hash;
    }

    public void insert(String key) {
        Item item = new Item(key);
        int hash = hash(key);
        while(table[hash] != null) {
            hash++;
            hash %= size;
        }
        table[hash] = item;
    }

    public void print() {
        for(int i = 0; i < size; i++)
            if(table[i] != null)
                System.out.println(i + " " + table[i].getKey());
    }

    public int find(String key) {
        int hash = hash(key);
        while(table[hash] != null) {
            if(table[hash].getKey().equals(key))
                return hash;
            hash++;
            hash = hash % size;
        }

        return -1;
    }
}


public class Runner {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(97);

        hashTable.insert("rhino");
        hashTable.insert("man");
        hashTable.insert("computer");
        hashTable.insert("home");
        hashTable.insert("basket");
        hashTable.insert("home");
        hashTable.insert("home");

        System.out.println(hashTable.find("computer"));

        hashTable.print();
    }
}
