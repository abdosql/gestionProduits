import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProduitServiceTest {

    @Test
    void ajouterProduit() {
        ProduitService produitService = new ProduitService();
        Produit produit1 = new Produit(1L, "ProduitTest1", 10.0, 5);
        Produit produit2 = new Produit(2L, "ProduitTest2", 15.0, 8);

        assertDoesNotThrow(() -> {
            produitService.ajouterProduit(produit1);
            produitService.ajouterProduit(produit2);
        });

        assertEquals(produit1, produitService.obtenirProduitParId(1L));
        assertEquals(produit2, produitService.obtenirProduitParId(2L));

        assertThrows(IllegalArgumentException.class, () -> produitService.ajouterProduit(produit1));
        assertThrows(IllegalArgumentException.class, () -> produitService.ajouterProduit(new Produit(3L, "ProduitTest3", -5.0, 3)));
    }

    @Test
    void mettreAJourProduit() {
        ProduitService produitService = new ProduitService();
        Produit produit = new Produit(1L, "ProduitTest", 10.0, 5);

        assertThrows(IllegalArgumentException.class, () -> produitService.mettreAJourProduit(produit));

        produitService.ajouterProduit(produit);

        assertThrows(IllegalArgumentException.class, () -> produitService.mettreAJourProduit(new Produit(2L, "ProduitTest2", 12.0, 3)));

        produit.setPrix(15.0);
        produit.setQuantite(8);

        assertDoesNotThrow(() -> produitService.mettreAJourProduit(produit));
        assertEquals(15.0, produitService.obtenirProduitParId(1L).getPrix());
        assertEquals(8, produitService.obtenirProduitParId(1L).getQuantite());
    }

    @Test
    void supprimerProduit() {
        ProduitService produitService = new ProduitService();
        Produit produit1 = new Produit(1L, "ProduitTest1", 10.0, 5);
        Produit produit2 = new Produit(2L, "ProduitTest2", 15.0, 8);

        assertThrows(IllegalArgumentException.class, () -> produitService.supprimerProduit(1L));

        produitService.ajouterProduit(produit1);
        produitService.ajouterProduit(produit2);

        assertDoesNotThrow(() -> {
            produitService.supprimerProduit(1L);
            produitService.supprimerProduit(2L);
        });

        assertNull(produitService.obtenirProduitParId(1L));
        assertNull(produitService.obtenirProduitParId(2L));

        assertThrows(IllegalArgumentException.class, () -> produitService.supprimerProduit(1L));
    }

    @Test
    void obtenirTousLesProduits() {
        ProduitService produitService = new ProduitService();
        Produit produit1 = new Produit(1L, "ProduitTest1", 10.0, 5);
        Produit produit2 = new Produit(2L, "ProduitTest2", 15.0, 8);

        assertDoesNotThrow(() -> {
            produitService.ajouterProduit(produit1);
            produitService.ajouterProduit(produit2);
        });

        assertEquals(2, produitService.obtenirTousLesProduits().size());
        assertTrue(produitService.obtenirTousLesProduits().contains(produit1));
        assertTrue(produitService.obtenirTousLesProduits().contains(produit2));

        produitService.supprimerProduit(1L);

        assertEquals(1, produitService.obtenirTousLesProduits().size());
        assertFalse(produitService.obtenirTousLesProduits().contains(produit1));
        assertTrue(produitService.obtenirTousLesProduits().contains(produit2));
    }

    @Test
    void validerUniciteProduit() {
        ProduitService produitService = new ProduitService();
        Produit produit1 = new Produit(1L, "ProduitTest1", 10.0, 5);
        Produit produit2 = new Produit(2L, "ProduitTest2", 15.0, 8);

        assertDoesNotThrow(() -> {
            produitService.ajouterProduit(produit1);
            produitService.ajouterProduit(produit2);
        });

        assertThrows(IllegalArgumentException.class, () -> produitService.validerUniciteProduit(new Produit(3L, "ProduitTest1", 8.0, 3)));
        assertThrows(IllegalArgumentException.class, () -> produitService.validerUniciteProduit(new Produit(4L, "ProduitTest2", 12.0, 4)));
        assertDoesNotThrow(() -> produitService.validerUniciteProduit(new Produit(5L, "ProduitTest3", 7.0, 2)));
    }

    

}