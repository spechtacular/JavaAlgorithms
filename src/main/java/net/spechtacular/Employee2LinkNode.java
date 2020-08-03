package net.spechtacular;

public class Employee2LinkNode {

    private Employee employee;
    private Employee2LinkNode next;
    private Employee2LinkNode previous;

    public Employee2LinkNode(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee2LinkNode getNext() {
        return next;
    }

    public void setNext(Employee2LinkNode next) {
        this.next = next;
    }

    public Employee2LinkNode getPrevious() {
        return previous;
    }

    public void setPrevious(Employee2LinkNode previous) {
        this.previous = previous;
    }

    public String toString() {
        return employee.toString();
    }

}
