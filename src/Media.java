import java.awt.*;
import java.io.*;
import java.util.*;
public class Media {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        Libretto libretto = new Libretto();
        File file = new File("source.txt");
        boolean result;
        String input;
        Scanner scan = new Scanner(System.in);
        result = file.createNewFile();
        if (result){
            System.out.println("file created " + file.getCanonicalPath());
        } else {
            System.out.println("File already exist at location: " + file.getCanonicalPath());
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        while((input = br.readLine())!=null) {
            String[] toInsert = input.split("[ ]");
            libretto.addSub(toInsert[0], new Sub(Integer.parseInt(toInsert[1]), Integer.parseInt(toInsert[2]), Boolean.parseBoolean(toInsert[3])));
        }
        clearScreen();
        int choice;
        String benvenutoUser = "Benvenuto!";
        char[] bA = benvenutoUser.toCharArray();
        for (char char_ : bA) {
            try {
                System.out.print(char_);
                Thread.sleep(20);
            } catch (InterruptedException ignored) {
            }
        }
        try {
            Thread.sleep(700);
        } catch (InterruptedException ignored) {
        }
        System.out.println();
        do {
            clearScreen();
            choice = -1;
            System.out.println("1. Aggiungi voto ➕ 2. Modifica voto ✏️\n" +
                    "\t3. Stampa libretto \uD83D\uDDA8️ 4. Proietta voto \uD83D\uDD2E 0. Esci \uD83D\uDEAA\n");
            while (!scan.hasNextInt()) {
                System.out.println("Inserisci un numero valido");
                scan.next();
            }
            choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1: // ADD
                    String[] text;
                    System.out.print("Inserisci MATERIA (Codice) VOTO CFU PASSATA (true/false) separti da uno spazio: ");
                    String in = scan.nextLine();
                    if (in.isBlank()){
                        System.out.println("\nInput non valido");
                        Thread.sleep(2000);
                        break;
                    }
                    text = in.split("[ ]");
                    if (text.length != 4){
                        System.out.println("\nInput non valido");
                        Thread.sleep(2000);
                        break;
                    }
                    libretto.addSub(text[0], new Sub(Integer.parseInt(text[1]), Integer.parseInt(text[2]), Boolean.parseBoolean(text[3])));
                    System.out.print("\033[3mPremi invio per continuare\033[0m");
                    scan.nextLine();
                    break;

            case 2:
                // Edit
                System.out.print("Che materia vuoi modificare? ");
                libretto.printMap();
                String toEdit = scan.nextLine();
                System.out.println("L'hai passata (true/false)? Che voto vuoi inserire?");
                boolean edit = scan.nextBoolean();
                int gradeedit = scan.nextInt();
                scan.nextLine();
                libretto.editSub(toEdit, edit, gradeedit);
                System.out.print("\033[3mPremi invio per continuare\033[0m");
                scan.nextLine();
                break;

            case 3:
                // Print
                clearScreen();
                System.out.println();
                libretto.printMean();
                Thread.sleep(50);
                System.out.println();
                libretto.printMap();
                System.out.print("\n\033[3mPremi invio per continuare\033[0m");
                scan.nextLine();
                break;

            case 4: // Project
                System.out.print("Che voto vuoi proiettare (VOTO CFU)? ");
                String[] query;
                query = scan.nextLine().split("[ ]");
                System.out.format("\nLa vecchia media è: %.2f\n", libretto.getRealMean());
            System.out.format("La nuova media sarebbe: %.2f\n", libretto.projectGrade(Integer.parseInt(query[0]), Integer.parseInt(query[1])));
                System.out.print("\n\033[3mPremi invio per continuare\033[0m");
                scan.nextLine();
                break;
            default:
                if (choice == 0) {
                    //scan.nextLine(); // I clean the buffer here
                    break;
                }
                System.out.println("Inserisci un numero valido");
        }
        }while(choice != 0);
        FileWriter save = new FileWriter(file, false);
        save.write(libretto.mapToText());
        save.close();
    }
}
