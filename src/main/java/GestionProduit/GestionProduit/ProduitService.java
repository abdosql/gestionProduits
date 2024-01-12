package GestionProduit.GestionProduit;
import java.util.ArrayList;
import java.util.List;

public class ProduitService {
    private List<Produit> produits;

    public ProduitService() {
        this.produits = new ArrayList<>();
    }

    public void ajouterProduit(Produit produit) {
        validerUniciteProduit(produit);
        validerDonneesProduit(produit);
        produits.add(produit);
    }

    public Produit obtenirProduitParId(Long id) {
        return produits.stream()
                .filter(produit -> produit.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Produit> obtenirTousLesProduits() {
        return produits;
    }

    public void mettreAJourProduit(Produit produit) {
        validerExistenceProduit(produit.getId());
        validerDonneesProduit(produit);
        for (int i = 0; i < produits.size(); i++) {
            if (produits.get(i).getId().equals(produit.getId())) {
                produits.set(i, produit);
                break;
            }
        }
    }

    public void supprimerProduit(Long id) {
        validerExistenceProduit(id);
        produits.removeIf(produit -> produit.getId().equals(id));
    }

    private void validerUniciteProduit(Produit produit) {
        if (produits.stream().anyMatch(p -> p.getId().equals(produit.getId()) || p.getNom().equals(produit.getNom()))) {
            throw new IllegalArgumentException("Un produit avec le même ID ou nom existe déjà.");
        }
    }

    private void validerDonneesProduit(Produit produit) {
        if (produit.getPrix() < 0 || produit.getQuantite() < 0) {
            throw new IllegalArgumentException("Le prix et la quantité du produit doivent être positifs.");
        }
    }

    private void validerExistenceProduit(Long id) {
        if (obtenirProduitParId(id) == null) {
            throw new IllegalArgumentException("Le produit avec l'ID " + id + " n'existe pas.");
        }
    }
}