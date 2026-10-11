package TD1ex9;

public class Mot_dict {

    private String Mot;
    private String Définition;

    public Mot_dict(String Mot, String Définition) {
        this.Mot = Mot;
        this.Définition = Définition;
    }

    public String getMot() {
        return this.Mot;
    }

    public String getDéfinition() {
        return this.Définition;
    }

    public void setMot(String Mot) {
        this.Mot = Mot;
    }

    public void setDéfinition(String Définition) {
        this.Définition = Définition;
    }

    public boolean synonyme(String mot) {
        return this.Mot.equals(mot);
    }

    public void afficher() {
        System.out.println("Mot : " + this.Mot);
        System.out.println("Définition : " + this.Définition);
    }
}

class Dictionnaire {

    private int nb_mots;
    private Mot_dict[] Dict;
    private String Nom;

    public Dictionnaire(int taille, String Nom) {
        this.nb_mots = 0;
        this.Dict = new Mot_dict[taille];
        this.Nom = Nom;
    }

    public int getNb_mots() {
        return this.nb_mots;
    }

    public String getNom() {
        return this.Nom;
    }

    public void Ajouter_Mot(Mot_dict m) {
        if (nb_mots >= Dict.length) {
            System.out.println("Le dictionnaire est plein !");
            return;
        }

        int i = nb_mots - 1;

        while (i >= 0 &&
               Dict[i].getMot().compareTo(m.getMot()) > 0) {
            Dict[i + 1] = Dict[i];
            i--;
        }

        Dict[i + 1] = m;
        nb_mots++;
    }

    public void Trier() {
        for (int i = 0; i < nb_mots - 1; i++) {
            for (int j = 0; j < nb_mots - 1 - i; j++) {
                if (Dict[j].getMot().compareTo(
                        Dict[j + 1].getMot()) > 0) {

                    Mot_dict temp = Dict[j];
                    Dict[j] = Dict[j + 1];
                    Dict[j + 1] = temp;
                }
            }
        }
    }

    public void Supprimer_Mot(Mot_dict m) {
        int i = 0;

        while (i < nb_mots &&
               !Dict[i].getMot().equals(m.getMot())) {
            i++;
        }

        if (i == nb_mots) {
            System.out.println("Mot introuvable !");
            return;
        }

        for (int j = i; j < nb_mots - 1; j++) {
            Dict[j] = Dict[j + 1];
        }

        Dict[nb_mots - 1] = null;
        nb_mots--;
    }

    public String Recherche_dicho(String mot) {
        Trier();

        int debut = 0;
        int fin = nb_mots - 1;

        while (debut <= fin) {
            int milieu = (debut + fin) / 2;
            int resultat = Dict[milieu].getMot().compareTo(mot);

            if (resultat == 0) {
                return Dict[milieu].getDéfinition();
            } else if (resultat < 0) {
                debut = milieu + 1;
            } else {
                fin = milieu - 1;
            }
        }

        return "Mot introuvable !";
    }

    public void Lister_dictionnaire() {
        System.out.println("Dictionnaire : " + this.Nom);

        for (int i = 0; i < nb_mots; i++) {
            Dict[i].afficher();
        }
    }

    public int Nombre_synonyme(Mot_dict m) {
        int c = 0;

        for (int i = 0; i < nb_mots; i++) {
            if (Dict[i].synonyme(m.getMot()) &&
                !Dict[i].getMot().equals(m.getMot())) {
                c++;
            }
        }

        return c;
    }
}

class Main {
    public static void main(String[] args) {

        Dictionnaire d = new Dictionnaire(10, "Français");

        Mot_dict m1 = new Mot_dict("Cercle", "Figure géométrique circulaire");

        Mot_dict m2 = new Mot_("Carré", "Figure géométrique à quatre côtés égaux");

        d.Ajouter_Mot(m1);
        d.Ajouter_Mot(m2);

        d.Lister_dictionnaire();

       
        System.out.println(d.Recherche_dicho("Cercle"));

        d.Supprimer_Mot(m2);
        d.Lister_dictionnaire();

        System.out.println("Nombre de synonymes : " d.Nombre_synonyme(m1));
    }
}
