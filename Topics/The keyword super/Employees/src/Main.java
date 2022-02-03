
class Employee {

    private String name;
    private String email;
    private int experience;

    public Employee(String name, String email, int experience) {
        this.name = name;
        this.email = email;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }


}

class Developer extends Employee {

    private String mainLanguage;
    private String[] skills;

    public Developer(String name, String email, int experience, String mainLanguage, String[] skills) {
        super(name, email, experience);
        this.mainLanguage = mainLanguage;
        this.skills = skills.clone();
    }


    public String getMainLanguage() {
        return mainLanguage;
    }

    public void setMainLanguage(String mainLanguage) {
        this.mainLanguage = mainLanguage;
    }

    public String[] getSkills() {
        return skills.clone();
    }

    public void setSkills(String[] skills) {
        this.skills = skills.clone();
    }
}

class DataAnalyst extends Employee {

    private boolean phd;
    private String[] methods;

    public DataAnalyst(String name, String email, int experience, boolean phd, String[] methods) {
        super(name, email, experience);
        this.phd = phd;
        this.methods = methods.clone();
    }

    public boolean isPhd() {
        return phd;
    }

    public String[] getMethods() {
        return methods.clone();
    }
}