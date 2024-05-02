package com.demo.demo;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.demo.demo.entities.Etudiant;
import com.demo.demo.repositories.EtudiantRepository;

// import static org.assertj.core.api.Assertions.assertThat;

// @DataJpaTest
// public class TestEtudiantRepository {

//     @Autowired
//     private EtudiantRepository etudiantRepository;

//     @Test
//     public void testCRUDOperations() {
//         // Test Create
//         Etudiant etudiant = new Etudiant();
//         etudiant.setNom("John");
//         etudiant.setAge(25);
//         etudiantRepository.save(etudiant);

//         // Test Read
//         Etudiant retrievedEtudiant = etudiantRepository.findById(etudiant.getId()).orElse(null);
//         assertThat(retrievedEtudiant).isNotNull();
//         assertThat(retrievedEtudiant.getNom()).isEqualTo("John");

//         // Test Update
//         retrievedEtudiant.setAge(30);
//         etudiantRepository.save(retrievedEtudiant);
//         Etudiant updatedEtudiant = etudiantRepository.findById(retrievedEtudiant.getId()).orElse(null);
//         assertThat(updatedEtudiant).isNotNull();
//         assertThat(updatedEtudiant.getAge()).isEqualTo(30);

//         // Test Delete
//         etudiantRepository.deleteById(updatedEtudiant.getId());
//         assertThat(etudiantRepository.findById(updatedEtudiant.getId())).isEmpty();
//     }
// }
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class EtudiantRepositoryTests {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @AfterEach
    public void tearDown() {
        etudiantRepository.deleteAll();
    }

    @Test
    @Rollback(false)
    public void testCreateEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom("John");
        etudiant.setAge(25);
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);
        assertThat(savedEtudiant.getId()).isNotNull();
    }

    @Test
    public void testFindEtudiantByNom() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom("John");
        etudiant.setAge(25);
        etudiantRepository.save(etudiant);

        Etudiant retrievedEtudiant = etudiantRepository.findByNom("John");
        assertThat(retrievedEtudiant).isNotNull();
        assertThat(retrievedEtudiant.getNom()).isEqualTo("John");
    }

    @Test
    public void testListEtudiants() {
        Etudiant etudiant1 = new Etudiant();
        etudiant1.setNom("John");
        etudiant1.setAge(25);
        etudiantRepository.save(etudiant1);

        Etudiant etudiant2 = new Etudiant();
        etudiant2.setNom("Alice");
        etudiant2.setAge(30);
        etudiantRepository.save(etudiant2);

        List<Etudiant> etudiants = (List<Etudiant>) etudiantRepository.findAll();
        assertThat(etudiants.size()).isEqualTo(2);
    }

    @Test
    @Rollback(false)
    public void testUpdateEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom("John");
        etudiant.setAge(25);
        etudiantRepository.save(etudiant);

        Etudiant retrievedEtudiant = etudiantRepository.findByNom("John");
        retrievedEtudiant.setAge(30);
        etudiantRepository.save(retrievedEtudiant);

        Etudiant updatedEtudiant = etudiantRepository.findByNom("John");
        assertThat(updatedEtudiant.getAge()).isEqualTo(30);
    }

    @Test
    @Rollback(true)
    public void testDeleteEtudiant() {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom("John");
        etudiant.setAge(25);
        etudiantRepository.save(etudiant);

        Etudiant retrievedEtudiant = etudiantRepository.findByNom("John");
        etudiantRepository.delete(retrievedEtudiant);

        Etudiant deletedEtudiant = etudiantRepository.findByNom("John");
        assertThat(deletedEtudiant).isNull();
    }
}
