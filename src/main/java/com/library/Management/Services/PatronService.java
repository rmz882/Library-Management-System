package com.library.Management.Services;

import com.library.Management.Entities.Patron;
import com.library.Management.Repository.PatronRepository;
import com.library.Management.Exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    // Retrieve all patrons
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    // Retrieve a specific patron by ID
    public Patron getPatronById(Long id) {
        return patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + id));
    }

    // Add a new patron
    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    // Update an existing patron
    public Patron updatePatron(Long id, Patron patron) {
        Patron existingPatron = patronRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patron not found with id: " + id));

        // Update the existing patron with new data
        existingPatron.setName(patron.getName());
        existingPatron.setContactInformation(patron.getContactInformation());

        return patronRepository.save(existingPatron);
    }

    // Delete a patron
    public void deletePatron(Long id) {
        if (!patronRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patron not found with id: " + id);
        }
        patronRepository.deleteById(id);
    }
}