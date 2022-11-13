package entity.cinema;
/**
 Represents the Layout Object Factory
 It's intended to create the objects based on the LayoutItem Enumerator
 @author Samuel Ong
 @version 1.0
 @since 2022-11-13
 */
public class LayoutObjectFactory {
    /**
     * A method to instantiate LayoutObject
     * @param layoutItem The Layout Item being created
     * @return The LayoutObject if LayoutItem exist else returns Null
     */
    public static LayoutObject getLayoutObject(LayoutItem layoutItem) {
        if (layoutItem == LayoutItem.SINGLE_SEAT) {
            return new LayoutObject(false, 'S', 1);
        } else if (layoutItem == LayoutItem.COUPLE_SEAT) {
            return new LayoutObject(false, 'C', 2);
        }
        return null;
    }
}