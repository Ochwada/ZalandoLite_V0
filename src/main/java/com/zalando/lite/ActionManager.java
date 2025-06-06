package com.zalando.lite;


import java.util.*;

/**
 * *******************************************************
 * Package: com.zalando.lite
 * File: ActionManager.java
 * Author: Ochwada
 * Date: Friday, 06.Jun.2025, 12:30 PM
 * Description:
 * Objective:
 * *******************************************************
 */


public class ActionManager {
    private Stack<String> actionHistory;

    public ActionManager() {
        actionHistory = new Stack<>();
    }


    public void recordAction(String action) {
        actionHistory.push(action);
        System.out.println("Action recorded: " + action);
    }

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

    public boolean hasHistory() {
        return !actionHistory.isEmpty();
    }
}
