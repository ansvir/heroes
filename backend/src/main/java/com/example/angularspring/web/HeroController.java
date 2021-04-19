package com.example.angularspring.web;

import com.example.angularspring.data.HeroRepository;
import com.example.angularspring.domain.Hero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/hero")
@CrossOrigin(origins = "*")
public class HeroController {

    private HeroRepository heroRepo;

    public HeroController(HeroRepository heroRepo) {
        this.heroRepo = heroRepo;
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Hero>> getAllHeroes() {
        Iterable<Hero> heroes = heroRepo.findAll();
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hero> getHeroById(@PathVariable Long id) {
        Optional<Hero> optHero = heroRepo.findById(id);
        if (optHero.isPresent()) {
            return new ResponseEntity<>(optHero.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping
    ResponseEntity<Hero> putHero(@RequestBody Hero hero) {
        Hero updatedHero = heroRepo.save(hero);
        return new ResponseEntity<>(updatedHero, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Hero> postHero(@RequestBody Hero hero) {
        Hero newHero = heroRepo.save(hero);
        return new ResponseEntity<>(newHero, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteHero(@PathVariable Long id) {
        heroRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    ResponseEntity<Iterable<Hero>> getHeroByName(@RequestParam String name) {
        Iterable<Hero> heroes = heroRepo.findByName(name);
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }
}
