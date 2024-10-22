package org.container;

public class ContainerMain {
    public static void main(String[] args) {
        Container<Integer> container = new Container<>();

        container.add(1);
        container.add(2);
        container.add(3);
        container.add(4);

        System.out.println("Container after adding elements:");
        container.printContainer();

        System.out.println("Number of elements in the container: " + container.size());

        System.out.println("Element at index 2: " + container.get(2));

        container.remove(2);

        System.out.println("Container after deleting elements:");
        container.printContainer();

        System.out.println("Number of elements in the container: " + container.size());
    }
}
