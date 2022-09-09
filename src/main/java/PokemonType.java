import java.util.ArrayList;

public class PokemonType {
    private String type;
    private ArrayList<String> effectives;
    private ArrayList<String> superEffectives;
    private ArrayList<String> notEffectives;
    private ArrayList<String> noEffectives;

    // Constructor
    public PokemonType(){
        effectives = new ArrayList<String>();
        superEffectives = new ArrayList<String>();
        notEffectives = new ArrayList<String>();
        noEffectives = new ArrayList<String>();
    }
    // Setters
    public void setType(String t){
        type = t;

    }

    public void setEffectives(String e1){
        effectives.add(e1);
    }

    public void setSuperEffectives(String e1){
        superEffectives.add(e1);
    }

    public void setNotEffectives(String e1){
        notEffectives.add(e1);
    }

    public void setNoEffectives(String e1){
        noEffectives.add(e1);
    }

    // Getters

    public String getType(){
        return type;
    }
    public ArrayList<String> getEffectives(){
        return effectives;
    }

    public ArrayList<String> getSuperEffectives(){
        return superEffectives;
    }

    public ArrayList<String> getNotEffectives(){
        return notEffectives;
    }

    public ArrayList<String> getNoEffectives(){
        return noEffectives;
    }

}
