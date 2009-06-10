package com.brmw.contacts.swing;

import java.util.List;

import javax.swing.SwingWorker;

/**
 * This is an implementation of SwingWorker with the primary interface methods
 * delegating to a separate abstract class: <code>SwingWorkerPlugin</code>. This
 * should allow better testability.
 * 
 * @param <T>
 * @param <V>
 */
public class PlugableSwingWorker<T, V> extends SwingWorker<T, V> {
    private SwingWorkerPlugin<T, V> plugin;

    public PlugableSwingWorker(SwingWorkerPlugin<T, V> plugin) {
        this.plugin = plugin;
        plugin.setOwner(this);
    }

    @Override
    protected T doInBackground() throws Exception {
        return plugin.doInBackground();
    }

    @Override
    protected void done() {
        plugin.done();
    }

    @Override
    protected void process(List<V> chunks) {
        plugin.process(chunks);
    }
}
