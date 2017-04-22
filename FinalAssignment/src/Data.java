

public class Data {

    private String name;
    private Integer year;
    private String parentOne;
    private String parentTwo;
    private Character gender;
    
    public Data(String name, char gender, Integer year, String parentOne, String parentTwo) {
        setName(name);
        setGender(gender);
        setYear(year);
        setParentOne(parentOne);
        setParentTwo(parentTwo);
    }

    
    public String getParentOne() {
    
        return parentOne;
    }

    
    public void setParentOne(String parentOne) {
    
        this.parentOne = parentOne;
    }

    
    public String getParentTwo() {
    
        return parentTwo;
    }

    
    public void setParentTwo(String parentTwo) {
    
        this.parentTwo = parentTwo;
    }

    
    public void setGender(Character gender) {
    
        this.gender = gender;
    }

    public String getName() {
    
        return name;
    }

    
    public void setName(String name) {
    
        this.name = name;
    }

    
    public Integer getYear() {
    
        return year;
    }

    
    public void setYear(Integer year) {
    
        this.year = year;
    }

    
    public char getGender() {
    
        return gender;
    }
    
    public void setGender(char gender) {
    
        this.gender = gender;
    }
    
    public String toString() {
        return "Name <" + name + ">\tYear <" + year + ">\tGender <" + gender + ">\tParent <" + parentOne + ">\tParent<" + parentTwo +">\n";
    }
}
