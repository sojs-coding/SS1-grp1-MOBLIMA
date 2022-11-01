package entity.cinema;

public class LayoutObjectFactory {
    public static LayoutObject getLayoutObject(LayoutItem layoutItem) {
        if (layoutItem == LayoutItem.SINGLE_SEAT) {
            return new LayoutObject(false, 'S', 1);
        } else if (layoutItem == LayoutItem.COUPLE_SEAT) {
            return new LayoutObject(false, 'C', 2);
        }
        return null;
    }
}