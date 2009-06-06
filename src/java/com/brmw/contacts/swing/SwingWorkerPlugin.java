/**
 * 
 */
package com.brmw.contacts.swing;

import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class SwingWorkerPlugin<T, V> {
    private PlugableSwingWorker<T, V> owner;
    private T initialValue;

    public PlugableSwingWorker<T, V> getOwner() {
        return owner;
    }

    public void setOwner(PlugableSwingWorker<T, V> owner) {
        this.owner = owner;
    }

    public T getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(T initialValue) {
        this.initialValue = initialValue;
    }

    public abstract T doInBackground() throws Exception;

    public void done() {
    }

    public void process(List<V> chunks) {
    }

    public T get() throws InterruptedException, ExecutionException {
        return getOwner().get();
    }
}
