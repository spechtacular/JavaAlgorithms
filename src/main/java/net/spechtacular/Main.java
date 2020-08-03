package net.spechtacular;

import org.apache.commons.cli.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Logger;

public class Main {

    private final static Logger LOGGER =
            Logger.getLogger(Main.class.getName());

    private final static String className = "SortReview";

    public static void main(String[] args) {

        Options options = new Options();
        ParseConfig parseConfig;
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine line;
        String configFileName;

        // command line options
        Option configFile = new Option("c", "config", true, "Optional Config file");
        Option sortString = new Option("s", "sort", true, "Sort Option");
        Option listString = new Option("l", "list", true, "List Option");
        Option stackString = new Option("k", "stack", true, "Stack Option");
        Option queueString = new Option("q", "queue", true, "Queue Option");
        Option heapString = new Option("h", "heap", true, "Heap Option");
        Option bstString = new Option("b", "bst", true, "Binary Sort Option");


        options.addOption(heapString);
        options.addOption(sortString);
        options.addOption(bstString);
        options.addOption(listString);
        options.addOption(stackString);
        options.addOption(queueString);
        options.addOption(configFile);

        try {
            line = parser.parse(options, args);

            if (line.hasOption("config")) {
                configFileName = line.getOptionValue("config");
                parseConfig = new ParseConfig(LOGGER, configFileName);
            } else {
                parseConfig = new ParseConfig(LOGGER);
            }

            ConfigData dataConfig = parseConfig.getConfig();

            if (line.hasOption("sort")) {
                //sortOption = line.getOptionValue("sort").toLowerCase();
                //LOGGER.info("sort="+sortOption);
                processSorts(line.getOptionValue("sort").toLowerCase(), dataConfig);
            }

            if (line.hasOption("list")) {
                processLists(line.getOptionValue("list").toLowerCase(), dataConfig);
            }

            if (line.hasOption("stack")) {
                processStacks(line.getOptionValue("stack").toLowerCase(), dataConfig);
            }

            if (line.hasOption("queue")) {
                processQueues(line.getOptionValue("queue").toLowerCase(), dataConfig);
            }

            if (line.hasOption("bst")) {
                processBinarySortTree(line.getOptionValue("bst").toLowerCase(), dataConfig);
            }

            if (line.hasOption("heap")) {
                processHeap(line.getOptionValue("heap").toLowerCase(), dataConfig);
            }


        } catch (ParseException e) {
            LOGGER.severe(e.getMessage());
            formatter.printHelp(className, options);
        }

    }

    static void processSorts(String sortOption, @NotNull ConfigData dataConfig) {

        if (!dataConfig.getSortNames().contains(sortOption)) {
            LOGGER.info(className + " sort option " + sortOption + " does not exist, sortNames=" + dataConfig.getSortNamesString());
        } else {
            SortAlgorithms sortAlgorithms = new SortAlgorithms(LOGGER);

            // convert sort and radix data lists in the config file to int[]
            int[] sortData = dataConfig.getDataIntArray();
            int[] radixDataArray = dataConfig.getRadixDataIntArray();
            int[] countingDataArray = dataConfig.getCountingDataIntArray();

            // sort the data based on sort option
            switch (sortOption) {

                case "bubble":
                    sortAlgorithms.bubbleSort(sortData);
                    break;

                case "selection":
                    sortAlgorithms.selectionSort(sortData);
                    break;

                case "shell":
                    sortAlgorithms.shellSort(sortData);
                    break;

                case "insertion":
                    sortAlgorithms.insertionSort(sortData);
                    break;

                case "isr":
                    sortAlgorithms.insertionSortRecursive(sortData, sortData.length);
                    break;

                case "merge":
                    sortAlgorithms.mergeSort(sortData, 0, sortData.length);
                    break;

                case "quick":
                    sortAlgorithms.quickSort(sortData, 0, sortData.length);
                    break;

                case "counting":
                    sortAlgorithms.countingSort(countingDataArray, dataConfig.getCountingMin(), dataConfig.getCountingMax());
                    break;

                case "radix":
                    sortAlgorithms.radixSort(radixDataArray, dataConfig.getRadix(), dataConfig.getRadixWidth());
                    break;

                case "radixstring":
                    sortAlgorithms.radixSortString(dataConfig.getRadixStringArrayToBeSorted2(), dataConfig.getRadix(), dataConfig.getRadixWidth());
                    break;

            }
        }
    }

