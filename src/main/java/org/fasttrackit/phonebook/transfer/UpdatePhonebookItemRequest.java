package org.fasttrackit.phonebook.transfer;

public class UpdatePhonebookItemRequest {

    private boolean done;

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "UpdatePhonebookItemRequest{" +
                "done=" + done +
                '}';
    }
}
