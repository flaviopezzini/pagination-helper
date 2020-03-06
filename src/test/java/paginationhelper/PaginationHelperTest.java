package challenges.paginationhelper;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import paginationhelper.PaginationHelper;

public class PaginationHelperTest {

    private PaginationHelper<Character> helper1 = new PaginationHelper<>(
            Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'), 4);
    PaginationHelper<Character> helper2 = new PaginationHelper<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f'), 2);
    
    @Test
    public void testPageCount() {
        assertEquals(2, helper1.pageCount()); // should == 2
        assertEquals(3, helper2.pageCount());
    }

    @Test
    public void testItemCount() {
        assertEquals(6, helper1.itemCount()); // should == 6
        assertEquals(6, helper2.itemCount());
    }

    @Test
    public void testPageItemCountOther() {
        assertEquals(4, helper1.pageItemCount(0)); // should == 4
        assertEquals(2, helper2.pageItemCount(0));
        assertEquals(2, helper1.pageItemCount(1)); // last page - should == 2
        assertEquals(2, helper2.pageItemCount(1));
        assertEquals(2, helper2.pageItemCount(2));
        assertEquals(-1, helper1.pageItemCount(2)); // should == -1 since the page is invalid
        assertEquals(2, helper2.pageItemCount(2));
    }

    @Test
    public void testPageIndex() {
        // pageIndex takes an item index and returns the page that it belongs on
        assertEquals(0, helper1.pageIndex(4)); // should == 1 (zero based index)
        assertEquals(1, helper1.pageIndex(5)); // should == 1 (zero based index)
        assertEquals(2, helper2.pageIndex(5));
        assertEquals(0, helper1.pageIndex(2)); // should == 0
        assertEquals(0, helper2.pageIndex(2));
        assertEquals(0, helper2.pageIndex(0));
    }

    @Test
    public void testPageIndexInvalid() {
        assertEquals(-1, helper1.pageIndex(20)); // should == -1
        assertEquals(-1, helper2.pageIndex(20));
        assertEquals(-1, helper1.pageIndex(-10)); // should == -1
        assertEquals(-1, helper2.pageIndex(-10));
    }
}