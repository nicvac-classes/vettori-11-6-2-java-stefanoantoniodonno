//LEGGERE LE ISTRUZIONI NEL FILE README.md

//Import di Classi Java necessarie al funzionamento del programma
import java.util.Scanner;

// Classe principale, con metodo main
class Esercizio {
import java.util.*;
import java.lang.Math;
import java.io.FileWriter;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int c;
        int nv, np, i;
        String e;

        // Inizialmente non ci sono libri in biblioteca.
        nv = 0;
        np = 0;

        // Alloco più spazio rispetto alla dimensione effettiva perchè sul vettore saranno effettuate operazioni di inserimento e cancellazione. Supponiamo di avere massimo 100 libri in biblioteca.
        String[] v = new String[100];
        String[] p = new String[100];

        // Inserisco dei libri di partenza
        nv = inizializzaVettoreLibri(v, nv);
        do {
            c = leggiComando();
            if (c == 1) {
                System.out.println("Nuovo libro da inserire in biblioteca:");
                e = input.nextLine();
                nv = inserisciInVettore(v, nv, e, 0);
            }
            if (c == 2) {
                if (nv > 0) {
                    visualizzaVettore(v, nv);
                } else {
                    System.out.println("Non ci sono libri in biblioteca");
                }
            }
            if (c == 3) {
                if (np > 0) {
                    visualizzaVettore(p, np);
                } else {
                    System.out.println("Non ci sono libri in prestito");
                }
            }
            if (c == 4) {
                visualizzaVettore(v, nv);
                System.out.println("Titolo del libro (o una sua parola) da prendere in prestito: ");
                e = input.nextLine();
                i = ricercaNelVettore(v, nv, e);
                if (i >= 0) {
                    System.out.println("Libro scelto: " + (char) 10 + i + ":" + v[i]);
                    np = inserisciInVettore(p, np, v[i], 0);
                    nv = eliminaDaVettore(v, nv, i);
                } else {
                    System.out.println("Libro " + e + " non trovato.");
                }
            }
            if (c == 5) {
                if (np > 0) {
                    visualizzaVettore(p, np);
                    do {
                        System.out.println("Indice del libro da restituire: ");
                        i = input.nextInt();
                    } while (i >= np);
                    System.out.println("Libro restituito: " + (char) 10 + i + ":" + p[i]);
                    nv = inserisciInVettore(v, nv, p[i], 0);
                    np = eliminaDaVettore(p, np, i);
                } else {
                    System.out.println("Non ci sono libri in prestito");
                }
            }
        } while (c != 6);
        salvalibro(v, nv, "nomefile.txt");
        salvaprestiti(p, np, "nomefile.txt");
    }
    
    public static int eliminaDaVettore(String[] v, int n, int ie) {
        int i, n2;

        n2 = n - 1;
        i = ie;
        while (i <= n - 2) {
            v[i] = v[i + 1];
            i = i + 1;
        }
        
        return n2;
    }
    
    public static int inizializzaVettoreLibri(String[] v, int n) {
        n = inserisciInVettore(v, n, "La bellezza e il coraggio - Paolo Comentale", n);
        n = inserisciInVettore(v, n, "Wonder - R.J. Palacio", n);
        n = inserisciInVettore(v, n, "Delitto e Castigo - Fëdor Dostoevskij", n);
        n = inserisciInVettore(v, n, "Hackers: Gli eroi della rivoluzione informatica - Steven Levy", n);
        n = inserisciInVettore(v, n, "Steve Jobs - Walter Isaacson", n);
        
        return n;
    }
    
    public static int inserisciInVettore(String[] v, int n, String e, int ie) {
        int i, n2;

        n2 = n + 1;
        i = n2 - 1;
        while (i >= ie + 1) {
            v[i] = v[i - 1];
            i = i - 1;
        }
        v[ie] = e;
        
        return n2;
    }
    
    public static int leggiComando() {
        int c;

        do {
            System.out.println("1) Nuovo libro in biblioteca" + (char) 10 + "2) Visualizza libri in biblioteca" + (char) 10 + "3) Visualizza libri in prestito" + (char) 10 + "4) Prendi in prestito un libro" + (char) 10 + "5) Restituisci un libro" + (char) 10 + "6) Esci dal programma");
            c = input.nextInt();
        } while (c < 1 || 6 < c);
        
        return c;
    }
    
    public static int ricercaNelVettore(String[] v, int n, String valore) {
        int i, iTrovato;

        i = 0;
        iTrovato = -1;
        while (i < n && iTrovato == -1) {

            // Controllo se valore è una sottostringa di V[i]
            if (sottostringa(valore, v[i]) != -1) {
                iTrovato = i;
            }
            i = i + 1;
        }
        
        return iTrovato;
    }
    
    public static void salvalibro(String[] v, int nv, String[] p, String[] np, String nomefile) {
        int i;

        outfile = new FileWriter(new File(nomefile));
        outfile.write(nv + "\n");
        i = 0;
        while (i < nv) {
            outfile.write(v[i] + "\n");
            i = i + 1;
        }
        outfile.close();
    }
    
    public static void salvaprestiti(String[] p, int np, String nomefile) {
        int i;

        outfile = new FileWriter(new File(nomefile));
        outfile.write(np + "\n");
        i = 0;
        while (i < np) {
            outfile.write(p[i] + "\n");
            i = i + 1;
        }
        outfile.close();
    }
    
    public static int sottostringa(String sottoStr, String stringa) {
        int i, j, iCurr, iTrovato, lungStr, lungSSt;
        String primoCarattere;
        boolean continua;

        iTrovato = -1;
        lungStr = stringa.length();
        lungSSt = sottoStr.length();
        primoCarattere = sottoStr.charAt(0);
        iCurr = 0;
        while (iCurr < lungStr && iTrovato == -1) {
            i = iCurr;
            continua = true;
            while (i < lungStr && continua) {
                if (!primoCarattere.equals(stringa.charAt(i))) {
                    i = i + 1;
                } else {
                    continua = false;
                }
            }
            iCurr = i;
            if (i < lungStr) {
                iTrovato = iCurr;
                i = i + 1;
                j = 1;
                continua = true;
                while (i < lungStr && j < lungSSt && continua) {
                    if (stringa.charAt(i).equals(sottoStr.charAt(j))) {
                        i = i + 1;
                        j = j + 1;
                    } else {
                        continua = false;
                    }
                }
                if (j < lungSSt) {
                    iTrovato = -1;
                }
            } else {
                iTrovato = -1;
            }
            iCurr = iCurr + 1;
        }
        
        return iTrovato;
    }
    
    public static void visualizzaVettore(String[] v, int n) {
        int i;

        i = 0;
        while (i < n) {
            System.out.println(Integer.toString(i) + ": " + v[i]);
            i = i + 1;
        }
    }
}
