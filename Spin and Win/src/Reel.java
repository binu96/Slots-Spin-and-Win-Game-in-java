import java.util.ArrayList;
import java.util.List;

/**
 * Created by Binu Senevirathne on 12/12/2016.
 */
public class Reel {

    //creating the 6 Symbol objects to store the images and the respective values
    List<Symbol> Spin() {
        List<Symbol> list = new ArrayList<Symbol>();

        Symbol img01 = new Symbol("Seven", 7, "C:/Users/Binu Senevirathne/IdeaProjects/Spin and Win/images/redseven.png");
        Symbol img02 = new Symbol("Bell", 6, "C:/Users/Binu Senevirathne/IdeaProjects/Spin and Win/images/bell.png");
        Symbol img03 = new Symbol("Watermelon", 5, "C:/Users/Binu Senevirathne/IdeaProjects/Spin and Win/images/watermelon.png");
        Symbol img04 = new Symbol("Plum", 4, "C:/Users/Binu Senevirathne/IdeaProjects/Spin and Win/images/plum.png");
        Symbol img05 = new Symbol("Lemon", 3, "C:/Users/Binu Senevirathne/IdeaProjects/Spin and Win/images/lemon.png");
        Symbol img06 = new Symbol("Cherry", 2, "C:/Users/Binu Senevirathne/IdeaProjects/Spin and Win/images/cherry.png");

        list.add(img01);
        list.add(img02);
        list.add(img03);
        list.add(img04);
        list.add(img05);
        list.add(img06);

        return list;
    }
}