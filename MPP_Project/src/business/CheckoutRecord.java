package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

final public class CheckoutRecord implements Serializable {
    private static final long serialVersionUID = 7138181845152631487L;
    private List<CheckoutRecordEntry> entries;

    CheckoutRecord() {
        this.entries = new ArrayList<CheckoutRecordEntry>();
    }

    public List<CheckoutRecordEntry> getEntries() {
        return entries;
    }

    public void addEntry(CheckoutRecordEntry entry) {
        this.entries.add(entry);
        entry.setCheckoutRecord(this);
    }
}
