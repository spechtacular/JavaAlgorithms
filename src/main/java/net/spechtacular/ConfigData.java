package net.spechtacular;

import java.util.List;
import java.util.Objects;

public class ConfigData {
    private String version;
    private String released;
    private int countingMin;
    private int countingMax;
    private int radix;
    private int radixWidth;

    private List< String > sortNames;
    private List< Integer > arrayToBeSorted;
    private List< Integer > radixArrayToBeSorted;
    private List< String > radixStringArrayToBeSorted;
    private List< Integer > countingArrayToBeSorted;
    private List< String > listNames;

    public List<String> getHashTable() {
        return hashTable;
    }

    public void setHashTable(List<String> hashTable) {
        this.hashTable = hashTable;
    }

    private List<String> hashTable;

    public List<String> getBinarySort() {
        return binarySort;
    }

    public void setBinarySort(List<String> binarySort) {
        this.binarySort = binarySort;
    }

    private List<String> binarySort;

    public List<String> getQueueNames() {
        return queueNames;
    }

    public void setQueueNames(List<String> queueNames) {
        this.queueNames = queueNames;
    }

    @Override
    public String toString() {
        return "ConfigData{" +
                "version='" + version + '\'' +
                ", released='" + released + '\'' +
                ", countingMin=" + countingMin +
                ", countingMax=" + countingMax +
                ", radix=" + radix +
                ", radixWidth=" + radixWidth +
                ", sortNames=" + sortNames +
                ", arrayToBeSorted=" + arrayToBeSorted +
                ", radixArrayToBeSorted=" + radixArrayToBeSorted +
                ", radixStringArrayToBeSorted=" + radixStringArrayToBeSorted +
                ", countingArrayToBeSorted=" + countingArrayToBeSorted +
                ", listNames=" + listNames +
                ", hashTable=" + hashTable +
                ", binarySort=" + binarySort +
                ", heapNames=" + heapNames +
                ", queueNames=" + queueNames +
                ", stackNames=" + stackNames +
                '}';
    }

    public List<String> getHeapNames() {
        return heapNames;
    }

    public void setHeapNames(List<String> heapNames) {
        this.heapNames = heapNames;
    }

    private List< String > heapNames;



    private List< String > queueNames;


    public List<String> getStackNames() {
        return stackNames;
    }

    public void setStackNames(List<String> stackNames) {
        this.stackNames = stackNames;
    }

    private List< String > stackNames;


    public List<String> getRadixStringArrayToBeSorted() {
        return radixStringArrayToBeSorted;

    }

    public String[] getRadixStringArrayToBeSorted2() {
        return radixStringArrayToBeSorted.toArray(new String[0]);

    }

    public void setRadixStringArrayToBeSorted(List<String> radixStringArrayToBeSorted) {
        this.radixStringArrayToBeSorted = radixStringArrayToBeSorted;
    }

    public int getCountingMin() {
        return countingMin;
    }

    public void setCountingMin(int countingMin) {
        this.countingMin = countingMin;
    }

    public int getCountingMax() {
        return countingMax;
    }

    public void setCountingMax(int countingMax) {
        this.countingMax = countingMax;
    }


    public List<Integer> getRadixArrayToBeSorted() {
        return radixArrayToBeSorted;
    }

    public void setRadixArrayToBeSorted(List<Integer> radixArrayToBeSorted) {
        this.radixArrayToBeSorted = radixArrayToBeSorted;
    }

    public int getRadixWidth() {
        return radixWidth;
    }

    public void setRadixWidth(int radixWidth) {
        this.radixWidth = radixWidth;
    }

    public int getRadix() {
        return radix;
    }

    public void setRadix(int radix) {
        this.radix = radix;
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List< Integer > getArrayToBeSorted() {
        return this.arrayToBeSorted;
    }

    public void setArrayToBeSorted(List< Integer > arrayToBeSorted) {
        this.arrayToBeSorted = arrayToBeSorted;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public List< String > getSortNames() {
        return sortNames;
    }

    public void setSortNames(List< String > sortNames) {
        this.sortNames = sortNames;
    }

    public List<Integer> getCountingArrayToBeSorted() {
        return countingArrayToBeSorted;
    }

    public void setCountingArrayToBeSorted(List<Integer> countingArrayToBeSorted) {
        this.countingArrayToBeSorted = countingArrayToBeSorted;
    }


    public int[] getDataIntArray() {
        int[] sortDataIntArray = this.arrayToBeSorted.stream().
                filter(Objects::nonNull).
                mapToInt(Integer::intValue).toArray();
        return sortDataIntArray;
    }

    public int[] getRadixDataIntArray() {
        int[] radixDataIntArray = radixArrayToBeSorted.stream().
                filter(Objects::nonNull).
                mapToInt(Integer::intValue).toArray();
        return radixDataIntArray;
    }

    public int[] getCountingDataIntArray() {
        int[] countingDataIntArray = countingArrayToBeSorted.stream().
                filter(Objects::nonNull).
                mapToInt(Integer::intValue).toArray();
        return countingDataIntArray;
    }

    public List<String> getListNames() {
        return listNames;
    }

    public String getListNamesString() {
        return String.join(",",listNames);
    }

    public String getSortNamesString() {
        return String.join(",",sortNames);
    }

    public String getStackNamesString() {
        return String.join(",",stackNames);
    }

    public String getQueueNamesString() {
        return String.join(",",queueNames);
    }

    public String getBinarySortNamesString() {
        return String.join(",",binarySort);
    }

    public String getHeapNamesString() {
        return String.join(",",heapNames);
    }

    public void setListNames(List<String> listNames) {
        this.listNames = listNames;
    }

}
