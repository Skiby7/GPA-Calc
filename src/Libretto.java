import java.util.*;
public class Libretto {
    private final LinkedHashMap<String, Sub> libretto;

    public Libretto(){
        this.libretto = new LinkedHashMap<>();
    }


    public void addSub(String sub, Sub data){
        this.libretto.put(sub, data);
    }

    public void editSub(String sub, boolean passed, int grade){
        this.libretto.get(sub).edit(grade, passed);
    }

    public float getRealMean(){
        float counterReal = 0;
        float sumReal = 0;

        for(Sub sub: this.libretto.values()){
            if(sub.isPassed()){
                sumReal += sub.getCfu()*sub.getGrade();
                counterReal += sub.getCfu();
            }
        }
        if(counterReal == 0)
            return 0;
        return sumReal/counterReal;
    }

    public float getProjectMean(){
        float counterProject = 0;
        float sumProject = 0;

        for(Sub sub: this.libretto.values()){
                sumProject += sub.getCfu()*sub.getGrade();
                counterProject += sub.getCfu();

        }
        if(counterProject == 0)
            return 0;
        return sumProject/counterProject;
    }


    public float projectGrade(int grade, int cfu){
        float counterReal = 0;
        float sumReal = 0;

        for(Sub sub: this.libretto.values()){
            if(sub.isPassed()){
                sumReal += sub.getCfu()*sub.getGrade();
                counterReal += sub.getCfu();
            }
        }
        sumReal += grade*cfu;
        counterReal += cfu;
        return sumReal/counterReal;
    }
    public void printMean(){

        System.out.format("La tua media è: %.2f\n", getRealMean());
        System.out.format("La tua proiezione, invece è %.2f\n", getProjectMean());
    }

    public void printProject(int grade, int cfu){
        System.out.format("La tua nuova media sarebbe: %.2f", projectGrade(grade, cfu));
    }
    public void printMap(){
        System.out.format("%-12s\t%8s\t\t%8s\t\t%13s\n", "\uD83D\uDCD3 Materia", "\uD83E\uDDEE Voto", "\uD83C\uDF93 CFU", "✔ ️Passata");
        for (Map.Entry<String, Sub> entry : this.libretto.entrySet()) {
            System.out.format("%-12s\t%8s\t\t%8s\t\t%12s\n", entry.getKey(), this.libretto.get(entry.getKey()).getGrade(), this.libretto.get(entry.getKey()).getCfu(), this.libretto.get(entry.getKey()).isPassed());
            try {
                Thread.sleep(50);
            } catch (InterruptedException ignored) {

            }
        }
    }

    public String mapToText(){
        StringBuilder text = new StringBuilder();
        for (Map.Entry<String, Sub> entry : this.libretto.entrySet()) {
            text.append(entry.getKey() + " ");
            text.append(this.libretto.get(entry.getKey()).getGrade() + " ");
            text.append(this.libretto.get(entry.getKey()).getCfu() + " ");
            text.append(this.libretto.get(entry.getKey()).isPassed() + "\n");
        }
        return text.toString();
    }


}
