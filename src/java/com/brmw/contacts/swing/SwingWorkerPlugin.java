package com.brmw.contacts.swing;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface SwingWorkerPlugin<T, V> {
    public PlugableSwingWorker<T, V> getOwner();

    public void setOwner(PlugableSwingWorker<T, V> owner);

    public T getInitialValue();

    public void setInitialValue(T initialValue);

    public T doInBackground() throws Exception;

    public void done();

    public void process(List<V> chunks);

    public T get() throws InterruptedException, ExecutionException;

}
