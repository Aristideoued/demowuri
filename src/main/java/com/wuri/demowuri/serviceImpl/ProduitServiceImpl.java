package com.wuri.demowuri.serviceImpl;

import com.wuri.demowuri.model.Categorie;
import com.wuri.demowuri.model.Produit;
import com.wuri.demowuri.repository.CategorieRepository;
import com.wuri.demowuri.repository.ProduitRepository;
import com.wuri.demowuri.services.ProduitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository, CategorieRepository categorieRepository) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
    }

    @Override
    public Produit creerProduit(Produit produit){
        return  this.produitRepository.save(produit);
    }

    @Override
    public Produit modifierProduit(Produit produit, UUID id){
        Produit produitExisted = produitRepository.findById(id).get();
        if (produitExisted != null){
            produitExisted.setNom(produit.getNom());
            produitExisted.setPrix(produit.getPrix());
            produitExisted.setDescription(produit.getDescription());
            return produitRepository.save(produitExisted);
        }else {
            return null;
        }
    }

    @Override
    public List<Produit> listerProduits(){
        return produitRepository.findAll();
    }

    @Override
    public Produit showProduit(UUID id){
        return produitRepository.findById(id).get();
    }

    @Override
    public void deleteProduit(UUID id){
        produitRepository.deleteById(id);
    }

    @Override
    public Produit affecterProduitCategorie(UUID idProduit, UUID idCategorie){
        Produit produit = produitRepository.findById(idProduit).orElseThrow( ()-> new RuntimeException("Produit non retrouvé avec id:"+idProduit));
        Categorie categorie = categorieRepository.findById(idCategorie).orElseThrow(() -> new RuntimeException("Categorie non retrouvée avec id:"+idCategorie));
        produit.setCategorie(categorie);
        return produitRepository.save(produit);
    }

}
