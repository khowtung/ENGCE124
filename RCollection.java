public class RCollection {
    Record[] arr;
    int count;

    public RCollection() {
        arr = new Record[100];
        count = 0;
    }

    public void add(Record r) {
        arr[count++] = r;
    }

    public boolean removeById(String id) {
        for (int i = 0; i < count; i++) {
            if (arr[i].id.equals(id)) {
                arr[i] = arr[count - 1];
                count--;
                return true;
            }
        }
        return false;
    }

    // เช็คว่า มี record ที่ ชื่อเดียวกัน และ วันที่ เวลา เดียวกัน อยู่แล้ว
    public boolean containsByNameDateTime(String name, String date, String time) {
        for (int i = 0; i < count; i++) {
            if (arr[i].name.equals(name) && arr[i].date.equals(date) && arr[i].time.equals(time))
                return true;
        }
        return false;
    }

    // เช็คว่า มีคนอื่น (ไม่ใช่ name นี้) จองในวันที่และเวลานี้แล้ว
    public boolean containsByOtherNameDateTime(String name, String date, String time) {
        for (int i = 0; i < count; i++) {
            if (!arr[i].name.equals(name) && arr[i].date.equals(date) && arr[i].time.equals(time))
                return true;
        }
        return false;
    }

    public int getCount() {
        return count;
    }
}