    static void processLists(String listOption, @NotNull ConfigData dataConfig) {

        List<String> listMethods = dataConfig.getListNames();
        if (!listMethods.contains(listOption)) {
            LOGGER.info(className + " list option " + listOption + " does not exist, listNames=" + dataConfig.getListNamesString());
        } else {
            LOGGER.info("processLists="+listOption);
            Employee janeJones = new Employee("Jane", "Jones", 123);
            Employee johnDoe = new Employee("John", "Doe", 4567);
            Employee marySmith = new Employee("Mary", "Smith", 22);
            Employee mikeWilson = new Employee("Mike", "Wilson", 3245);

            switch (listOption) {

                case "linked":
                    EmployeeLinkedList ell = new EmployeeLinkedList();
                    ell.addToFront(janeJones);
                    ell.addToFront(johnDoe);
                    ell.addToFront(marySmith);
                    ell.addToFront(mikeWilson);

                    LOGGER.info(String.valueOf(ell.getSize()));

                    ell.printList();

                    ell.removeFromFront();
                    LOGGER.info(String.valueOf(ell.getSize()));
                    ell.printList();

                    break;

                case "double":
                    EmployeeDoublyLinkedList list = new EmployeeDoublyLinkedList();

                    list.addToFront(janeJones);
                    list.addToFront(johnDoe);
                    list.addToFront(marySmith);
                    list.addToFront(mikeWilson);

                    list.printList();
                    LOGGER.info(String.valueOf(list.getSize()));

                    Employee billEnd = new Employee("Bill", "End", 78);
                    list.addToEnd(billEnd);
                    list.printList();
                    LOGGER.info(String.valueOf(list.getSize()));
                    list.removeFromFront();
                    list.printList();
                    LOGGER.info(String.valueOf(list.getSize()));
                    list.removeFromEnd();
                    list.printList();
                    LOGGER.info(String.valueOf(list.getSize()));

                    break;

                case "integer":
                    Integer one = 1;
                    Integer two = 2;
                    Integer three = 3;
                    Integer four = 4;
                    IntegerLinkedList ilist = new IntegerLinkedList();
                    ilist.insertSorted(three);
                    ilist.printList();
                    ilist.insertSorted(two);
                    ilist.printList();
                    ilist.insertSorted(one);
                    ilist.printList();
                    ilist.insertSorted(four);
                    ilist.printList();

                    break;
            }
        }
    }

    static void processStacks(String stackOption, @NotNull ConfigData dataConfig) {

        List<String> stackMethods = dataConfig.getStackNames();
        if (!stackMethods.contains(stackOption)) {
            LOGGER.info(className + " stack option " + stackOption + " does not exist, stackNames=" + dataConfig.getStackNamesString());
        } else {
            LOGGER.info("processStacks=" + stackOption);
            switch (stackOption) {

                case "array":
                    ArrayStack stack = new ArrayStack(10);

                    stack.push(new Employee("Jane", "Jones", 123));
                    stack.push(new Employee("John", "Doe", 4567));
                    stack.push(new Employee("Mary", "Smith", 22));
                    stack.push(new Employee("Mike", "Wilson", 3245));
                    stack.push(new Employee("Bill", "End", 78));
                    LOGGER.info(String.valueOf(stack.peek()));
                    //stack.printStack();

                    LOGGER.info("Popped: " + stack.pop());
                    LOGGER.info(String.valueOf(stack.peek()));
                    break;

                case "linklist":
                    Employee janeJones = new Employee("Jane", "Jones", 123);
                    Employee johnDoe = new Employee("John", "Doe", 4567);
                    Employee marySmith = new Employee("Mary", "Smith", 22);
                    Employee mikeWilson = new Employee("Mike", "Wilson", 3245);
                    Employee billEnd = new Employee("Bill", "End", 78);

                    LinkedStack linkedStack = new LinkedStack();
                    linkedStack.push(janeJones);
                    linkedStack.push(johnDoe);
                    linkedStack.push(marySmith);
                    linkedStack.push(mikeWilson);
                    linkedStack.push(billEnd);

                    //linkedStack.printStack();

                    //System.out.println(linkedStack.peek());
                    //linkedStack.printStack();

                    System.out.println("Popped: " + linkedStack.pop());
                    System.out.println(linkedStack.peek());

                    break;
            }

        }
    }

