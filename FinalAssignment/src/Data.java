

public class Data {

    private String name, year;
    private char gender;
    
    public Data(String name, String year, char gender) {
        this.name = name;
        this.year = year;
        this.gender = gender;
    }

    public String getName() {
    
        return name;
    }

    
    public void setName(String name) {
    
        this.name = name;
    }

    
    public String getYear() {
    
        return year;
    }

    
    public void setYear(String year) {
    
        this.year = year;
    }

    
    public char getGender() {
    
        return gender;
    }
    
    public void setGender(char gender) {
    
        this.gender = gender;
    }
    
    public String toString() {
        return "Name <" + name + ">\tYear <" + year + ">\tGender <" + gender + ">";
    }
}
