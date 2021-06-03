package LB6;

//Виконав: Яринич В.П.
//Група: ТІ-92

//Варіант 21

//1. Організувати хеш-таблицю з відкритою адресацією, використовуючи хеш-функцію h (k) = trunc (M * Frac (k * d)),
// де d = (sqrt (5) -1) / 2, M - розмір хеш-таблиці.
// Організувати метод пошуку по ключу в цій хеш-таблиці.
// Результат пошуку - номер комірки зі знайденим ключем або (-1).

class Item{

    private String key;
    private int value;

    public Item(String key, int value) {

        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

class HashTable {

    private final Item[] table;
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

    public void insert(String key, int value) {
        Item item = new Item(key, value);
        int hash = hash(key);
        while(table[hash] != null) {
            if(table[hash].getKey().equals(key)) {
                System.out.println("Дане значення уже знаходиться в таблиці");
                return;
            }
            hash++;
            hash %= size;
        }
        table[hash] = item;
    }

    public void print() {
        System.out.println();
        for(int i = 0; i < size; i++)
            if(table[i] != null)
                System.out.println("Ключ: " + table[i].getKey() + "; Значення: " + table[i].getValue());
        System.out.println();
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

        hashTable.insert("rhino", 1);
        hashTable.insert("man", 2);
        hashTable.insert("computer", 3);
        hashTable.insert("home", 4);
        hashTable.insert("basket", 5);

        hashTable.print();

        hashTable.insert("home", 4);
        hashTable.insert("home", 7);

        hashTable.print();

        System.out.print("Номер комірки по ключу \"computer\": ");

        System.out.println(hashTable.find("computer"));

        System.out.print("Номер комірки по ключу \"hello\": ");

        System.out.println(hashTable.find("hello"));


    }
}