    static void processQueues(String queueOption, @NotNull ConfigData dataConfig) {

        List<String> queueMethods = dataConfig.getQueueNames();
        if (!queueMethods.contains(queueOption)) {
            LOGGER.info(className + " queue option " + queueOption + " does not exist, queueNames=" + dataConfig.getQueueNamesString());
        } else {
            LOGGER.info("processQueues=" + queueOption);

            Employee janeJones = new Employee("Jane", "Jones", 123);
            Employee johnDoe = new Employee("John", "Doe", 4567);
            Employee marySmith = new Employee("Mary", "Smith", 22);
            Employee mikeWilson = new Employee("Mike", "Wilson", 3245);
            Employee billEnd = new Employee("Bill", "End", 78);

            switch (queueOption) {

                case "queue":

                    ArrayQueue queue = new ArrayQueue(5);

                    queue.add(janeJones);
                    queue.add(johnDoe);
                    queue.remove();
                    queue.add(marySmith);
                    queue.remove();
                    queue.add(mikeWilson);
                    queue.remove();
                    queue.add(billEnd);
                    queue.remove();
                    queue.add(janeJones);

                    queue.printQueue();

                    break;

                case "queue2":
                    ArrayQueue2 queue2 = new ArrayQueue2(5);

                    queue2.add(janeJones);
                    queue2.add(johnDoe);
                    queue2.remove();
                    queue2.add(marySmith);
                    queue2.remove();
                    queue2.add(mikeWilson);
                    queue2.remove();
                    queue2.add(billEnd);
                    queue2.remove();
                    queue2.add(janeJones);

                    queue2.printQueue();
                    break;

                case "queue-assignment1":
                    // should return true
                    QueueAssignment qa = new QueueAssignment(LOGGER);
                    qa.checkForPalindrome("abccba");
                    // should return true
                    qa.checkForPalindrome("Was it a car or a cat I saw?");
                    // should return true
                    qa.checkForPalindrome("I did, did I?");
                    // should return false
                    qa.checkForPalindrome("hello");
                    // should return true
                    qa.checkForPalindrome("Don't nod");

            }

        }
    }

    static void processBinarySortTree(String bstOption, @NotNull ConfigData dataConfig) {

        List<String> bstMethods = dataConfig.getBinarySort();
        if (!bstMethods.contains(bstOption)) {
            LOGGER.info(className + " BinarySortTree option " + bstOption + " does not exist, bstNames=" + dataConfig.getBinarySortNamesString());
        } else {
            LOGGER.info("processQueues=" + bstOption);

            Tree intTree = new Tree();
            intTree.insert(25);
            intTree.insert(20);
            intTree.insert(15);
            intTree.insert(27);
            intTree.insert(30);
            intTree.insert(29);
            intTree.insert(26);
            intTree.insert(22);
            intTree.insert(32);
            intTree.insert(17);

            intTree.traverseInOrder();
            System.out.println();

            switch (bstOption) {

                case "bst":

                    // bst delete 3 cases

//
//        System.out.println(intTree.get(27));
//        System.out.println(intTree.get(17));
//        System.out.println(intTree.get(8888));

//        System.out.println(intTree.min());
//        System.out.println(intTree.max());

                    intTree.delete(8888);
                    intTree.traverseInOrder();
                    System.out.println();

                case "bst1":
                    // add preorder traversal
                    intTree.traversePreOrder();
                    System.out.println();

            }

        }

    }

    static void processHeap(String heapOption, @NotNull ConfigData dataConfig) {

        List<String> heapMethods = dataConfig.getHeapNames();
        if (!heapMethods.contains(heapOption)) {
            LOGGER.info(className + " Heap option " + heapOption + " does not exist, bstNames=" + dataConfig.getHeapNamesString());
        } else {
            LOGGER.info("processHeap=" + heapOption);
            switch (heapOption) {

                case "heap":

                    Heap heap = new Heap(10);

                    heap.insert(80);
                    heap.insert(75);
                    heap.insert(60);
                    heap.insert(68);
                    heap.insert(55);
                    heap.insert(40);
                    heap.insert(52);
                    heap.insert(67);

                    heap.printHeap();

                    //System.out.println(heap.peek());

                    heap.delete(0);
                    heap.printHeap();

                    //System.out.println(heap.peek());

                    heap.sort();
                    heap.printHeap();

                case "pq":
                    PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

                    pq.add(25);
                    pq.add(-22);
                    pq.add(1343);
                    pq.add(54);
                    pq.add(0);
                    pq.add(-3492);
                    pq.add(429);

            //        System.out.println(pq.peek());
            //        System.out.println(pq.remove());
            //        System.out.println(pq.peek());
            //        System.out.println(pq.poll());
            //        System.out.println(pq.peek());
                    System.out.println(pq.remove(54));

                    Object[] ints = pq.toArray();
                    for (Object num: ints) {
                        System.out.println(num);
                    }

                    //System.out.println(pq.peek());
                    pq.add(-1);
                    //System.out.println(pq.peek());


            }

        }

    }

}
