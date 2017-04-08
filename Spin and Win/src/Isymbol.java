/**
 * Created by Binu Senevirathne on 12/11/2016.
 */
public interface Isymbol {

    //declaring the abstract methods
    public abstract void setImage();
    public abstract String getImage();
    public abstract void setValue(int valueIndex);
    public abstract int getValue();
    public abstract void displayGUI();

    public static void main(String [] args){


        Isymbol sc = new Symbol();
        sc.displayGUI();
    }
}