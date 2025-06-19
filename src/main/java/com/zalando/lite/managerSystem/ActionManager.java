package com.zalando.lite.managerSystem;


import java.util.*;

/**
 * *******************************************************
 * Package: com.zalando.lite
 * File: ActionManager.java
 * Author: Ochwada
 * Date: Friday, 06.Jun.2025, 12:30 PM
 * Description: The {@code ActionManager} class maintains a history of actions performed in the application to allow for
 * * basic undo functionality.
 * Objective:
 * *******************************************************
 */


public class ActionManager {

    /**
     * Stack that stores the history of user actions.
     */
    private final Stack<String> actionHistory;

    /**
     * Constructs a new ActionManager with an empty history stack.
     */
    public ActionManager() {
        actionHistory = new Stack<>();
    }

    /**
     * Records an action by pushing it onto the history stack.
     *
     * @param action the description of the action performed
     */
    public void recordAction(String action) {
        actionHistory.push(action);
        System.out.println("Action recorded: " + action);
    }

    /**
     * Undoes the last recorded action.
     * This method simulates undoing by printing a message.
     * Extend it to include actual rollback logic.
     */
    public void undoLastAction() {
        if (actionHistory.isEmpty()) {
            System.out.println("No actions to undo.");
            return;
        }

        String lastAction = actionHistory.pop();
        System.out.println("Undoing: " + lastAction);

        // Basic undo logic — you can expand this to call actual service methods
        if (lastAction.contains("Product")) {
            System.out.println("NOTE: This demo doesn't really delete the product. Implement actual rollback if needed.");

        } else if (lastAction.contains("Order")) {
            System.out.println("Order undone (simulate actual logic here).");

        } else if (lastAction.contains("Customer")) {
            System.out.println("Customer undo simulated.");

        } else {
            System.out.println("Undo not implemented for: " + lastAction);
        }
    }

    /**
     * Checks if the history stack contains any actions.
     *
     * @return {@code true} if there is at least one recorded action, {@code false} otherwise
     */
    public boolean hasHistory() {
        return !actionHistory.isEmpty();
    }
}
