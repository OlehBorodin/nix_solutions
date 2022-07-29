package com.garage;

import com.model.Vehicle;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Garage<G extends Vehicle> {

    private Node<G> currentPointer;
    private Node<G> head;
    private Node<G> tail;

    @Data
    private static class Node<G> {
        private G vehicle;
        private Node<G> next;
        private Node<G> previous;
        private String restyling;
        private String time;

        private Node(Node<G> previous, G vehicle, Node<G> next) {
            this.vehicle = vehicle;
            this.next = next;
            this.previous = previous;
            this.restyling = UUID.randomUUID().toString();
            this.time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        }

    }

    public void add(G vehicle) {
        Node<G> newNode = new Node<>(null, vehicle, null);
        newNode.setVehicle(vehicle);

        Node<G> last = getTail();
        if (last == null) {
            head = newNode;
            return;
        }
        last.setNext(newNode);
        newNode.setPrevious(last);
    }


    public void addHead(G vehicle) {
        Node<G> node = new Node<G>(null, vehicle, null);
        node.setVehicle(vehicle);
        node.setPrevious(null);
        if (head != null) {
            node.next = head;
        }
        this.head = node;
    }

    private Node<G> getTail() {
        if (head == null) {
            return null;
        }
        Node<G> current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    public Optional<Vehicle> getRestylingVehicle(String restyling) {
        Node<G> current = this.head;
        while (current != null) {
            if (current.getVehicle().getRestyling().equals(restyling)) {
                return Optional.of(current.getVehicle());
            }
            current = current.next;
        }
        return Optional.empty();
    }

    private void unlinked(Node<G> node) {
        G vehicle = node.vehicle;
        Node<G> prev = node.previous;
        Node<G> next = node.next;
        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            node.previous = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.previous = prev;
            node.next = null;
        }

        node.vehicle = null;

    }

    public boolean deleteRestylingVehicle(String restyling) {
        Optional<Node<G>> current = Optional.of(this.head);
        while (!current.get().getVehicle().getRestyling().equals(restyling)) {
            current = Optional.of(current.get().next);
        }
        for (Node<G> x = head; x != null; x = x.next) {
            if (current.get().vehicle.equals(x.vehicle)) {
                unlinked(x);
                return true;
            }
        }
        return false;
    }

    public Optional<Node<G>> update(G toVehicle, G fromVehicle) {
        Node<G> current = this.head;
        while (current != null && !current.getVehicle().equals(toVehicle)) {
            current = current.next;
        }
        assert current != null;
        if (current.getVehicle() == null) {
            System.out.println("Collection is empty");
        }
        current.setVehicle(fromVehicle);
        return Optional.of(current);

    }

    public int restylingNumber(G vehicle) {
        int sum = 0;
        Node<G> current = this.head;
        while (current != null) {
            if (current.getVehicle().getModel().equals(vehicle.getModel())) {
                sum++;
            }
            current = current.next;
        }
        return sum;
    }

    public DateTimeFormatter getRestylingData() {
        Node<G> current = head;
        if (head == null) {
            return null;
        } else {
            while (true) {
                if (current.next == null) {
                    return current.getVehicle().getTime();
                }
                current = current.next;
            }
        }
    }

    private Node<G> getNext(Node<G> current) {
        return current.getNext();
    }

    public Iterator<G> iterator = new Iterator<>() {

        @Override
        public boolean hasNext() {
            if (head == null) {
                return false;
            }
            if (currentPointer == null) {
                currentPointer = head;
            }
            return currentPointer.next != null;
        }

        @Override
        public G next() {
            if (head == null) {
                return null;
            }
            if (currentPointer == null) {
                currentPointer = head;
            }
            G vehicle = currentPointer.getVehicle();
            currentPointer = currentPointer.next;
            return vehicle;
        }
    };

    public void forEach() {
        while (iterator.hasNext()) {
            System.out.println(currentPointer.vehicle);
            iterator.next();
        }
        if (currentPointer != null) {
            System.out.println(currentPointer.vehicle);
        }
    }

    public void printAll() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node<G> target = head;
        while (target != null) {
            System.out.println(target.getVehicle());
            target = getNext(target);
        }
    }
}
