package com.library.Management.Controllers;

import com.library.Management.Entities.Patron;
import com.library.Management.Services.PatronService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    // GET /api/patrons - Retrieve all patrons
    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatrons();
    }

    // GET /api/patrons/{id} - Retrieve a specific patron by ID
    @GetMapping("/{id}")
    public Patron getPatronById(@PathVariable Long id) {
        return patronService.getPatronById(id);
    }

    // POST /api/patrons - Add a new patron
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patron addPatron(@Valid @RequestBody Patron patron) {
        return patronService.addPatron(patron);
    }

    // PUT /api/patrons/{id} - Update an existing patron
    @PutMapping("/{id}")
    public Patron updatePatron(@PathVariable Long id, @Valid @RequestBody Patron patron) {
        return patronService.updatePatron(id, patron);
    }

    // DELETE /api/patrons/{id} - Remove a patron
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatron(@PathVariable Long id) {
        patronService.deletePatron(id);
    }
}