package com.brmw.contacts.test.swing;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.brmw.contacts.swing.PlugableSwingWorker;
import com.brmw.contacts.swing.SwingWorkerPlugin;

public class SwingWorkerTestPlugin<T, V> extends SwingWorkerPlugin<T, V> {
    SwingWorkerPlugin<T, V> delegate;

    public SwingWorkerTestPlugin(SwingWorkerPlugin<T, V> delegate) {
        this.delegate = delegate;
    }

    /**
     * This method is a synchronized wrapper around the corresponding method of
     * the delegate. The notify() call allows the waitForCompletion() to resume
     * once the real work of <code>done()</code> is done.
     */
    @Override
    public synchronized void done() {
        delegate.done();
        this.notify();
    }

    /**
     * This method should be called at the end of the test. It allows the done()
     * method to complete on the Event Dispatch Thread before the test will
     * complete.
     */
    public synchronized void waitForCompletion() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // All remaining methods are handled by the delegate.

    @Override
    public PlugableSwingWorker<T, V> getOwner() {
        return delegate.getOwner();
    }

    @Override
    public void setOwner(PlugableSwingWorker<T, V> owner) {
        delegate.setOwner(owner);
    }

    @Override
    public T getInitialValue() {
        return delegate.getInitialValue();
    }

    @Override
    public void setInitialValue(T initialValue) {
        delegate.setInitialValue(initialValue);
    }

    @Override
    public T doInBackground() throws Exception {
        return delegate.doInBackground();
    }

    @Override
    public void process(List<V> chunks) {
        delegate.process(chunks);
    }

    @Override
    public T get() throws InterruptedException, ExecutionException {
        return delegate.get();
    }

}
