public class Sub {
    private int grade;
    private final int cfu;
    boolean passed;

    public Sub(int grade, int cfu, boolean passed) {
        this.cfu = cfu;
        this.passed = passed;
        this.grade = grade;
    }

    public int getCfu() {
        return cfu;
    }

    public boolean isPassed() {
        return passed;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
    public void edit(int grade, boolean passed){
        setGrade(grade);
        setPassed(passed);
    }
}
