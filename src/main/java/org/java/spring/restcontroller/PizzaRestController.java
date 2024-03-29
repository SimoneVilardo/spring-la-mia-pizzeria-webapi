package org.java.spring.restcontroller;

import java.util.List;

import org.java.spring.pojo.Pizza;
import org.java.spring.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/v1.0/pizzas")
public class PizzaRestController {

	@Autowired
	private PizzaService pizzaService;

	@GetMapping
    public ResponseEntity<List<Pizza>> getIndex(@RequestParam(required = false) String nome) {

        List<Pizza> pizzas = nome == null  
                ? pizzaService.findAll()
                : pizzaService.findByNome(nome);

        return new ResponseEntity<>(pizzas, HttpStatus.OK);
    }
	
	@GetMapping("{id}")
    public ResponseEntity<Pizza> getPizza(@PathVariable int id) {

        Pizza pizza = pizzaService.findById(id);

        if (pizza == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }
	
	@PostMapping
    public ResponseEntity<Pizza> create(@RequestBody Pizza pizza) {

        pizzaService.save(pizza);

        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }
	
	@PutMapping("{id}")
    public ResponseEntity<Pizza> update(@PathVariable int id, @RequestBody Pizza newPizza) {

        Pizza pizza = pizzaService.findById(id);

        pizza.setNome(newPizza.getNome());
        pizza.setDescrizione(newPizza.getDescrizione());
        pizza.setUrl_foto(newPizza.getUrl_foto());
        pizza.setPrezzo(newPizza.getPrezzo());

        pizzaService.save(pizza);

        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }
	
	@DeleteMapping("{id}")
    public ResponseEntity<Pizza> delete(@PathVariable int id) {

        Pizza pizza = pizzaService.findById(id);

        pizzaService.delete(pizza);

        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }
}
