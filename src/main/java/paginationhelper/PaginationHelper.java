package paginationhelper;

import java.util.List;

/**
 * A simple pagination class
 * 
 * @param <I>
 *            - the type being paginated
 */
public class PaginationHelper<I> {

    private List<I> collection;
    private int itemsPerPage;

    /**
     * The constructor takes in an array of items and a integer indicating how many
     * items fit within a single page
     */
    public PaginationHelper(List<I> collection, int itemsPerPage) {
        this.collection = collection;
        this.itemsPerPage = itemsPerPage;
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return (this.collection == null ? 0 : this.collection.size());
    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        return this.position(this.collection.size());
    }

    /**
     * Retrieves the page an item index is in the pagination
     * 
     * @param index
     * @return
     */
    private int position(int index) {
        Double div = Math.ceil((double) index / this.itemsPerPage);
        return div.intValue();
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        if (pageIndex < 0) {
            return -1;
        }
        int pageCount = pageCount();
        int lastPage = pageCount - 1;
        if (pageIndex > lastPage) {
            return -1;
        } else if (pageIndex >= 0 && pageIndex < lastPage) {
            return this.itemsPerPage;
        } else { // last page
            int mod = this.collection.size() % this.itemsPerPage;
            return (mod == 0 ? this.itemsPerPage : mod);
        }

    }

    /**
     * determines what page an item is on. Zero based indexes this method should
     * return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        if (this.collection == null || this.collection.size() == 0) {
            return -1;
        }
        if (itemIndex < 0 || itemIndex > this.collection.size()) {
            return -1;
        }
        int position = this.position(itemIndex);
        return (position == 0 ? 0 : position - 1);
    }
}