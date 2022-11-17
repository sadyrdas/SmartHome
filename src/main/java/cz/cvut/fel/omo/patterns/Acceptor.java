package cz.cvut.fel.omo.patterns;

public interface Acceptor<T> {
    public void accept(T visitor);
}
