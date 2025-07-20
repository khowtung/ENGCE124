public class Restaurants {
    RCollection[] tables;
    String[] ids;
    int idCount = 0;
    int nextID = 1; //  ตัวนับเริ่มจาก 1

    public Restaurants() {
        tables = new RCollection[100];
        for (int i = 0; i < 100; i++) {
            tables[i] = new RCollection();
        }
        ids = new String[1000];
    }

    public String generateID() {
        String id = String.format("%03d", nextID);
        nextID++;
        return id;
    }

    public boolean addBooking(Record r) {
        if (!addId(r.id))
            return false;
        tables[r.no].add(r);
        return true;
    }

    private boolean addId(String id) {
        for (int i = 0; i < idCount; i++) {
            if (ids[i].equals(id))
                return false;
        }
        ids[idCount++] = id;
        return true;
    }

    public boolean cancelBooking(String id) {
        for (int i = 0; i < tables.length; i++) {
            if (tables[i].removeById(id)) {
                return true;
            }
        }
        return false;
    }

    public String check(int tableNo) {
        if (tables[tableNo].getCount() > 0) {
            return "Reserved";
            } else {
                return "Available";
        }
    }   
    
    public boolean isDuplicateBooking(int tableNo, String name, String date, String time) {
        return tables[tableNo].containsByNameDateTime(name, date, time);
    }

    public boolean isDoubleBooking(int tableNo, String name, String date, String time) {
        return tables[tableNo].containsByOtherNameDateTime(name, date, time);
    }

}
    
    

