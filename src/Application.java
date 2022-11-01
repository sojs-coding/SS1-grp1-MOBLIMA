import entity.cinema.*;

public class Application {
    public static void main(String[] args) {
        System.out.printf("Welcome to my moviplex!\n");
        Cineplex gvJurong = new Cineplex();
        Layout layout = new Layout(5,5);
        layout.setObject(0,0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0,1, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(0,4, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1,0, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(1,2, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(1,3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(2,0, LayoutObjectFactory.getLayoutObject(LayoutItem.SINGLE_SEAT));
        layout.setObject(2,1, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));
        layout.setObject(2,3, LayoutObjectFactory.getLayoutObject(LayoutItem.COUPLE_SEAT));

        Cinema cinema = new Cinema("JR1".toCharArray(), layout);
        gvJurong.addCinema(cinema);

        Layout layout1 = cinema.cloneCinemaLayout();
        layout1.occupy(0, 1);
        layout1.occupy(2,1);

        layout.displayLayout();
        layout1.displayLayout();

        layout = new Layout(5,5);
        cinema = new Cinema("JR2".toCharArray(), layout);
        gvJurong.addCinema(cinema);
    }
}