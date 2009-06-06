/**
 * 
 */
package com.brmw.contacts.swing;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * This provides most of the required implementation of
 * <code>SwingWorkerPlugin</code>. The concrete class that extends this base
 * class must implement <code>doInBackground()</code> and can also implement
 * <code>done()</code> and/or <code>process(List<V>)</code>
 * 
 * @param <T>
 * @param <V>
 */
public abstract class AbstractSwingWorkerPlugin<T, V> implements SwingWorkerPlugin<T, V> {
    private PlugableSwingWorker<T, V> owner;
    private T initialValue;

    @Override
    public PlugableSwingWorker<T, V> getOwner() {
        return owner;
    }

    @Override
    public void setOwner(PlugableSwingWorker<T, V> owner) {
        this.owner = owner;
    }

    @Override
    public T getInitialValue() {
        return initialValue;
    }

    @Override
    public void setInitialValue(T initialValue) {
        this.initialValue = initialValue;
    }

    @Override
    public void done() {
    }

    @Override
    public void process(List<V> chunks) {
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        return getOwner().get();
    }
}
