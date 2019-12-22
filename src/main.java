import java.io.*;


public class main {


    public static void main(String[] args) throws IOException {
        int n = 0;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Bitte eine natürliche Zahl größer 1 eingeben ");
            n = Integer.parseInt(in.readLine());
        }
        while (n < 2);

        System.out.println("=====Alle Zahlen======");
        int[] a = new int[n - 1]; //array erstellen mit n-1 Werten drinnen wir brauchen n-1 weil wir die 1 ignorieren beim Sieb des Eratosthenes
        for (int i = 2; i <= n; i++) //array füllen von 2 bis n
        {
            a[i - 2] = i;
            System.out.print(a[i - 2] + ", "); //debughilfe

        }
        a = siebDesEratosthenes(a);
        System.out.println("\n");
        System.out.println("========Alle Primzahlen========");
        for (int j : a) {
            System.out.print(j + ", ");
        }
        System.out.println("\n \n");
        findusMultipikationus(n, a);
    }

    private static void findusMultipikationus(int n, int[] a) {
        int[] temp = new int[a.length];                     //gibt Häufigkeit der kleinsten passenden Primzahl an
        for (int prim = 0; prim < a.length; prim++) {
            if (n % a[prim] == 0) {
                int hauefigkeit = n / a[prim];
                temp[prim] = hauefigkeit;
            }
        }                                                   //


        int primPos = 0;
        do {
            if (n % a[primPos] == 0) {                                 // n % kleinste passende Primzahl
                System.out.print(a[primPos] + " * ");                // ausgabe
                n = n / a[primPos];              // n auf Rest setzen
            } else {
                primPos++;                       // springt zu nächsten passenden Primzahl
            }
        } while (n != 1);                         //Solange der Rest nicht 1 ist

    }


    private static int[] siebDesEratosthenes(int[] a) {
        int[] primarray = new int[a.length];
        int i = 0;
        while (a.length > 0) {
            int prim = a[0];
            primarray[i] = prim;
            i++;
            a = deleteMultiple(a, prim);
            a = deleteInArray(prim, a);

        }
        primarray = removeNullsofArray(primarray);
        return primarray;
    }


    private static int[] deleteMultiple(int[] a, int prim) {            //löscht Vielfache von gefundenen Primzahlen

        for (int i = 1; i < a.length; i++) {

            if (a[i] % prim == 0) {
                a = deleteInArray(a[i], a);
                i--;
            }
        }
        return a;
    }

    private static int[] deleteInArray(int i, int[] a) {
        int[] z = new int[a.length - 1];
        int c = 0;
        for (int j = 0; j < z.length + 1; j++) {
            if (a[j] != i) {
                z[c] = a[j];
                c++;
            }
        }
        return z;
    }

    private static int[] removeNullsofArray(int[] a) {
        int countUntilNull = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != 0) {
                countUntilNull++;
            }
        }
        int[] z = new int[countUntilNull];
        for (int i = 0; i < z.length; i++) {
            z[i] = a[i];
        }
        return z;
    }

}
