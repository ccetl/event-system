package ccetl.event;

import ccetl.event.listeners.Listener;

import java.lang.reflect.Method;

public interface IEventSystem {
    /**
     * This will execute the listeners synchronously.
     *
     * @param event the event to post
     * @return true if the event got canceled
     */
    boolean post(Object event);

    /**
     * @param event        the event to post
     * @param asynchronous Determines whether the listeners get executed synchronous (false) or asynchronous (true);
     *                     asynchronously ignores the priority and executes the invoker on multiple threads, so
     *                     your event and listener for asynchronous execution have to be thread save.
     * @param await        Waits until all listeners have been executed before continuing with the thread calling this
     *                     method. This doesn't matter if you run the execution synchronous since you already run the
     *                     listeners on the calling thread this way. It should always be true when you need to know the
     *                     cancellation status.
     * @return the cancellation status
     */
    boolean post(Object event, boolean asynchronous, boolean await);

    /**
     * This method will register all annotated listeners from the given objects.
     *
     * @param objects target objects
     */
    void register(Object... objects);

    /**
     * This method will register all annotated listeners from the given object.
     *
     * @param object target object
     */
    void register(Object object);

    /**
     * @param method   target method
     * @param provider target object
     * @return true when the method was successfully registered
     */
    boolean register(Method method, Object provider);

    /**
     * @param listener target listener
     */
    void register(Listener<?> listener);

    /**
     * This method will deregister all annotated listeners from the given objects.
     *
     * @param objects target object
     */
    void deregister(Object... objects);

    /**
     * This method will deregister all annotated listeners from the given object.
     *
     * @param object target object
     */
    void deregister(Object object);

    /**
     * @param method   target method
     * @param provider target object
     * @return true if the method was present and got removed
     */
    @SuppressWarnings("UnusedReturnValue")
    boolean deregister(Method method, Object provider);

    /**
     * @param listener target listener
     * @return true if the method was present and got removed
     */
    @SuppressWarnings("UnusedReturnValue")
    boolean deregister(Listener<?> listener);

    /**
     * @param eventClass what event the listeners have to listen to
     * @return true when the event system has registered listeners for the event
     */
    boolean hasListeners(Class<?> eventClass);
}
