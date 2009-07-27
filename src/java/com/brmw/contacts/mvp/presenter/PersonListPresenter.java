package com.brmw.contacts.mvp.presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Person;
import com.brmw.contacts.mvp.model.PersonListModel;
import com.brmw.contacts.mvp.view.PersonListView;
import com.brmw.contacts.swing.AbstractSwingWorkerPlugin;
import com.brmw.contacts.swing.PlugableSwingWorker;
import com.brmw.contacts.swing.SwingWorkerPlugin;

/**
 * List matching (or all) Person entries.
 */
public class PersonListPresenter {
    private static final Logger logger = LoggerFactory.getLogger(PersonListPresenter.class);
    private PersonListView personListView;
    private PersonListModel personListModel;
    private SwingWorkerPlugin<Collection<Person>, Object> workerPlugin;

    public PersonListPresenter(PersonListView personListView, PersonListModel personListModel) {
        this.personListView = personListView;
        this.personListModel = personListModel;
        addListeners();
        workerPlugin = new WorkerPlugin();
    }

    /*
     * Do the work associated with this action. This executes on a worker
     * thread, not on the event dispatch thread.
     */
    private void handlePersonListRequest() {
        logger.debug("Calling PersonListPresenter.handlePersonListRequest()");
        SwingWorker<Collection<Person>, Object> swingWorker =
                new PlugableSwingWorker<Collection<Person>, Object>(workerPlugin);
        swingWorker.execute();
    }

    /**
     * Add required listeners for this presenter.
     */
    private void addListeners() {
        // Button to go to Person List screen
        personListView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handlePersonListRequest();
            }
        });
    }

    protected SwingWorkerPlugin<Collection<Person>, Object> getWorkerPlugin() {
        return workerPlugin;
    }

    protected void setWorkerPlugin(SwingWorkerPlugin<Collection<Person>, Object> workerPlugin) {
        this.workerPlugin = workerPlugin;
    }

    private class WorkerPlugin extends AbstractSwingWorkerPlugin<Collection<Person>, Object> {
        @Override
        public Collection<Person> doInBackground() throws Exception {
            logger.debug("doInBackground() - running in Worker Thread");
            return personListModel.getAllPersons();
        }

        @Override
        public void done() {
            try {
                logger.debug("done() - running in Event Dispatch Thread");
                personListView.displayPersons(get());
            } catch (InterruptedException e) {
                // TODO determine if this can every happen in normal operation, then throw custom Exception, or ignore explicitly
                throw new RuntimeException("THIS SHOULD NOT HAPPEN!?!? PersonListView.displayPersons() was interupted!", e);
            } catch (ExecutionException e) {
                // TODO throw custom Exception 
                throw new RuntimeException("PersonListView.displayPersons() apparently failed!", e);
            }
        }
    }
}
