package com.brmw.contacts.test.jmock;

import java.util.Map;

import org.hamcrest.Description;
import org.jmock.api.Action;
import org.jmock.api.Invocation;

/**
 * This class should capture a parameter passed to a mock object.
 * 
 * See: http://www.jmock.org/custom-actions.html
 */
public class SaveParameterAction<T> implements Action {

    private Integer paramIndex;
    private String key;
    private Map<String, T> savedParameters;
    
    /**
     * This method should be invoked in the Expectation section of the JUnit test.
     * 
     * @param key map key for the actual saved parameter
     * @param savedParameters collection into which parameter will be saved. 
     * @return the Action
     */
    public static <T> Action saveParameter(Integer paramIndex, String key, Map<String, T> savedParameters) {
        return new SaveParameterAction<T>(paramIndex, key, savedParameters);
    }

    /** 
     * Private constructor, only invoked from above static factory method.
     * @param paramIndex TODO
     * @param key map key for the actual saved parameter
     * @param savedParameters the map into which parameter will be saved. 
     */
    private SaveParameterAction(Integer paramIndex, String key, Map<String, T> savedParameters) {
        this.paramIndex = paramIndex;
        this.key = key;
        this.savedParameters = savedParameters;
    }

    public void describeTo(Description description) {
        description.appendText("adds an action listener to the GUI element");
    }

    /**
     * This should be called during the test when the actual call to the mock object is made.
     * This is our one chance to grab the parameter we want to use in further testing.
     */
    @SuppressWarnings("unchecked")
    public Object invoke(Invocation invocation) throws Throwable {
        savedParameters.put(key, (T)invocation.getParameter(paramIndex));
        return null;
    }
}
