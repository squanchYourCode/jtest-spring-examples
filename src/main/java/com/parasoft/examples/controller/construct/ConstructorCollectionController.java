package com.parasoft.examples.controller.construct;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * An example controller which requires a Collection to construct.
 */
@Controller
public class ConstructorCollectionController
{

    // The constructor-provided collection
    private List<String> values;

    @Autowired
    public ConstructorCollectionController(List<String> values)
    {
        this.values = values;
    }

    /**
     * An MVC handler method which gets all values in the collection
     */
    @RequestMapping("/collexample")
    public ResponseEntity<List<String>> handler(Model model)
    {
        return new ResponseEntity<>(values, HttpStatus.OK);
    }
}
