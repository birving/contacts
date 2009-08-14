package com.brmw.contacts.mvp.presenter;

import java.util.concurrent.ExecutionException;

import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brmw.contacts.domain.Person;
import com.brmw.contacts.mvp.model.PersonMaintModel;
import com.brmw.contacts.mvp.view.PersonMaintView;
import com.brmw.contacts.swing.AbstractSwingWorkerPlugin;
import com.brmw.contacts.swing.PlugableSwingWorker;
import com.brmw.contacts.swing.SwingWorkerPlugin;

/**
 * Maintenance functions for Person entries.
 */
public class PersonMaintPresenter {
    private static final Logger logger = LoggerFactory.getLogger(PersonMaintPresenter.class);
    private PersonMaintView personMaintView;
    private PersonMaintModel personMaintModel;
    private SwingWorkerPlugin<Person, Object> workerPlugin;

    public PersonMaintPresenter(PersonMaintView personMaintView, PersonMaintModel personMaintModel) {
        this.personMaintView = personMaintView;
        this.personMaintModel = personMaintModel;
        addListeners();
        workerPlugin = new WorkerPlugin();
    }

    /*
     * Do the work associated with this action. This executes on a worker
     * thread, not on the event dispatch thread.
     */
    private void handlePersonMaintRequest() {
        logger.debug("Calling PersonMaintPresenter.handlePersonMaintRequest()");
        Person person = personMaintView.getSelected();
        workerPlugin.setInitialValue(person);
        SwingWorker<Person, Object> swingWorker = new PlugableSwingWorker<Person, Object>(workerPlugin);
        swingWorker.execute();
    }

    /**
     * Add required listeners for this presenter.
     */
    private void addListeners() {
        // Button to go to Media Maintenance screen
        personMaintView.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel model = (ListSelectionModel) e.getSource();
                logger.info("ListenerSource: " + model.getClass().getName());
                logger.info("Index range: " + e.getFirstIndex() +" : "+ e.getLastIndex());
                logger.info("ValueIsAdjusting: " + e.getValueIsAdjusting());
                int selectedIndex = model.getLeadSelectionIndex();
                logger.info("Selected index: " + selectedIndex);
                handlePersonMaintRequest();
            }
        });
    }

    protected SwingWorkerPlugin<Person, Object> getWorkerPlugin() {
        return workerPlugin;
    }

    protected void setWorkerPlugin(SwingWorkerPlugin<Person, Object> workerPlugin) {
        this.workerPlugin = workerPlugin;
    }

    private class WorkerPlugin extends AbstractSwingWorkerPlugin<Person, Object> {
        @Override
        public Person doInBackground() throws Exception {
            logger.debug("doInBackground() - running in Worker Thread");
            Person person = getInitialValue();
            return personMaintModel.getPerson(person.getId());
        }

        @Override
        public void done() {
            try {
                logger.debug("done() - running in Event Dispatch Thread");
                personMaintView.displayPerson(get());
            } catch (InterruptedException e) {
                // TODO determine if this can every happen in normal operation,
                // then throw custom Exception, or ignore explicitly
                throw new RuntimeException(
                        "THIS SHOULD NOT HAPPEN!?!? PersonMaintView.displayPersons() was interupted!", e);
            } catch (ExecutionException e) {
                // TODO throw custom Exception
                throw new RuntimeException("PersonMaintView.displayPersons() apparently failed!", e);
            }
        }
    }
}
